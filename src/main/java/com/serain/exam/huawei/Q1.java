package com.serain.exam.huawei;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.huawei
 * @Author: Serain
 * @CreateTime: 2026-04-28  23:24
 * @Description: TODO
 * @Version: 1.0
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        Map<String,Integer> map = new HashMap<>();
        for(int i=1;i<=N;i++){
            int k=sc.nextInt();
            Set<Integer> set=new HashSet<>();
            List<Integer> list=new ArrayList<>();
            for(int j=1;j<=k;j++){
                int id=sc.nextInt();
                if(set.contains(id)) continue;
                set.add(id);
                list.add(id);
            }
            list.sort((a,b)->a-b);
            for(int m=0;m<list.size();m++){
                for(int n=m+1;n<list.size();n++){
                    String key=list.get(m)+","+list.get(n);
                    map.put(key,map.getOrDefault(key,0)+1);
                }
            }
        }
        List<Integer> list=new ArrayList<>();
        for(Integer num:map.values()){
            list.add(num);
        }
        list.sort((a,b)->b-a);
        for(int i=0;i<Q;i++){
            int T=sc.nextInt();
            int count=0;
            for (Integer integer : list) {
                if (integer < T) break;
                count++;
            }
            System.out.println(count);
        }
    }
}
