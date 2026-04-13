package com.serain.exam.bytedance;

import java.util.Stack;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.bytedance
 * @Author: Serain
 * @CreateTime: 2026-03-30  09:49
 * @Description: 判断括号序列是否有效
 * @Version: 1.0
 */
public class Test {
    
    enum BracketType {
        LEFT_PARENTHESIS('(', ')'),
        RIGHT_PARENTHESIS(')', '('),
        LEFT_BRACKET('[', ']'),
        RIGHT_BRACKET(']', '['),
        LEFT_BRACE('{', '}'),
        RIGHT_BRACE('}', '{');
        
        private final char symbol;
        private final char matchingSymbol;
        
        BracketType(char symbol, char matchingSymbol) {
            this.symbol = symbol;
            this.matchingSymbol = matchingSymbol;
        }
        
        public char getSymbol() {
            return symbol;
        }
        
        public char getMatchingSymbol() {
            return matchingSymbol;
        }
        
        public boolean isLeft() {
            return this == LEFT_PARENTHESIS || this == LEFT_BRACKET || this == LEFT_BRACE;
        }
        
        public boolean isRight() {
            return this == RIGHT_PARENTHESIS || this == RIGHT_BRACKET || this == RIGHT_BRACE;
        }
        
        public static BracketType fromChar(char c) {
            for (BracketType type : values()) {
                if (type.symbol == c) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid bracket character: " + c);
        }
    }
    
    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            BracketType type = BracketType.fromChar(c);
            
            if (type.isLeft()) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top != type.getMatchingSymbol()) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println("测试用例1: \"()\" -> " + isValid("()"));
        System.out.println("测试用例2: \"()[]{}\" -> " + isValid("()[]{}"));
        System.out.println("测试用例3: \"(]\" -> " + isValid("(]"));
        System.out.println("测试用例4: \"([)]\" -> " + isValid("([)]"));
        System.out.println("测试用例5: \"{[]}\" -> " + isValid("{[]}"));
        System.out.println("测试用例6: \"\" -> " + isValid(""));
        System.out.println("测试用例7: \"((()))\" -> " + isValid("((()))"));
        System.out.println("测试用例8: \"({[()]})\" -> " + isValid("({[()]})"));
    }
}
