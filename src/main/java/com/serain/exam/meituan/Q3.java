package com.serain.exam.meituan;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.meituan
 * @Author: Serain
 * @CreateTime: 2026-03-28  09:46
 * @Description: TODO
 * @Version: 1.0
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] v=new int[n];
        for(int i=0;i<n;i++){
            v[i]=sc.nextInt();
        }
        int ans=0;
        int tempAns=0;
        for(int i=0;i<n;i++){
            if(i>0&&v[i]>=v[i-1]){
                tempAns--;
                ans+=tempAns;
                continue;
            }
            tempAns=0;
            int maxNum=v[i];
            for(int j=i+1;j<n;j++){
                maxNum=Math.max(maxNum,v[j]);
                if(v[i]+v[j]>maxNum){
                    tempAns++;
                }
            }
            ans+=tempAns;
        }
        System.out.println(ans);
    }
}
