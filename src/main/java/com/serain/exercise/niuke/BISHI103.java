package com.serain.exercise.niuke;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-08  16:21
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI103 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int w=sc.nextInt();
        int[] c=new int[n+1];
        int[] d=new int[n+1];
        for(int i=1;i<=n;i++){
            c[i]=sc.nextInt();
            d[i]=sc.nextInt();
        }
        UFind uFind=new UFind(n,c,d);
        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            uFind.union(u,v);
        }
        TreeSet<Integer> set=new TreeSet<>();
        int[] dp=new int[w+1];
        for(int i=1;i<=n;i++){
            int u=uFind.find(i);
            if(set.contains(u)) continue;
            set.add(u);
            for(int j=w;j>=uFind.cost[u];j--){
                dp[j]=Math.max(dp[j],dp[j-uFind.cost[u]]+uFind.value[u]);
            }
        }
        System.out.println(dp[w]);
    }
}

class UFind{
    public int[] parent;
    public int[] cost;
    public int[] value;
    public UFind(int n,int[] c,int[] d){
        parent=new int[n+1];
        cost=new int[n+1];
        value=new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
            cost[i]=c[i];
            value[i]=d[i];
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
            cost[rootY]+=cost[rootX];
            value[rootY]+=value[rootX];
        }
    }
}
