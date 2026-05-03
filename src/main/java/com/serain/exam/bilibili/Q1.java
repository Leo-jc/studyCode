package com.serain.exam.bilibili;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.bilibili
 * @Author: Serain
 * @CreateTime: 2026-05-03  12:01
 * @Description: TODO
 * @Version: 1.0
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        TreeMap<Integer, Integer> map=new TreeMap<>();
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            map.put(x,map.getOrDefault(x,0)+1);
        }
        List<House> list=new ArrayList<>();
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            list.add(new House(a,b));
        }
        list.sort((a,b)-> {
            if(a.a==b.a)
                return a.b-b.b;
            return b.a-a.a;
        });
        long ans=0;
        for(House house:list){
            int a=house.a;
            int b=house.b;
            Integer id=map.higherKey(b);
            if(id!=null&&map.containsKey(id)){
                ans+=a;
                map.put(id,map.get(id)-1);
                if(map.get(id)==0){
                    map.remove(id);
                }
            }
        }
        System.out.println(ans);
    }
}

class House{
    int a;
    int b;
    House(int a,int b){
        this.a=a;
        this.b=b;
    }
}
