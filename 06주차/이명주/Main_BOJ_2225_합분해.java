import java.io.*;
import java.util.*;

/*
    합분해
    골드 5
    시간 : 128ms
    메모리 : 12104kb
*/

public class Main {

    /*
    K개를 더해서 합이 N이 되는 경우의 수

    dp[K][N] = dp[K-1][N] +  ... + dp[K-1][0]

     */

    static int N,K;
    static long[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[K+1][N+1];

        for (int i = 0; i <= N ; i++) {
            dp[1][i] = 1; // 1개의 수를 더해서 i가 되는 경우 : 1
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 0; n <= N; n++) {
                for (int i = 0; i <= n; i++) {
                    dp[k][n] += dp[k-1][n-i];
                    dp[k][n] %= 1_000_000_000;
                }
            }
        }
        
        System.out.println(dp[K][N]);

    }
}
