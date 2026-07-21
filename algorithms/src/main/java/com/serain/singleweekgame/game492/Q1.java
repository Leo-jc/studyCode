package com.serain.singleweekgame.game492;

public class Q1 {
    public int minimumIndex(int[] capacity, int itemSize) {
        int pos = -1;
        int cap = Integer.MAX_VALUE;
        for(int i = 0; i < capacity.length; i++){
            if(capacity[i] >= itemSize && capacity[i] < cap){
                pos = i;
                cap = capacity[i];
            }
        }
        return pos;
    }
}
