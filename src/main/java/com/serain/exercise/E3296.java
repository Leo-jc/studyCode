package com.serain.exercise;

import java.util.*;

public class E3296 {
    public record Item(int index, long time){};
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int[] useTime = new int[workerTimes.length];
        long minCostTime = Long.MIN_VALUE;
        PriorityQueue<Item> queue = new PriorityQueue<>((a, b) -> Long.compare(a.time , b.time));
        for(int i = 0; i < workerTimes.length; i++){
            queue.add(new Item(i, workerTimes[i]));
            useTime[i] = 1;
        }
        while(mountainHeight > 0){
            mountainHeight--;
            Item item = queue.poll();
            System.out.println(item.index+","+item.time);
            minCostTime = Math.max(minCostTime, item.time);
            useTime[item.index]++;
            queue.add(new Item(item.index, item.time+(long)useTime[item.index]*workerTimes[item.index]));
        }
        return minCostTime;
    }

    public static void main(String[] args) {
        E3296 e3296 = new E3296();
        System.out.println(e3296.minNumberOfSeconds(100000, new int[]{1000000}));
    }
}
