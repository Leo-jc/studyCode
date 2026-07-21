package com.serain.singleweekgame.game492;

public class Q4 {
    public long minCost(String s, int encCost, int flatCost) {
        int n = s.length();
        int[] sum = new int[n+1];
        sum[0]=0;
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (s.charAt(i-1)=='1'?1:0);
        }
        return getMinCost(s,0,n-1,sum, encCost, flatCost);
    }

    private long getMinCost(String s, int left, int right, int[] sum, int encCost, int flatCost) {
        if (left == right) {
            return s.charAt(left) == '1' ? encCost : flatCost;
        }
        int lap = (right - left + 1);
        long res = flatCost;
        if(sum[right+1]-sum[left]!=0){
            res=(long) lap *(sum[right+1]-sum[left])*encCost;
        }
        long newRes= Long.MAX_VALUE;
        if(lap % 2 == 0){
            int mid = (left + right) / 2;
            newRes= getMinCost(s, left, mid, sum, encCost, flatCost) +
                    getMinCost(s, mid + 1, right, sum, encCost, flatCost);
        }
        return Math.min(res, newRes);
    }
}
