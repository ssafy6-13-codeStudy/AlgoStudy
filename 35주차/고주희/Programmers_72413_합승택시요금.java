/*
1. 같이 가는 거리 + A가 가는 거리 + B가 가는 거리 를 구하라는 것
1-1. 시작점 -> x 지점까지 최소 거리를 구하고 x지점에서 A와 B에 가는 최소 거리를 계속 구한다?
1-2. 걍 A로 가는 최소 거리를 구하고 B로 가는 최소 거리도 따로 구한다? -> 같이 가는 거리 요금은 반띵해야 해서 안됨
*/

import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(map[i], 987654321);
            map[i][i] = 0;
        }
        for(int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int weight = fare[2];
            map[start][end] = weight;
            map[end][start] = weight;
        }
//         return go_together(s, a, b, map);
        return floid(s, a, b, map);

    }
    
    
    static public int floid(int s, int a, int b, int[][] map) {
        
        int m = map.length;
        
        for(int k = 1; k < m; k++) {
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < m; j++) {
                    // if(map[i][k] == 987654321 || map[k][j] == 987654321) continue;
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        
        int cost = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            // int common = dist[s][i];
            // int A = dist[i][a];
            // int B = dist[i][b];
            int tmp = map[s][i] + map[i][a] + map[i][b];
            if (cost > tmp && tmp >= 0) {
                cost = tmp;
            }
        }
        return cost;
    }
    
    
    
    static public int go_together(int s, int a, int b, int[][] map) {
        
        int m = map.length;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < m; i++) {
            // if(i == s) continue;
            
            int common = dijkstra(s, i, map);
//             int[] dist = dijkstra(i, map);
            int A = dijkstra(i, a, map);
            int B = dijkstra(i, b, map);
//             int A = dist[a];
//             int B = dist[b];
            
            // System.out.println("node " + i +"까지 같이 " + common + " A : "+A+"// B : "+B);
            if(min > common + A + B && common + A + B >= 0) min = common + A + B;
        }
        
        return min;
    }
    
    static public int dijkstra(int start, int end, int[][] map) {
        
        int m = map.length;
        int[] dist = new int[m];
        for(int i = 0; i < m; i++) {
            dist[i] = map[start][i];
        }
        
        boolean[] v = new boolean[m];
        for(int i = 1; i < m; i++) {
            int n = find_smallest_node(dist, v);
            if(n == -1) return dist[end];
            v[n] = true;
            for (int j = 0; j < m; j++) {
                if(dist[j] > dist[n] + map[n][j]) {
                    dist[j] = dist[n] + map[n][j];
                }
            }
        }
        return dist[end];
        
    }
    
    static public int find_smallest_node(int[] dist, boolean[] v) {
        
        int m = dist.length;
        int min = Integer.MAX_VALUE;
        int node = -1;
        
        for (int i = 0; i < m; i++) {
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

dijstra로 한 경우
정확성
테스트 1 〉	통과 (0.08ms, 76.4MB)
테스트 2 〉	통과 (0.11ms, 75.5MB)
테스트 3 〉	통과 (0.13ms, 78.5MB)
테스트 4 〉	통과 (0.25ms, 73.2MB)
테스트 5 〉	통과 (0.55ms, 75.7MB)
테스트 6 〉	통과 (0.48ms, 73.5MB)
테스트 7 〉	통과 (0.48ms, 77.4MB)
테스트 8 〉	통과 (0.71ms, 79.4MB)
테스트 9 〉	통과 (0.81ms, 77.1MB)
테스트 10 〉	통과 (1.16ms, 73.1MB)
효율성
테스트 1 〉	통과 (47.34ms, 52.9MB)
테스트 2 〉	통과 (58.70ms, 54MB)
테스트 3 〉	통과 (77.40ms, 53.7MB)
테스트 4 〉	통과 (76.29ms, 53.6MB)
테스트 5 〉	통과 (77.01ms, 53.3MB)
테스트 6 〉	통과 (74.91ms, 53MB)
테스트 7 〉	통과 (84.97ms, 61.6MB)
테스트 8 〉	통과 (94.99ms, 66.1MB)
테스트 9 〉	통과 (87.82ms, 64.8MB)
테스트 10 〉	통과 (81.03ms, 64.8MB)
테스트 11 〉	통과 (73.55ms, 64.7MB)
테스트 12 〉	통과 (93.06ms, 59.7MB)
테스트 13 〉	통과 (98.70ms, 60.6MB)
테스트 14 〉	통과 (243.69ms, 76.4MB)
테스트 15 〉	통과 (85.48ms, 57.7MB)
테스트 16 〉	통과 (79.25ms, 53.2MB)
테스트 17 〉	통과 (77.65ms, 53.4MB)
테스트 18 〉	통과 (74.72ms, 53.5MB)
테스트 19 〉	통과 (74.01ms, 53.6MB)
테스트 20 〉	통과 (80.01ms, 54.4MB)
테스트 21 〉	통과 (82.72ms, 53.8MB)
테스트 22 〉	통과 (137.66ms, 60.4MB)
테스트 23 〉	통과 (98.24ms, 58.2MB)
테스트 24 〉	통과 (128.66ms, 63MB)
테스트 25 〉	통과 (69.78ms, 53.1MB)
테스트 26 〉	통과 (68.84ms, 53.4MB)
테스트 27 〉	통과 (77.78ms, 53.8MB)
테스트 28 〉	통과 (78.63ms, 54MB)
테스트 29 〉	통과 (44.27ms, 52.7MB)
테스트 30 〉	통과 (36.12ms, 53MB)


floid로 한 경우
정확성
테스트 1 〉	통과 (0.05ms, 72MB)
테스트 2 〉	통과 (0.07ms, 71.2MB)
테스트 3 〉	통과 (0.05ms, 75.2MB)
테스트 4 〉	통과 (0.09ms, 77.4MB)
테스트 5 〉	통과 (0.11ms, 73.4MB)
테스트 6 〉	통과 (0.13ms, 74.3MB)
테스트 7 〉	통과 (0.10ms, 74.9MB)
테스트 8 〉	통과 (0.17ms, 71.2MB)
테스트 9 〉	통과 (0.35ms, 79.1MB)
테스트 10 〉	통과 (0.29ms, 76.9MB)
효율성
테스트 1 〉	통과 (23.76ms, 65.5MB)
테스트 2 〉	통과 (41.66ms, 57.3MB)
테스트 3 〉	통과 (30.48ms, 52.8MB)
테스트 4 〉	통과 (27.83ms, 52.5MB)
테스트 5 〉	통과 (28.39ms, 53MB)
테스트 6 〉	통과 (26.61ms, 52.3MB)
테스트 7 〉	통과 (48.48ms, 63.9MB)
테스트 8 〉	통과 (65.47ms, 65.5MB)
테스트 9 〉	통과 (42.65ms, 64.3MB)
테스트 10 〉	통과 (30.50ms, 61MB)
테스트 11 〉	통과 (50.58ms, 61.8MB)
테스트 12 〉	통과 (54.10ms, 59.2MB)
테스트 13 〉	통과 (134.64ms, 64.7MB)
테스트 14 〉	통과 (52.27ms, 59.8MB)
테스트 15 〉	통과 (64.44ms, 59.5MB)
테스트 16 〉	통과 (36.56ms, 52.3MB)
테스트 17 〉	통과 (28.67ms, 52.7MB)
테스트 18 〉	통과 (27.81ms, 52.4MB)
테스트 19 〉	통과 (29.20ms, 52.9MB)
테스트 20 〉	통과 (56.90ms, 57.2MB)
테스트 21 〉	통과 (27.95ms, 53.5MB)
테스트 22 〉	통과 (55.99ms, 59.4MB)
테스트 23 〉	통과 (49.97ms, 57.5MB)
테스트 24 〉	통과 (43.89ms, 56.3MB)
테스트 25 〉	통과 (34.87ms, 52.6MB)
테스트 26 〉	통과 (131.24ms, 70.2MB)
테스트 27 〉	통과 (32.56ms, 53.2MB)
테스트 28 〉	통과 (52.67ms, 57.1MB)
테스트 29 〉	통과 (20.46ms, 68.6MB)
테스트 30 〉	통과 (17.40ms, 52.2MB)
*/
