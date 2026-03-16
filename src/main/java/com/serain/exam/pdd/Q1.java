package com.serain.exam.pdd;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.study.exam
 * @Author: Serain
 * @CreateTime: 2026-03-15  15:04
 * @Description: TODO
 * @Version: 1.0
 */

class Content{
    int index;
    int u;
    int a;
    int b;
    int t;
    Content(int index,int u,int a,int b,int t){
        this.index=index;
        this.u=u;
        this.a=a;
        this.b=b;
        this.t=t;
    }
}
public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n= in.nextInt();
        int q= in.nextInt();
        List<Content> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            int u=in.nextInt();
            int a=in.nextInt();
            int b=in.nextInt();
            int t=in.nextInt();
            list.add(new Content(i+1,u,a,b,t));
        }
        Collections.sort(list,(o1, o2) -> {
            if(o1.a!=o2.a){
                return o1.a-o2.a;
            }
            if(o1.b!=o2.b){
                return o1.b-o2.b;
            }
            if(o1.t!=o2.t){
                return o2.t-o1.t;
            }
            return o2.u-o1.u;
        });
        for(int i = 0;i<n;i++){
            Content content=list.get(i);
            System.out.println(content.index+" "+content.u + " " + content.a + " " + content.b + " " + content.t);
        }
        Map<Integer,Boolean> map1=new HashMap<>();
        Map<Integer,Integer> map2=new HashMap<>();
        int pos=1;
        for(int i=0;i<n;i++){
            Content content=list.get(i);
            if(!map1.containsKey(content.u)){
                map1.put(content.u,true);
                map2.put(content.index,pos);
                pos++;
            }
        }
        for(int i=0;i<q;i++){
            int id = in.nextInt();
            if(map2.containsKey(id)){
                System.out.println(map2.get(id));
            }else{
                System.out.println(0);
            }
        }
    }
}
