import java.io.*;
import java.util.*;

public class Main {

    /*
    평범한 배낭
    시간 : 152ms
    메모리 : 51348kb
     */

    static int N,K;
    static int[][] item;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        item = new int[N+1][2];
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // i번 아이템을 넣을 수 있는 무게라면
                if(j - item[i][0] >= 0)
                    dp[i][j] = Math.max(dp[i-1][j], item[i][1]+dp[i-1][j-item[i][0]]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);
    }


}