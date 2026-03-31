package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-31  12:19
 * @Description: TODO
 * @Version: 1.0
 */
public class E3474 {
    public String generateString(String str1, String str2) {
        char[] chars = new char[str1.length() + str2.length()];
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i)=='T'){
                for(int j=i;j<i+str2.length();j++){
                    if(chars[j]!=0&&chars[j]!=str2.charAt(j-i)) return "";
                    chars[j]=str2.charAt(j-i);
                }
            }
        }
        char[] oldChars = chars.clone();
        for(int i=0;i<chars.length;i++) if(chars[i]==0) chars[i]='a';
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)=='F') {
                if (!new String(chars, i, str2.length()).equals(str2)) continue;
                boolean flag = true;
                for(int j=i+str2.length();j>=i;j--){
                    if(oldChars[j]==0) {
                        chars[j]='b';
                        flag = false;
                        break;
                    }
                }
                if(flag) return "";
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(new E3474().generateString("abc", "def"));
    }
}
