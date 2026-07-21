package com.serain.tools.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 待办事项扫描器 —— 递归扫描项目目录，提取所有待办/修复/临时方案/注意标记，
 * 分类汇总并输出结构化 JSON 报告。
 *
 * <p>用法：</p>
 * <pre>{@code
 *   ScanReport report = TodoScanner.scan("e:/Code/Source/Trae/studyCode/src");
 *   String json = TodoScanner.toJson(report);
 *   System.out.println(json);
 * }</pre>
 */
public class TodoScanner {

    // ======================== 配置常量 ========================

    /** 扫描的标记关键词 */
    private static final List<String> MARK_KEYWORDS = Arrays.asList("TODO", "FIXME", "HACK", "XXX");

    /** 需要跳过的目录名 */
    private static final Set<String> SKIP_DIRS = new HashSet<>(Arrays.asList(
        "target", ".git", "node_modules", ".idea", "out", "build",
        "__pycache__", ".svn", ".vscode", "dist", ".settings", "bin",
        ".codebuddy"
    ));

    /** 需要扫描的文件扩展名 */
    private static final Set<String> SCAN_EXTENSIONS = new HashSet<>(Arrays.asList(
        ".java", ".kt", ".py", ".js", ".ts", ".tsx", ".jsx",
        ".xml", ".yml", ".yaml", ".properties", ".json", ".html",
        ".css", ".scss", ".vue", ".sql", ".md"
    ));

    /** 用于识别 Javadoc 块：以 * 开头的内容 */
    private static final Pattern JAVADOC_LINE = Pattern.compile("^\\s*\\*\\s*(.*)$");

    /** 用于提取标记关键词 */
    private static final Pattern MARK_PATTERN;

    static {
        String patternStr = "\\b(" + String.join("|", MARK_KEYWORDS) + ")\\b[\\s:：]*(.*)";
        MARK_PATTERN = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
    }

    // ======================== 公共入口 ========================

    /**
     * 扫描指定根目录下所有源文件中的待办标记
     *
     * @param rootPath   项目根目录（如 "e:/Code/Source/Trae/studyCode"）
     * @param sourceDir  源码子目录（如 "src"），可选，null 则扫描整个 rootPath
     * @return 扫描报告
     */
    public static ScanReport scan(String rootPath, String sourceDir) {
        Path root = Paths.get(rootPath).toAbsolutePath().normalize();
        Path scanRoot = (sourceDir != null && !sourceDir.isEmpty())
            ? root.resolve(sourceDir) : root;

        ScanReport report = new ScanReport();
        report.setScanTime(java.time.LocalDateTime.now().toString());
        report.setScanRoot(scanRoot.toString());

        long startTime = System.currentTimeMillis();
        report.log("========== 待办扫描器启动 ==========");
        report.log("扫描根目录: " + scanRoot);
        report.log("跳过的目录: " + String.join(", ", SKIP_DIRS));

        if (!Files.exists(scanRoot)) {
            report.log("[错误] 扫描目录不存在: " + scanRoot);
            return report;
        }

        try {
            scanDirectory(scanRoot, root, report);
        } catch (Exception e) {
            report.log("[错误] 扫描过程中发生异常: " + e.getMessage());
        }

        // 汇总统计
        buildStatistics(report);

        long elapsed = System.currentTimeMillis() - startTime;
        report.log("========== 扫描完成 ==========");
        report.log(String.format("耗时: %.2f 秒", elapsed / 1000.0));
        report.log(String.format("扫描文件: %d, 跳过文件: %d, 发现待办: %d",
            report.getTotalFilesScanned(), report.getTotalFilesSkipped(),
            report.getTotalItems()));

        return report;
    }

    /** 便捷方法：直接扫描整个目录 */
    public static ScanReport scan(String rootPath) {
        return scan(rootPath, null);
    }

    // ======================== 目录扫描 ========================

