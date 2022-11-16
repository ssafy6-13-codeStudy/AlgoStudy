import java.util.*;
import java.io.*;

// 각 장은 각각 다른 파일에 저장
// 페이지가 연속이 되도록 합쳐야 한다.
// 두개의 파일 합쳐서 하나의 임시파일 -> 최종 하나
// 최종 파일을 완성하는데 필요한 비용의 총 합

// dp[i][j] = i페이지~j페이지를 합친 최소비용
// 최종으로 dp[0][k-1] 구하기
// dp[i][j] = dp[i][i] + dp[i+1][j]
// dp[i][i+1] + dp[i+2][j] 의 최소값
/*
33960kb
1156ms
 */
class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int k = Integer.parseInt(br.readLine());

            int[][] dp = new int[k+1][k+1];
            int[] arr = new int[k+1];
            int[] sum = new int[k+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + arr[i];
            }

            for (int i = 1; i < k ; i++) {
                dp[i][i+1] = arr[i] + arr[i+1];
            }

            // i 페이지만큼 합치기
            for (int p = 2; p < k; p++) {
                for (int i = 1; i <= k - p; i++) {
                    int to = i + p;
                    dp[i][to] = Integer.MAX_VALUE;
                    for (int j = i; j < to; j++) {
                        dp[i][to] = Math.min(dp[i][j]+dp[j+1][to]+sum[to]-sum[i-1],dp[i][to]);
                    }
                }
            }
            sb.append(dp[1][k]+"\n");
        }
        System.out.println(sb);
    }
}