package com.serain.exam.huawei;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.huawei
 * @Author: Serain
 * @CreateTime: 2026-05-18  10:56
 * @Description: 华为笔试笔试题
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5};
        int target=6;
        System.out.println(getMinLength(arr,target));

    }
    public static int getMinLength(int[] arr,int target){
        int n=arr.length;
        int ans=Integer.MAX_VALUE;
        int l=0;
        int r=0;
        int sum=0;
        while(l<n){
            if(r<n){
                sum+=arr[r];
                r++;
            }
            while(l<n&&sum>=target){
                ans=Math.min(ans,r-l);
                sum-=arr[l];
                l++;
            }
            if(r==n&&sum<target){break;}
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }
}
