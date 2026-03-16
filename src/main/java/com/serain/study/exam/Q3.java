package com.serain.study.exam;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.study.exam
 * @Author: Serain
 * @CreateTime: 2026-03-15  16:19
 * @Description: TODO
 * @Version: 1.0
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0){
            int n = in.nextInt();
            String s = in.next();
            int x=in.nextInt();
            int y=in.nextInt();
            Map<Integer, List<Integer>> map = new HashMap<>();
            int resultX=0;
            int resultY=0;
            for(int i=0;i<n;i++){
                char c = s.charAt(i);
                switch (c){
                    case 'U':
                        resultY++;
                        break;
                    case 'D':
                        resultY--;
                        break;
                    case 'L':
                        resultX--;
                        break;
                    case 'R':
                            resultX++;
                            break;
                }
                map.put(i, Arrays.asList(resultX,resultY));
            }
            int minResult=Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                for(int j=i;j<n;j++){
                    int newX=map.get(j).get(0)-map.get(i).get(0);
                    int newY=map.get(j).get(1)-map.get(i).get(1);
                    if(resultX-newX==x&&resultY-newY==y){
                        minResult=Math.min(minResult,j-i+1);
                    }
                }
            }
            if(minResult==Integer.MAX_VALUE){
                System.out.println(-1);
            } else{
                System.out.println(minResult);
            }
        }
    }
}
