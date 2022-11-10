import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        for(int[] i : puddles) {
            map[i[1] - 1][i[0] - 1] = -1;
        }
        map[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == -1) continue;
                if(i-1 >= 0 && map[i-1][j] != -1) {
                    map[i][j] += map[i-1][j];
                    map[i][j] %= 1000000007;
                }
                if(j-1 >= 0 && map[i][j-1] != -1) {
                    map[i][j] += map[i][j-1];
                    map[i][j] %= 1000000007;
                }
            }
        }
        return map[n-1][m-1];
    }
}

/*
+6
정확성  테스트
테스트 1 〉	통과 (0.02ms, 77.4MB)
테스트 2 〉	통과 (0.03ms, 75.7MB)
테스트 3 〉	통과 (0.04ms, 79.2MB)
테스트 4 〉	통과 (0.04ms, 81.6MB)
테스트 5 〉	통과 (0.05ms, 74.3MB)
테스트 6 〉	통과 (0.03ms, 74.7MB)
테스트 7 〉	통과 (0.05ms, 82.3MB)
테스트 8 〉	통과 (0.04ms, 72.9MB)
테스트 9 〉	통과 (0.03ms, 74MB)
테스트 10 〉	통과 (0.04ms, 80.2MB)
효율성  테스트
테스트 1 〉	통과 (0.71ms, 51.8MB)
테스트 2 〉	통과 (0.54ms, 52.3MB)
테스트 3 〉	통과 (0.64ms, 52.3MB)
테스트 4 〉	통과 (1.07ms, 52.3MB)
테스트 5 〉	통과 (0.58ms, 52.2MB)
테스트 6 〉	통과 (1.37ms, 52.2MB)
테스트 7 〉	통과 (0.70ms, 52.3MB)
테스트 8 〉	통과 (1.12ms, 51.5MB)
테스트 9 〉	통과 (1.28ms, 51.9MB)
테스트 10 〉	통과 (1.09ms, 51.7MB)
*/
