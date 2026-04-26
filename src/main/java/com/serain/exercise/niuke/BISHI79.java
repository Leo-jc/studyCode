package com.serain.exercise.niuke;

import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-26  18:39
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI79 {
    public static int[][] maxValue;
    public static boolean[][] visited;
    public static int[][] dicts=new int[][]{{0,2},{0,-2},{2,0},{-2,0}};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            int[][] a=new int[n][m];
            maxValue=new int[n][m];
            visited=new boolean[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    a[i][j]=in.nextInt();
                    maxValue[i][j]=Integer.MIN_VALUE;
                }
            }
            int ans=Integer.MIN_VALUE;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    visited=new boolean[n][m];
                    ans=Math.max(ans,dfs(a,i,j,n,m));
                }
            }
            System.out.println(ans);
        }
    }

    public static int dfs(int[][] a,int x,int y,int n,int m){
        if(maxValue[x][y]!=Integer.MIN_VALUE){
            return maxValue[x][y];
        }
        
        visited[x][y]=true;
        int tempMax=a[x][y];
        
        for(int i=0;i<4;i++){
            int newX=x+dicts[i][0];
            int newY=y+dicts[i][1];
            if(newX<0||newY<0||newX>=n||newY>=m||visited[newX][newY]) continue;
            
            int nextVal=dfs(a,newX,newY,n,m);
            tempMax=Math.max(tempMax,a[x][y]+nextVal);
        }
        
        visited[x][y]=false;
        maxValue[x][y]=tempMax;
        return tempMax;
    }
}
