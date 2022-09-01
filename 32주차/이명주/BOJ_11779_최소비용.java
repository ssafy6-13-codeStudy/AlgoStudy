import java.io.*;
import java.util.*;

public class Main {

    /*
    최소비용 구하기2
    골드3
     */

    static int n,m;
    static ArrayList<Node>[] graph;
    static int start, dest;
    static int[] dist, route;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1]; // 간선
        dist = new int[n+1]; // 다익스트라
        route = new int[n+1]; // 경로

        for (int i = 0; i <= n; i++) {
          graph[i] = new ArrayList<>();
          dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,cost));
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        dijkstra(start);

        ArrayList<Integer> path = new ArrayList<>();

        int n = dest;
        while(n != 0) {
            path.add(n);
            n = route[n];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[dest]+"\n");
        sb.append(path.size()+"\n");

        for (int i = path.size()-1; i >= 0 ; i--) {
            sb.append(path.get(i)+" ");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(start, 0));

        dist[start] = 0;
        route[start] = 0;

        while(!q.isEmpty()) {
            Node node = q.poll();
            int cur = node.to;

            for (Node next : graph[cur]) {

                int to = next.to;
                int cost = next.cost;

                if(dist[to] > dist[cur] + cost) {
                    dist[to] = dist[cur] + cost;

                    route[to] = cur;
                    q.add(new Node(to,dist[to]));
                }

            }
        }

    }

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }


}