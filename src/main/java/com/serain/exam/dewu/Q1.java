package com.serain.exam.dewu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.dewu
 * @Author: Serain
 * @CreateTime: 2026-04-26  18:47
 * @Description: TODO
 * @Version: 1.0
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int pos=0;
        int flag=1;
        while (k>0){
            k--;
            int a=sc.nextInt();
            a=a%list.size();
            while(a>0){
                a--;
                pos=(pos+flag+list.size())%(list.size());
            }
            System.out.print(list.get(pos)+1+" ");
            list.remove(pos);
            flag*=-1;
            if(flag==-1) pos=(pos-1+list.size())%list.size();
        }
    }
}
