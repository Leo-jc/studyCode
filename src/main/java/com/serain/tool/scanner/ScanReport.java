package com.serain.tool.scanner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 扫描报告的汇总数据结构，支持导出为 JSON
 */
public class ScanReport {
    /** 扫描时间戳 */
    private String scanTime;
    /** 扫描的根目录 */
    private String scanRoot;
    /** 扫描的文件总数 */
    private int totalFilesScanned;
    /** 跳过的文件数 */
    private int totalFilesSkipped;
    /** 跳过的目录列表 */
    private List<String> skippedDirs;
    /** 发现的待办/修复标记总数 */
    private int totalItems;
    /** 按标记类型统计 */
    private Map<String, Integer> countByType;
    /** 按模块统计 */
    private Map<String, Integer> countByModule;
    /** 按优先级统计 */
    private Map<String, Integer> countByPriority;
    /** 所有待办项列表 */
    private List<TodoItem> items;
    /** 自动修复的项数 */
    private int autoFixedCount;
    /** 执行日志 */
    private List<String> executionLog;

    public ScanReport() {
        this.items = new ArrayList<>();
        this.skippedDirs = new ArrayList<>();
        this.countByType = new LinkedHashMap<>();
        this.countByModule = new LinkedHashMap<>();
        this.countByPriority = new LinkedHashMap<>();
        this.executionLog = new ArrayList<>();
        this.autoFixedCount = 0;
    }

    public void log(String message) {
        String logEntry = String.format("[%s] %s",
            java.time.LocalTime.now().toString().substring(0, 12), message);
        executionLog.add(logEntry);
        System.out.println(logEntry);
    }

    public String getScanTime() { return scanTime; }
    public void setScanTime(String scanTime) { this.scanTime = scanTime; }

    public String getScanRoot() { return scanRoot; }
    public void setScanRoot(String scanRoot) { this.scanRoot = scanRoot; }

    public int getTotalFilesScanned() { return totalFilesScanned; }
    public void setTotalFilesScanned(int totalFilesScanned) { this.totalFilesScanned = totalFilesScanned; }

    public int getTotalFilesSkipped() { return totalFilesSkipped; }
    public void setTotalFilesSkipped(int totalFilesSkipped) { this.totalFilesSkipped = totalFilesSkipped; }

    public List<String> getSkippedDirs() { return skippedDirs; }
    public void setSkippedDirs(List<String> skippedDirs) { this.skippedDirs = skippedDirs; }

    public int getTotalItems() { return totalItems; }
    public void setTotalItems(int totalItems) { this.totalItems = totalItems; }

    public Map<String, Integer> getCountByType() { return countByType; }
    public void setCountByType(Map<String, Integer> countByType) { this.countByType = countByType; }

    public Map<String, Integer> getCountByModule() { return countByModule; }
    public void setCountByModule(Map<String, Integer> countByModule) { this.countByModule = countByModule; }

    public Map<String, Integer> getCountByPriority() { return countByPriority; }
    public void setCountByPriority(Map<String, Integer> countByPriority) { this.countByPriority = countByPriority; }

    public List<TodoItem> getItems() { return items; }
    public void setItems(List<TodoItem> items) { this.items = items; }

    public int getAutoFixedCount() { return autoFixedCount; }
    public void setAutoFixedCount(int autoFixedCount) { this.autoFixedCount = autoFixedCount; }

    public List<String> getExecutionLog() { return executionLog; }
    public void setExecutionLog(List<String> executionLog) { this.executionLog = executionLog; }
}
