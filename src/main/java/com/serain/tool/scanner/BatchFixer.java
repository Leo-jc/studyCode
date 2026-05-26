package com.serain.tool.scanner;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 批量待办修复器 —— 扫描项目中所有 @Description 待办描述并自动填充。
 * <p>
 * 用法: java -cp target/classes com.serain.tool.scanner.BatchFixer [项目根目录]
 */
public class BatchFixer {

    public static void main(String[] args) {
        String rootPath = args.length > 0 ? args[0] : System.getProperty("user.dir");
        Path root = Paths.get(rootPath).toAbsolutePath().normalize();

        System.out.println("========== 批量待办修复器 ==========");
        System.out.println("项目根目录: " + root);
        System.out.println();

        // 扫描项目
        ScanReport report = TodoScanner.scan(root.toString(), "src");
        System.out.println();

        // 统计信息
        int totalItems = report.getItems().size();
        int skippedCount = 0;
        int successCount = 0;
        int failCount = 0;

        System.out.println("========== 开始批量修复 ==========");
        System.out.println("共发现 " + totalItems + " 个待办项");
        System.out.println();

        for (TodoItem item : report.getItems()) {
            // 跳过非本项目的待办（如 target 目录下的文件）
            String fp = item.getFilePath();
            if (fp == null || fp.contains("target") || fp.contains(".git")) {
                continue;
            }

            // 构造绝对路径
            Path absPath = root.resolve(item.getFilePath());
            // 临时替换 filePath 为绝对路径以供 autoFix 使用
            String originalPath = item.getFilePath();
            item.setFilePath(absPath.toString());

            String result = TodoScanner.autoFix(item, false);
            String shortPath = originalPath.length() > 50
                ? "..." + originalPath.substring(originalPath.length() - 50)
                : originalPath;

            if (result.startsWith("修复成功")) {
                successCount++;
                System.out.println("  ✓ " + shortPath);
            } else if (result.startsWith("跳过")) {
                skippedCount++;
                // 不打印跳过的，减少输出噪音
            } else {
                failCount++;
                System.out.println("  ✗ " + shortPath + " : " + result);
            }

            // 恢复原路径
            item.setFilePath(originalPath);
        }

        System.out.println();
        System.out.println("========== 修复完成 ==========");
        System.out.println("成功: " + successCount);
        System.out.println("跳过: " + skippedCount);
        System.out.println("失败: " + failCount);
        System.out.println("总计: " + totalItems);
    }
}