    private static void scanDirectory(Path dir, Path projectRoot, ScanReport report) throws IOException {
        try (Stream<Path> stream = Files.list(dir)) {
            List<Path> entries = stream.sorted().toList();

            for (Path entry : entries) {
                String name = entry.getFileName().toString();

                if (Files.isDirectory(entry)) {
                    if (SKIP_DIRS.contains(name)) {
                        report.getSkippedDirs().add(entry.toString());
                        report.log("[跳过目录] " + entry);
                        continue;
                    }
                    scanDirectory(entry, projectRoot, report);
                } else if (Files.isRegularFile(entry)) {
                    String ext = getExtension(name);
                    if (SCAN_EXTENSIONS.contains(ext)) {
                        report.setTotalFilesScanned(report.getTotalFilesScanned() + 1);
                        scanFile(entry, projectRoot, report, ext);
                    } else {
                        report.setTotalFilesSkipped(report.getTotalFilesSkipped() + 1);
                    }
                }
            }
        }
    }

    // ======================== 文件扫描 ========================

    private static void scanFile(Path filePath, Path projectRoot, ScanReport report, String ext) {
        String relativePath = projectRoot.relativize(filePath).toString();
        String className = extractClassName(filePath);

        try {
            List<String> allLines = Files.readAllLines(filePath);
            boolean inJavadoc = false;
            // 收集 Javadoc 块中各行的行号和内容
            List<int[]> javadocLineNumbers = new ArrayList<>();
            List<String> javadocLines = new ArrayList<>();

            for (int i = 0; i < allLines.size(); i++) {
                String line = allLines.get(i);
                String trimmed = line.trim();
                int lineNum = i + 1;

                // ----- Javadoc 块处理 (/** ... */) -----
                if (trimmed.startsWith("/**") && !trimmed.endsWith("*/")) {
                    inJavadoc = true;
                    javadocLineNumbers.clear();
                    javadocLines.clear();
                    // 也检查 /** 行本身
                    checkForMarks(trimmed, "JAVADOC", relativePath,
                        lineNum, className, allLines, report);
                    continue;
                }
                if (inJavadoc) {
                    javadocLineNumbers.add(new int[]{lineNum, i});
                    javadocLines.add(line);
                    if (trimmed.endsWith("*/") || trimmed.startsWith("*/")) {
                        inJavadoc = false;
                        // 逐行搜索 Javadoc 内部的标记
                        for (int idx = 0; idx < javadocLines.size(); idx++) {
                            int actualLineNum = javadocLineNumbers.get(idx)[0];
                            String javadocLine = javadocLines.get(idx);
                            checkForMarks(javadocLine, "JAVADOC", relativePath,
                                actualLineNum, className, allLines, report);
                        }
                        javadocLineNumbers.clear();
                        javadocLines.clear();
                    }
                    continue;
                }
                // 单行 Javadoc
                if (trimmed.startsWith("/**") && trimmed.endsWith("*/")) {
                    checkForMarks(trimmed, "JAVADOC", relativePath,
                        lineNum, className, allLines, report);
                    continue;
                }

                // ----- 单行注释 (//) -----
                if (trimmed.startsWith("//")) {
                    checkForMarks(trimmed, "LINE", relativePath,
                        lineNum, className, allLines, report);
                    continue;
                }

                // ----- 普通行中的行内注释 -----
                int commentIdx = trimmed.indexOf("//");
                if (commentIdx > 0) {
                    String inlineComment = trimmed.substring(commentIdx);
                    checkForMarks(inlineComment, "LINE", relativePath,
                        lineNum, className, allLines, report);
                }
            }

        } catch (IOException e) {
            report.log("[警告] 无法读取文件: " + relativePath + " - " + e.getMessage());
        }
    }

    // ======================== 标记提取 ========================

