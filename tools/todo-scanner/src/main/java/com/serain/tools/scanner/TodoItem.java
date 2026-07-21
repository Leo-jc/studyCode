package com.serain.tools.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 单个待办事项的数据模型
 */
public class TodoItem {
    /** 文件相对路径 */
    private String filePath;
    /** 行号 */
    private int lineNumber;
    /** 标记类型：待办 / 修复 / 临时方案 / 注意 */
    private String markType;
    /** 待办原文内容 */
    private String content;
    /** 所在方法/类上下文（前后各2行） */
    private List<String> contextLines;
    /** 所属类名 */
    private String className;
    /** 所属模块 */
    private String module;
    /** 优先级评分（越高越紧急） */
    private int priorityScore;
    /** 优先级标签 */
    private String priorityLabel;
    /** 自动生成的修复建议 */
    private String suggestion;
    /** 是否已自动修复 */
    private boolean autoFixed;
    /** 注释类型：JAVADOC / BLOCK / LINE */
    private String commentType;
    /** 文件的全限定类名 */
    private String qualifiedClassName;

    public TodoItem() {
        this.contextLines = new ArrayList<>();
        this.autoFixed = false;
        this.priorityScore = 1;
        this.priorityLabel = "低";
        this.markType = "TODO";
        this.commentType = "LINE";
    }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public int getLineNumber() { return lineNumber; }
    public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

    public String getMarkType() { return markType; }
    public void setMarkType(String markType) { this.markType = markType; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<String> getContextLines() { return contextLines; }
    public void setContextLines(List<String> contextLines) { this.contextLines = contextLines; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public int getPriorityScore() { return priorityScore; }
    public void setPriorityScore(int priorityScore) { this.priorityScore = priorityScore; }

    public String getPriorityLabel() { return priorityLabel; }
    public void setPriorityLabel(String priorityLabel) { this.priorityLabel = priorityLabel; }

    public String getSuggestion() { return suggestion; }
    public void setSuggestion(String suggestion) { this.suggestion = suggestion; }

    public boolean isAutoFixed() { return autoFixed; }
    public void setAutoFixed(boolean autoFixed) { this.autoFixed = autoFixed; }

    public String getCommentType() { return commentType; }
    public void setCommentType(String commentType) { this.commentType = commentType; }

    public String getQualifiedClassName() { return qualifiedClassName; }
    public void setQualifiedClassName(String qualifiedClassName) { this.qualifiedClassName = qualifiedClassName; }

    @Override
    public String toString() {
        return String.format("[%s] %s:%d - %s",
            markType, filePath, lineNumber,
            content.length() > 60 ? content.substring(0, 60) + "..." : content);
    }
}
