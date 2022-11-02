import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] is : edge) {
            list[is[0]-1].add(is[1]-1);
            list[is[1]-1].add(is[0]-1);
        }
        
        int answer = dijkstra(list);
        return answer;
    }
    
    public static int dijkstra(List<Integer>[] list) {
        
        int N = list.length;
        int[] dist = new int[N];
        Arrays.fill(dist, 987654321);
        for (Integer i : list[0]) {
            dist[i] = 1;
        }
        dist[0] = 0;
        boolean[] v = new boolean[N];
        v[0] = true;
        int max = 0;
        for (int i = 0; i < N - 1; i++) {
            int n = smallest_node(dist, v);
            v[n] = true;
            for (Integer j : list[n]) {
                if(dist[j] > dist[n] + 1) {
                    dist[j] = dist[n] + 1;
                    max = Math.max(dist[j], max);
                }
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(dist[i] == max) {
                // System.out.println(i);
                cnt++;
            }
        }
        // System.out.println(Arrays.toString(dist));
        return cnt;
    }
    
    public static int smallest_node(int[] dist, boolean[] v) {
        
        int N = dist.length;
        int min = 987654321;
        int node = -1;
        for (int i = 0; i < N; i++) {
            if(v[i]) continue;
            if(min > dist[i]) {
                min = dist[i];
                node = i;
            }
        }
        return node;
    }
}
/*
+2
테스트 1 〉	통과 (0.10ms, 74.3MB)
테스트 2 〉	통과 (0.11ms, 79.3MB)
테스트 3 〉	통과 (0.23ms, 74.5MB)
테스트 4 〉	통과 (1.06ms, 73MB)
테스트 5 〉	통과 (5.71ms, 75.6MB)
테스트 6 〉	통과 (15.93ms, 94.6MB)
테스트 7 〉	통과 (298.12ms, 93.1MB)
테스트 8 〉	통과 (892.50ms, 115MB)
테스트 9 〉	통과 (522.70ms, 100MB)
*/