    private static void checkForMarks(String commentText, String commentType,
                                       String filePath, int lineNum, String className,
                                       List<String> allLines, ScanReport report) {
        Matcher matcher = MARK_PATTERN.matcher(commentText);
        while (matcher.find()) {
            String markType = matcher.group(1).toUpperCase();
            String content = matcher.group(2) != null ? matcher.group(2).trim() : "";

            TodoItem item = new TodoItem();
            item.setFilePath(filePath);
            item.setLineNumber(lineNum);
            item.setMarkType(markType);
            item.setContent(content);
            item.setClassName(className);
            item.setModule(extractModule(filePath));
            item.setCommentType(commentType);
            item.setQualifiedClassName(filePath.replace("/", ".")
                .replace("\\", ".").replace(".java", ""));

            // 提取上下文
            List<String> ctx = new ArrayList<>();
            for (int j = Math.max(0, lineNum - 3); j < Math.min(allLines.size(), lineNum + 2); j++) {
                ctx.add(String.format("%5d| %s", j + 1, allLines.get(j)));
            }
            item.setContextLines(ctx);

            // 评估优先级
            evaluatePriority(item);

            // 生成修复建议
            generateSuggestion(item);

            report.getItems().add(item);
            report.log(String.format("[发现] %s | %s:%d | %s...",
                markType, filePath, lineNum,
                content.length() > 40 ? content.substring(0, 40) : content));
        }
    }

    // ======================== 优先级评估 ========================

    private static void evaluatePriority(TodoItem item) {
        String content = item.getContent().toLowerCase();
        int score = 1;
        String label = "低";

        // 紧急关键词
        if (containsAny(content, "urgent", "紧急", "critical", "fix")) {
            score += 5;
        }
        // Bug 相关
        if (containsAny(content, "bug", "error", "错误", "缺陷", "crash", "崩溃")) {
            score += 4;
        }
        // 安全相关
        if (containsAny(content, "security", "安全", "漏洞", "xss", "sql injection")) {
            score += 5;
        }
        // 性能相关
        if (containsAny(content, "performance", "性能", "优化", "slow", "optimize")) {
            score += 3;
        }
        // 空描述（最常见的未填写类型）
        if (content.isEmpty() || content.equals("TODO") || content.equals("todo")) {
            score = 1;
        }
        // 根据标记类型
        if ("FIXME".equals(item.getMarkType())) {
            score += 2;
        }

        if (score >= 7) {
            label = "高";
        } else if (score >= 4) {
            label = "中";
        }

        item.setPriorityScore(score);
        item.setPriorityLabel(label);
    }

    // ======================== 修复建议生成 ========================

    private static void generateSuggestion(TodoItem item) {
        String className = item.getClassName();
        String module = item.getModule();
        String content = item.getContent();
        StringBuilder sb = new StringBuilder();

        boolean isLeetCode = className != null && className.startsWith("E");
        boolean isNiuKe = className != null && className.startsWith("BISHI");
        boolean isExamQ = className != null && className.startsWith("Q");
        boolean isDescStub = content.isEmpty() || content.equals("TODO");

        if (isDescStub) {
            // 最常见的场景：@Description 待办未填写（空描述）
            sb.append("【自动填充建议】将 @Description 改为有意义的描述:\n");
            if (isLeetCode) {
                sb.append("  @Description: LeetCode 第 ")
                    .append(className.substring(1))
                    .append(" 题 —— [请填写题目名称]");
            } else if (isNiuKe) {
                sb.append("  @Description: 牛客网第 ")
                    .append(className.replace("BISHI", ""))
                    .append(" 题 —— [请填写题目名称]");
            } else if (isExamQ) {
                sb.append("  @Description: ").append(module).append("笔试题 —— [请填写题目描述]");
            } else {
                sb.append("  @Description: ").append(className).append(" —— [请填写功能描述]");
            }
        } else {
            // 有内容的待办
            sb.append("【处理建议】");
            if (containsAny(content.toLowerCase(), "optimize", "优化", "重构", "refactor")) {
                sb.append("\n  1. 定位相关代码的性能瓶颈\n");
                sb.append("  2. 使用更好的数据结构或算法\n");
                sb.append("  3. 添加单元测试验证优化效果");
            } else if (containsAny(content.toLowerCase(), "fix", "修复", "bug", "错误")) {
                sb.append("\n  1. 复现问题并添加断点调试\n");
                sb.append("  2. 修改后运行相关测试用例\n");
                sb.append("  3. 代码审查确认修复方案");
            } else if (containsAny(content.toLowerCase(), "implement", "实现", "完成", "添加")) {
                sb.append("\n  1. 根据已有逻辑补充缺失的实现\n");
                sb.append("  2. 参照同类文件的代码结构\n");
                sb.append("  3. 实现后添加测试代码");
            } else {
                sb.append("\n  1. 评估此待办的实际需求\n");
                sb.append("  2. 制定实现计划并排期\n");
                sb.append("  3. 实现后移除待办标记");
            }
        }

        item.setSuggestion(sb.toString());
    }

