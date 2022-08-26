import java.io.*;
import java.util.*;

public class Main {

  /*
    벼락치기
    시간 : 112ms
    메모리 : 15886kb
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] dp = new int[101][10001];
        int[][] arr = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int t = 1; t <= T; t++) {
                int time = arr[i][0];
                int score = arr[i][1];

                if (t - time >= 0) {
                    dp[i][t] = Math.max(dp[i - 1][t], dp[i - 1][t - time] + score);
                } else {
                    dp[i][t] = dp[i - 1][t];
                }
            }
        }

        System.out.println(dp[N][T]);

    }
}