package com.serain.exercise.niuke;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-08  16:39
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI107 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        Find find=new Find(n);
        int m=sc.nextInt();
        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2)->Long.compare(e1.w,e2.w));
        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int w=sc.nextInt();
            Edge edge=new Edge(u,v,w);
            queue.add(edge);
        }
        long ans=0;
        int count=0;
        while(count<n-1){
            Edge edge=queue.poll();
            if(find.find(edge.u)!=find.find(edge.v)){
                find.union(edge.u,edge.v);
                ans+=edge.w;
                count++;
            }
        }
        if(count!=n-1){
            System.out.println("NO");
        }else{
            System.out.println(ans);
        }
    }
}
class Edge{
    public int u;
    public int v;
    public long w;
    public Edge(int u,int v,long w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
}
class Find{
    public int[] parent;
    public Find(int n){
        parent=new int[n+1];;
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x,int y){
        int rootX=find(x);
        int rootY=find(y);
        if(rootX!=rootY){
            parent[rootX]=rootY;
        }
    }
}