    // ======================== 统计汇总 ========================

    private static void buildStatistics(ScanReport report) {
        Map<String, Integer> byType = new LinkedHashMap<>();
        Map<String, Integer> byModule = new LinkedHashMap<>();
        Map<String, Integer> byPriority = new LinkedHashMap<>();

        for (TodoItem item : report.getItems()) {
            byType.merge(item.getMarkType(), 1, Integer::sum);
            byModule.merge(item.getModule(), 1, Integer::sum);
            byPriority.merge(item.getPriorityLabel(), 1, Integer::sum);
        }

        report.setTotalItems(report.getItems().size());
        report.setCountByType(byType);
        report.setCountByModule(byModule);
        report.setCountByPriority(byPriority);
    }

    // ======================== JSON 序列化 ========================

    /**
     * 将扫描报告导出为格式化的 JSON 字符串（不依赖第三方库）
     */
    public static String toJson(ScanReport report) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");

        // 基础信息
        appendJsonField(json, 1, "scanTime", report.getScanTime(), true);
        appendJsonField(json, 1, "scanRoot", report.getScanRoot(), true);
        appendJsonField(json, 1, "totalFilesScanned", report.getTotalFilesScanned(), true);
        appendJsonField(json, 1, "totalFilesSkipped", report.getTotalFilesSkipped(), true);
        appendJsonField(json, 1, "totalItems", report.getTotalItems(), true);
        appendJsonField(json, 1, "autoFixedCount", report.getAutoFixedCount(), true);

        // 跳过的目录
        appendJsonArray(json, 1, "skippedDirs", report.getSkippedDirs(), true);

        // 分类统计
        appendJsonMap(json, 1, "countByType", report.getCountByType(), true);
        appendJsonMap(json, 1, "countByModule", report.getCountByModule(), true);
        appendJsonMap(json, 1, "countByPriority", report.getCountByPriority(), true);

