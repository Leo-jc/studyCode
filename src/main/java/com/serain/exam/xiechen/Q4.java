package com.serain.exam.xiechen;

import javax.imageio.metadata.IIOMetadataNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.xiechen
 * @Author: Serain
 * @CreateTime: 2026-04-12  09:57
 * @Description: TODO
 * @Version: 1.0
 */
public class Q4 {
    public static final int MOD=1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            getAns(n,m);

        }
    }

    private static void getAns(int n, int m) {
        Queue<Integer> q=new LinkedList<>();
        q.add(n);
        int oneNum=0;
        int twoNum=0;
        while(!q.isEmpty()){
            int size=q.size();
            twoNum=(2*twoNum+oneNum)%MOD;
            oneNum=2*oneNum%MOD;
            for (int i = 0; i < size; i++) {
                int cur=q.poll();
                if(cur/2!=0&&cur/2!=1){
                    q.add(cur/2+1);
                    q.add(cur/2+1);
                }else{
                    if(cur/2==1) twoNum+=2;
                    else oneNum+=2;
                }
                if(cur%2==1){
                    twoNum++;
                }
            }
        }
        System.out.println(oneNum+" "+twoNum);
    }

    public static int quickPow(int oneNum,int twoNum,int n){
        int[][] base={{2,1},{0,2}};
        int[][] res={{1,0},{0,1}};
        while(n>0){
            if(n%2==1){
                res[0][0]=res[0][0]*base[0][0]+res[0][1]*base[1][0];
                res[0][1]=res[0][0]*base[0][1]+res[0][1]*base[1][1];
                res[1][0]=res[1][0]*base[0][0]+res[1][1]*base[1][0];
                res[1][1]=res[1][0]*base[0][1]+res[1][1]*base[1][1];
            }
            n/=2;
            base[0][0]=base[0][0]*base[0][0]+base[0][1]*base[1][0];
            base[0][1]=base[0][0]*base[0][1]+base[0][1]*base[1][1];
            base[1][0]=base[1][0]*base[0][0]+base[1][1]*base[1][0];
            base[1][1]=base[1][0]*base[0][1]+base[1][1]*base[1][1];
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                System.out.println(res[i][j]);
            }
        }
        return (oneNum*res[0][0]+twoNum*res[1][0])+2*(oneNum*res[0][1]+twoNum*res[1][1]);
    }
}
