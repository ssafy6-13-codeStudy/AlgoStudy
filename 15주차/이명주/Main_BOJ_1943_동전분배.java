import java.util.*;
import java.io.*;

/*
    동전 분배
    골드 3
    시간 : 416ms
    메모리 : 15940kb
*/

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 3; t++) {

            int[] dp = new int[50001];
            int sum = 0;

            int n = Integer.parseInt(br.readLine());

            Coin[] coin = new Coin[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                coin[i] = new Coin(cnt,value);

                sum += cnt*value;

            }

            if(sum%2==1) {
                sb.append("0").append("\n");
                continue;
            }

            dp[0] = 1;

            for (int i = 0; i < n; i++) {
                int v = coin[i].value;
                int cnt = coin[i].cnt;

                for (int j= 50000; j >= v; j--) {
                    if(dp[j-v] ==1){
                        for (int k = 0; k < cnt && (j + k*v) <= 50000; k++) {
                            dp[j+k*v] = 1;
                        }
                    }
                }
            }
            sb.append(dp[sum/2]).append("\n");
        }

        System.out.println(sb);
    }

    public static class Coin {
        int cnt;
        int value;

        public Coin(int cnt, int value) {
            this.cnt = cnt;
            this.value = value;
        }
    }
}