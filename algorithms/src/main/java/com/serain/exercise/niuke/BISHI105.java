package com.serain.exercise.niuke;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class BISHI105 {
    static class Node{
        long dis;
        int u;
        public Node(int u,long dis) {
            this.dis = dis;
            this.u = u;
        }
    }
    public static void main(String[] args) {
        int n,m,s;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        Map<Integer, List<Node>> map = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->{
            return Math.toIntExact(o1.dis - o2.dis);
        });
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();
            List<Node> list = map.getOrDefault(u, new ArrayList<>());
            list.add(new Node(v,w));
            map.put(u, list);
        }
        long[] dis=new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dis, -1);
        dis[s]=0;
        queue.add(new Node(s,0));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(visited[node.u]) continue;
            visited[node.u]=true;
            for (Node V : map.getOrDefault(node.u, new ArrayList<>())) {
                if(visited[V.u]) continue;
                if(dis[V.u]==-1||dis[V.u]>dis[node.u]+V.dis){
                    dis[V.u]=dis[node.u]+V.dis;
                }
                queue.add(V);
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(dis[i]+" ");
        }
    }
}