        // 待办列表
        json.append("  \"items\": [\n");
        List<TodoItem> items = report.getItems();
        for (int i = 0; i < items.size(); i++) {
            json.append(serializeItem(items.get(i), 2));
            if (i < items.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("  ],\n");

        // 执行日志
        appendJsonArray(json, 1, "executionLog", report.getExecutionLog(), false);

        json.append("}");
        return json.toString();
    }

    private static String serializeItem(TodoItem item, int indent) {
        StringBuilder sb = new StringBuilder();
        String pad = "  ".repeat(indent);
        sb.append(pad).append("{\n");
        appendJsonField(sb, indent + 1, "filePath", item.getFilePath(), true);
        appendJsonField(sb, indent + 1, "lineNumber", item.getLineNumber(), true);
        appendJsonField(sb, indent + 1, "markType", item.getMarkType(), true);
        appendJsonField(sb, indent + 1, "content", item.getContent(), true);
        appendJsonField(sb, indent + 1, "className", item.getClassName(), true);
        appendJsonField(sb, indent + 1, "module", item.getModule(), true);
        appendJsonField(sb, indent + 1, "commentType", item.getCommentType(), true);
        appendJsonField(sb, indent + 1, "priorityLabel", item.getPriorityLabel(), true);
        appendJsonField(sb, indent + 1, "priorityScore", item.getPriorityScore(), true);
        appendJsonField(sb, indent + 1, "autoFixed", item.isAutoFixed(), true);
        appendJsonField(sb, indent + 1, "qualifiedClassName", item.getQualifiedClassName(), true);
        appendJsonArray(sb, indent + 1, "contextLines", item.getContextLines(), true);
        appendJsonField(sb, indent + 1, "suggestion", item.getSuggestion(), false);
        sb.append(pad).append("}");
        return sb.toString();
    }

    // ======================== JSON 辅助方法 ========================

    private static void appendJsonField(StringBuilder sb, int indent, String key,
                                         String value, boolean trailingComma) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("\"").append(key).append("\": ");
        if (value == null) {
            sb.append("null");
        } else {
            sb.append("\"").append(escapeJson(value)).append("\"");
        }
        if (trailingComma) sb.append(",");
        sb.append("\n");
    }

