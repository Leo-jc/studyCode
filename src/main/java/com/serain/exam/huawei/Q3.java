package com.serain.exam.huawei;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.huawei
 * @Author: Serain
 * @CreateTime: 2026-04-28  23:24
 * @Description: TODO
 * @Version: 1.0
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<=1){
            System.out.println(-1);
            return;
        }
        List<PlotNode> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new PlotNode(x,y));
        }
        double ans=Double.MAX_VALUE;
        points.sort((p1,p2) -> {
            if(p1.x==p2.x){
                return Integer.compare(p1.y,p2.y);
            }
            return Integer.compare(p1.x,p2.x);
        });
        int k=(int)Math.sqrt(n)/2;
        for(int i=0;i<points.size()-k;i++){
            int x1=points.get(i).x;
            int y1=points.get(i).y;
            for(int j=1;j<=k;j++){
                int x2=points.get(i+j).x;
                int y2=points.get(i+j).y;
                double distance=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                ans=Math.min(ans,distance);
            }
        }
        points.sort((p1,p2)->{
            if(p1.y==p2.y){
                return Integer.compare(p1.x,p2.x);
            }
            return Integer.compare(p1.y,p2.y);
        });
        for(int i=0;i<points.size()-k;i++){
            int x1=points.get(i).x;
            int y1=points.get(i).y;
            for(int j=1;j<=k;j++){
                int x2=points.get(i+j).x;
                int y2=points.get(i+j).y;
                double distance=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                ans=Math.min(ans,distance);
            }
        }
        System.out.println((int)ans);
    }
}

class PlotNode{
    int x;
    int y;
    PlotNode(int x,int y){
        this.x=x;
        this.y=y;
    }
}
