import java.util.*;

// 오른쪽 아래로만 움직인다.
// 집 -> 학교 최단경로 갯수
// m,n <= 100
// n*m
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        
        for(int[] p : puddles)
            dp[p[1]-1][p[0]-1] = -1;
        
        dp[0][0] = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(dp[i][j]==-1) continue;
                
                if(i!=0 && dp[i-1][j]!=-1)
                    dp[i][j] += dp[i-1][j];
                if(j!=0 && dp[i][j-1]!=-1)
                    dp[i][j] += dp[i][j-1];
                
                dp[i][j] %= 1_000_000_007;
            }
        }
        
        return dp[n-1][m-1];
    }
}

/*
+6
정확성  테스트
테스트 1 〉	통과 (0.03ms, 77.2MB)
테스트 2 〉	통과 (0.02ms, 75.3MB)
테스트 3 〉	통과 (0.04ms, 78.9MB)
테스트 4 〉	통과 (0.03ms, 75.6MB)
테스트 5 〉	통과 (0.04ms, 71.1MB)
테스트 6 〉	통과 (0.04ms, 80.1MB)
테스트 7 〉	통과 (0.03ms, 78.5MB)
테스트 8 〉	통과 (0.07ms, 66.1MB)
테스트 9 〉	통과 (0.03ms, 71.7MB)
테스트 10 〉	통과 (0.03ms, 72.6MB)
효율성  테스트
테스트 1 〉	통과 (1.20ms, 51.3MB)
테스트 2 〉	통과 (0.30ms, 52.2MB)
테스트 3 〉	통과 (0.48ms, 52MB)
테스트 4 〉	통과 (0.83ms, 51.8MB)
테스트 5 〉	통과 (0.73ms, 51.8MB)
테스트 6 〉	통과 (1.23ms, 51.8MB)
테스트 7 〉	통과 (0.58ms, 52MB)
테스트 8 〉	통과 (0.94ms, 52.2MB)
테스트 9 〉	통과 (3.05ms, 52.2MB)
테스트 10 〉	통과 (0.58ms, 52.6MB)
 */