package com.serain.exercise.leetcode.math;

/**
 * @BelongsProject: StudyCode
 * @BelongsPackage: com.serain.exercise.leetcode.math
 * @Author: Serain
 * @CreateTime: 2026-09-19  16:00
 * @Description: TODO
 * @Version: 1.0
 */
public class E1401 {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int[] radiusX=new int[]{xCenter-radius,xCenter+radius};
        int[] radiusY=new int[]{yCenter-radius,yCenter+radius};
        int[] x=new int[]{x1,x2};
        int[] y=new int[]{y1,y2};
        Boolean isX=false;
        Boolean isY=false;
        for(int i=0;i<2;i++){
            if(x[i]>=radiusX[0]&&x[i]<=radiusX[1]){
                isX=true;
            }
            if(y[i]>=radiusY[0]&&y[i]<=radiusY[1]){
                isY=true;
            }
        }
        if((radiusX[0]>=x[1]&& radiusX[1]<=x[0])||(radiusX[0]<=x[1]&& radiusX[1]>=x[0])){
            isX=true;
        }
        if((radiusY[0]>=y[1]&& radiusY[1]<=y[0])||(radiusY[0]<=y[1]&& radiusY[1]>=y[0])){
            isY=true;
        }
        return isX&isY;
    }
}
