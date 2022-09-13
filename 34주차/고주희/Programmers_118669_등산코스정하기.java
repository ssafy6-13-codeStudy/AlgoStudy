import java.util.*;
// 생각 : gate -> summit 까지 경로만 찾으면 그대로 내려올 수 있지 않나?
// 이유 : gate -> summit 보다 itensity가 낮은 summit -> gate가 존재하더라도 최대 itensity를 찾아야 하기 때문에 결국 gate->summit이 될 것이다.
class Solution {
    static int w = Integer.MAX_VALUE;
    static int node = -1;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] adj = new ArrayList[n + 1];
        boolean[] gate = new boolean[n+1];
        boolean[] summit = new boolean[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int weight = path[2];
            adj[start].add(new int[]{end, weight});
            adj[end].add(new int[]{start, weight});
        }
        for(int g : gates) {
            gate[g] = true;
        }
        for(int s : summits) {
            summit[s] = true;
        }
        
        for (int g : gates) {
                min_way(g, gate, summit, adj);

        }
        return new int[]{node, w};
    }
    static public void min_way(int start, boolean[] gate, boolean[] summit, List<int[]>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, 987654321);
        for(int[] i : adj[start]) {
            int node = i[0];
            int weight = i[1];
            dist[node] = weight;
        }
        boolean[] v = new boolean[n];
        for (int i = 1; i < n; i++) {
            int sn = find_smallest_node(dist, v);
            // System.out.println(sn);
            // if(sn == 0) continue;
            if(sn == -1) return;
            if(summit[sn]) {
                if(dist[sn] < w) {
                    // System.out.println(start+"에서 시작함!!\n"+node+"에서 "+sn +"으로 변경 : "+dist[sn]);
                    w = dist[sn];
                    node = sn;
                } else if(dist[sn] == w) {
                    // System.out.println(start+"에서 시작함!!\n"+node+"에서 "+sn +"으로 변경 : "+dist[sn]);
                    if(node > sn) node = sn;
                }
                return;
            }
            v[sn] = true;
            if(gate[sn]) continue;
            for (int[] tmp : adj[sn]) {
                int node = tmp[0];
                int weight = tmp[1];
                if(dist[node] > Math.max(weight, dist[sn])) {
                    dist[node] = Math.max(weight, dist[sn]);
                }
            }
        }
        return;
    }
    
    static public int find_smallest_node(int[] dist, boolean[] v) {
        int min = 987654321;
        int node = -1;
        int n = dist.length;
        for(int i = 1; i < n; i++) {
            if(v[i]) continue;
            if(dist[i] < min) {
                min = dist[i];
                node = i;
            }
        }
        return node;
    }
}
/*
+13점
테스트 1 〉	통과 (0.10ms, 76.8MB)
테스트 2 〉	통과 (0.08ms, 69.7MB)
테스트 3 〉	통과 (0.08ms, 70.7MB)
테스트 4 〉	통과 (0.05ms, 75MB)
테스트 5 〉	통과 (0.16ms, 72.8MB)
테스트 6 〉	통과 (0.40ms, 74.8MB)
테스트 7 〉	통과 (0.22ms, 79.6MB)
테스트 8 〉	통과 (0.23ms, 68.1MB)
테스트 9 〉	통과 (0.45ms, 73.7MB)
테스트 10 〉	통과 (1.15ms, 78.5MB)
테스트 11 〉	통과 (1.18ms, 75.6MB)
테스트 12 〉	통과 (1.31ms, 73.2MB)
테스트 13 〉	통과 (10.65ms, 88.3MB)
테스트 14 〉	통과 (25.62ms, 104MB)
테스트 15 〉	통과 (84.28ms, 159MB)
테스트 16 〉	통과 (96.19ms, 162MB)
테스트 17 〉	통과 (114.25ms, 162MB)
테스트 18 〉	통과 (15.20ms, 101MB)
테스트 19 〉	통과 (30.53ms, 111MB)
테스트 20 〉	통과 (1284.38ms, 147MB)
테스트 21 〉	통과 (6298.57ms, 132MB)
테스트 22 〉	통과 (169.99ms, 137MB)
테스트 23 〉	통과 (806.34ms, 390MB)
테스트 24 〉	통과 (1730.30ms, 402MB)
테스트 25 〉	통과 (3014.69ms, 526MB)
*/
