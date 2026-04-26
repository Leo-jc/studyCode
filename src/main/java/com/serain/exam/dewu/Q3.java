package com.serain.exam.dewu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.dewu
 * @Author: Serain
 * @CreateTime: 2026-04-26  18:47
 * @Description: TODO
 * @Version: 1.0
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int p=sc.nextInt();
        String s=sc.next();
        Map<Integer,Map<Character,Integer>> map=new HashMap<>();
        for(int i=0;i<n;i+=p){
            int j=i+p-1;
            comupte(s,i,j,map);
        }
        int m=n/p*2;
        int ans=0;
        for(int i=0;i<p/2+1;i++){
            Map<Character,Integer> map1=map.get(i);
            if(map1==null) continue;
            int max=0;
            for(char c:map1.keySet()){
                max=Math.max(max,map1.get(c));
            }
            ans+=m-max;
        }
        System.out.println(ans);
    }

    private static void comupte(String s, int l, int r, Map<Integer, Map<Character, Integer>> map) {
        int pos=0;
        while(l<=r){
            Map<Character,Integer> map1=map.getOrDefault(pos,new HashMap<>());
            map1.put(s.charAt(l),map1.getOrDefault(s.charAt(l),0)+1);
            map1.put(s.charAt(r),map1.getOrDefault(s.charAt(r),0)+1);
            map.put(pos,map1);
            pos++;
            l++;
            r--;
        }
    }
}
