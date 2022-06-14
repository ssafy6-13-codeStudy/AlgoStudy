import java.io.*;
import java.util.*;

public class Main {

    /*
    노드사이의 거리
    골드 5
    시간: 284ms
    메모리: 46760kb
     */

    static int N,M;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 점 점 거리
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b,d));
            list[b].add(new Node(a,d));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(bfs(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))+"\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int a, int b) {

        boolean []v = new boolean[N+1];
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(a,0));
        v[a] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();

            for (Node node : list[now.next]) {
                if(!v[node.next]) {
                    if(node.next==b) return now.d+node.d;
                    q.add(new Node(node.next,now.d+node.d));
                    v[node.next] = true;
                }
            }
        }

        return 0;
    }

    public static class Node{
        int next;
        int d;

        public Node(int next, int d) {
            this.next = next;
            this.d = d;
        }
    }
}
