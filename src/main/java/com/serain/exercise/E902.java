package com.serain.exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise
 * @Author: Serain
 * @CreateTime: 2026-03-16  12:10
 * @Description: TODO
 * @Version: 1.0
 */
public class E902 {
    private String[] digits;
    private int[] dp;
    private char[] chars;
    public int atMostNGivenDigitSet(String[] digits, int n) {
        chars=String.valueOf(n).toCharArray();
        this.digits=digits;
        dp=new int[chars.length+1];
        Arrays.fill(dp, -1);
        return dfs(0,false,false);
    }

    private int dfs(int pos, boolean isLimit, boolean isNum) {
        if(pos==chars.length) return isNum?1:0;
        if(!isLimit && isNum && dp[pos]!=-1) return dp[pos];
        int res=0;
        if(!isNum) res=dfs(pos+1,false,false);
        char up=isLimit?chars[pos]:'9';
        for (String digit : digits) {
            if (digit.charAt(0) > up) break;
            res += dfs(pos + 1, isLimit && digit.charAt(0) == up, true);
        }
        if(!isLimit&&isNum) dp[pos]=res;
        return res;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int n= in.nextInt();
        while(n-->0){
            String input = in.next();
            switch (input){
                case "push":
                    int x=in.nextInt();
                    stack.push(x);
                    break;
                case "pop":
                    if(stack.size()==0){
                        System.out.println("Empty");
                    }else{
                        stack.pop();
                    }
                    break;
                case "query":
                    if(stack.size()==0){
                        System.out.println("Empty");
                    }else{
                        System.out.println(stack.peek());
                    }
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
            }
        }
    }
}
