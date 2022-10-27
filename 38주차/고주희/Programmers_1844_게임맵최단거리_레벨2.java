import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        int[] dr = {-1,0,1,0};
        int[] dc = {0,-1,0,1};
        
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{0,0,1});
        boolean[][] v = new boolean[N][M];
        v[0][0] = true;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            if(cur[0] == N-1 && cur[1] == M-1) {
                return cur[2];
            }
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr >= N || nc >= M || nr < 0 || nc < 0) continue;
                if(maps[nr][nc] == 0 || v[nr][nc]) continue;
                v[nr][nc] = true;
                que.offer(new int[]{nr, nc, cur[2] + 1});
            }
        }
        return -1;
    }
}

/*
+2
정확성  테스트
테스트 1 〉	통과 (0.18ms, 74.5MB)
테스트 2 〉	통과 (0.11ms, 70.9MB)
테스트 3 〉	통과 (0.20ms, 73.7MB)
테스트 4 〉	통과 (0.19ms, 78.7MB)
테스트 5 〉	통과 (0.14ms, 81.6MB)
테스트 6 〉	통과 (0.16ms, 72.9MB)
테스트 7 〉	통과 (0.21ms, 74.9MB)
테스트 8 〉	통과 (0.23ms, 80.7MB)
테스트 9 〉	통과 (0.16ms, 77.4MB)
테스트 10 〉	통과 (0.20ms, 81.2MB)
테스트 11 〉	통과 (0.19ms, 73.7MB)
테스트 12 〉	통과 (0.17ms, 76.6MB)
테스트 13 〉	통과 (0.14ms, 77.7MB)
테스트 14 〉	통과 (0.21ms, 71.8MB)
테스트 15 〉	통과 (0.18ms, 73.3MB)
테스트 16 〉	통과 (0.12ms, 75MB)
테스트 17 〉	통과 (0.19ms, 73.7MB)
테스트 18 〉	통과 (0.20ms, 80MB)
테스트 19 〉	통과 (0.12ms, 77MB)
테스트 20 〉	통과 (0.12ms, 70.3MB)
테스트 21 〉	통과 (0.16ms, 76.3MB)
효율성  테스트
테스트 1 〉	통과 (8.15ms, 56.2MB)
테스트 2 〉	통과 (5.79ms, 54MB)
테스트 3 〉	통과 (5.76ms, 53MB)
테스트 4 〉	통과 (5.92ms, 54.4MB)
*/
