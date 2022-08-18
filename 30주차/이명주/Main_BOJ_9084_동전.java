import java.io.*;
import java.util.*;

public class Main {

  /*
    동전
    시간 : 80ms
    메모리 : 11876kb
     */
  public static void main(String[] args) throws Exception {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int T = Integer.parseInt(br.readLine());
      
      StringBuilder sb = new StringBuilder();
      
      for (int t = 0; t < T; t++) {
          int n = Integer.parseInt(br.readLine());
          StringTokenizer st = new StringTokenizer(br.readLine());
          
          int[] coins = new int[n+1];
          
          for (int i = 1; i <= n; i++) {
              coins[i] = Integer.parseInt(st.nextToken());
          }
          
          int m = Integer.parseInt(br.readLine());
          
          int[] dp = new int[m+1];
          
          for (int i = 1; i <= n; i++) {
              for (int j = 1; j <=m; j++) {
                  if (j - coins[i] > 0) {
                      dp[j] = dp[j] + dp[j-coins[i]];
                  } else if (j - coins[i] == 0) {
                      dp[j]++;
                  }
              }
          }
          sb.append(dp[m] + "\n");
      }
      System.out.print(sb);
  }
}