    private static void appendJsonField(StringBuilder sb, int indent, String key,
                                         int value, boolean trailingComma) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("\"").append(key).append("\": ").append(value);
        if (trailingComma) sb.append(",");
        sb.append("\n");
    }

    private static void appendJsonField(StringBuilder sb, int indent, String key,
                                         boolean value, boolean trailingComma) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("\"").append(key).append("\": ").append(value);
        if (trailingComma) sb.append(",");
        sb.append("\n");
    }

    private static void appendJsonArray(StringBuilder sb, int indent, String key,
                                         List<String> items, boolean trailingComma) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("\"").append(key).append("\": [\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(pad).append("  \"").append(escapeJson(items.get(i))).append("\"");
            if (i < items.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append(pad).append("]");
        if (trailingComma) sb.append(",");
        sb.append("\n");
    }

    private static void appendJsonMap(StringBuilder sb, int indent, String key,
                                       Map<String, Integer> map, boolean trailingComma) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("\"").append(key).append("\": {\n");
        List<String> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            sb.append(pad).append("  \"")
                .append(escapeJson(keys.get(i))).append("\": ")
                .append(map.get(keys.get(i)));
            if (i < keys.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append(pad).append("}");
        if (trailingComma) sb.append(",");
        sb.append("\n");
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // ======================== 修复建议执行 ========================

    /**
     * 对指定待办项执行自动修复（仅为 @Description 待办标记的项生成基础代码框架描述）
     *
     * @param item    目标待办项
     * @param dryRun  是否仅模拟（不实际修改文件）
     * @return 修复结果描述
     */
    public static String autoFix(TodoItem item, boolean dryRun) {
        String className = item.getClassName();
        String commentType = item.getCommentType();
        String content = item.getContent();

        // 只处理 Javadoc 中空描述的情况
        if (!"JAVADOC".equals(commentType)) {
            return "跳过：非 Javadoc 类型的待办，需人工判断";
        }
        if (content != null && !content.isEmpty() && !content.equals("TODO")) {
            return "跳过：待办已有自定义内容，保留";
        }

        // 生成建议描述
        String suggestion = generateDescription(className, item.getModule());
        if (dryRun) {
            item.setSuggestion("【自动修复（预览）】将替换为: " + suggestion);
            return "预览完成（dry-run 模式）: " + suggestion;
        }

        // 实际修改文件（通过文件内容替换）
        Path filePath = Paths.get(item.getFilePath());
        try {
            String fileContent = Files.readString(filePath);
            // 替换 @Description 待办标记为具体描述
            String updated = fileContent.replaceFirst(
                "@Description:\\s*TODO",
                "@Description: " + suggestion
            );
            Files.writeString(filePath, updated);
            item.setAutoFixed(true);
            return "修复成功: @Description -> " + suggestion;
        } catch (IOException e) {
            return "修复失败: " + e.getMessage();
        }
    }

    private static String generateDescription(String className, String module) {
        if (className == null) return "待补充描述";
        if (className.startsWith("E")) {
            return "LeetCode 第 " + className.substring(1) + " 题";
        }
        if (className.startsWith("BISHI")) {
            return "牛客网第 " + className.replace("BISHI", "") + " 题";
        }
        if (className.startsWith("Q") || className.startsWith("Test")) {
            return module + "笔试题";
        }
        return className + "功能描述";
    }

    // ======================== 工具方法 ========================

    private static String extractClassName(Path filePath) {
        String name = filePath.getFileName().toString();
        int dotIdx = name.lastIndexOf('.');
        return dotIdx > 0 ? name.substring(0, dotIdx) : name;
    }

    private static String extractModule(String relativePath) {
        // 根据路径推断模块
        String normalized = relativePath.replace('\\', '/');
        if (normalized.contains("exercise/leetcode")) return "LeetCode练习";
        if (normalized.contains("exercise/niuke")) return "牛客网练习";
        if (normalized.contains("exam/alibaba")) return "阿里巴巴笔试";
        if (normalized.contains("exam/bilibili")) return "B站笔试";
        if (normalized.contains("exam/bytedance")) return "字节跳动笔试";
        if (normalized.contains("exam/dewu")) return "得物笔试";
        if (normalized.contains("exam/huawei")) return "华为笔试";
        if (normalized.contains("exam/jingdong")) return "京东笔试";
        if (normalized.contains("exam/meituan")) return "美团笔试";
        if (normalized.contains("exam/tencent")) return "腾讯笔试";
        if (normalized.contains("exam/xiechen")) return "携程笔试";
        if (normalized.contains("doubleweekgame")) return "双周赛";
        if (normalized.contains("singleweekgame")) return "单周赛";
        if (normalized.contains("study")) return "学习代码";
        if (normalized.contains("parameter")) return "公共数据结构";
        return "其他";
    }

    private static boolean containsAny(String text, String... keywords) {
        for (String kw : keywords) {
            if (text.contains(kw)) return true;
        }
        return false;
    }

    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf('.');
        return idx > 0 ? fileName.substring(idx).toLowerCase() : "";
    }

    // ======================== 主方法（演示入口） ========================

    public static void main(String[] args) {
        // 默认扫描当前项目的 src 目录
        String projectRoot = args.length > 0 ? args[0] : System.getProperty("user.dir");

        System.out.println("项目根目录: " + projectRoot);
        System.out.println();

        // 执行扫描
        ScanReport report = scan(projectRoot, "src");

        // 输出 JSON 报告
        String json = toJson(report);

        // 写入文件
        Path outputPath = Paths.get(projectRoot, "target", "todo-report.json");
        try {
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, json);
            System.out.println("\nJSON 报告已写入: " + outputPath);
        } catch (IOException e) {
            System.err.println("无法写入 JSON 报告: " + e.getMessage());
        }

        // 打印摘要
        System.out.println("\n========== 扫描摘要 ==========");
        System.out.printf("文件扫描: %d | 跳过: %d | 待办/FIXME: %d%n",
            report.getTotalFilesScanned(), report.getTotalFilesSkipped(),
            report.getTotalItems());
        System.out.println("\n按模块统计:");
        report.getCountByModule().forEach((mod, count) ->
            System.out.printf("  %-20s: %d%n", mod, count));
        System.out.println("\n按优先级统计:");
        report.getCountByPriority().forEach((pri, count) ->
            System.out.printf("  %s: %d%n", pri, count));
    }
}
