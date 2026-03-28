package com.serain.exam.meituan;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.meituan
 * @Author: Serain
 * @CreateTime: 2026-03-28  09:46
 * @Description: TODO
 * @Version: 1.0
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        while (T-- > 0) {
            int n=sc.nextInt(),a=sc.nextInt(),b=sc.nextInt(),k=sc.nextInt();
            int[] arr=new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=sc.nextInt();
            }
            Arrays.sort(arr);
            long ans=0;
            int pos=arr.length-1;
            for(int i=0;i<a;i++){
                if(pos<0) break;
                if(arr[pos]<0) break;
                ans+=arr[pos--]/2;
            }
            for(int i=0;i<pos;i++){
                ans+=arr[i];
            }
            System.out.println(ans-(long)b*k);
        }
    }
}
