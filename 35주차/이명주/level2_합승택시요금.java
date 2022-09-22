import java.util.*;

class Solution {
    
    // 중간까지 같이 갔다가 나중에 헤어질수도 있음
    
    // n 지점 수 s 출발
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] dist = new int[n + 1][n + 1];
        
        // 초기화
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                dist[i][j] = 99999999;
            }
            dist[i][i] = 0;
        }
        
        // 경로 입력
        for(int i = 0; i < fares.length; i++) {
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        // 플로이드 워셜 
        for(int k = 1; k < n + 1; k++) {
            for(int i = 1; i < n + 1; i++) {
                for(int j = 1; j < n + 1; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        // s -> i 
        //        -> a
        //        -> b
        for(int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
}

/*

정확성  테스트
테스트 1 〉	통과 (0.04ms, 78.8MB)
테스트 2 〉	통과 (0.06ms, 73.9MB)
테스트 3 〉	통과 (0.04ms, 74.5MB)
테스트 4 〉	통과 (0.07ms, 70.2MB)
테스트 5 〉	통과 (0.13ms, 77.1MB)
테스트 6 〉	통과 (0.16ms, 79.3MB)
테스트 7 〉	통과 (0.10ms, 74MB)
테스트 8 〉	통과 (0.19ms, 77MB)
테스트 9 〉	통과 (0.28ms, 76.2MB)
테스트 10 〉	통과 (0.33ms, 77.1MB)
효율성  테스트
테스트 1 〉	통과 (14.50ms, 52.1MB)
테스트 2 〉	통과 (20.16ms, 53.1MB)
테스트 3 〉	통과 (27.65ms, 52.6MB)
테스트 4 〉	통과 (27.86ms, 52.2MB)
테스트 5 〉	통과 (35.76ms, 52.1MB)
테스트 6 〉	통과 (33.40ms, 52.8MB)
테스트 7 〉	통과 (44.59ms, 60.6MB)
테스트 8 〉	통과 (28.71ms, 62.7MB)
테스트 9 〉	통과 (35.20ms, 64.4MB)
테스트 10 〉	통과 (33.90ms, 65MB)
테스트 11 〉	통과 (44.01ms, 61MB)
테스트 12 〉	통과 (35.44ms, 60.4MB)
테스트 13 〉	통과 (40.84ms, 56.6MB)
테스트 14 〉	통과 (78.68ms, 62.4MB)
테스트 15 〉	통과 (30.11ms, 57.3MB)
테스트 16 〉	통과 (130.36ms, 70.6MB)
테스트 17 〉	통과 (26.31ms, 53.5MB)
테스트 18 〉	통과 (26.28ms, 52.9MB)
테스트 19 〉	통과 (28.95ms, 53.1MB)
테스트 20 〉	통과 (32.42ms, 57.1MB)
테스트 21 〉	통과 (55.31ms, 56.9MB)
테스트 22 〉	통과 (51.88ms, 57.4MB)
테스트 23 〉	통과 (40.97ms, 60.5MB)
테스트 24 〉	통과 (66.10ms, 60MB)
테스트 25 〉	통과 (24.66ms, 52.6MB)
테스트 26 〉	통과 (25.77ms, 52.9MB)
테스트 27 〉	통과 (33.79ms, 53.5MB)
테스트 28 〉	통과 (25.18ms, 53.1MB)
테스트 29 〉	통과 (22.42ms, 69.2MB)
테스트 30 〉	통과 (10.45ms, 52.8MB)
 */