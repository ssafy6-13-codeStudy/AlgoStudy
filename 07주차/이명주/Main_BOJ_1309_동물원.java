import java.io.*;
import java.util.*;

/*
    동물원
    실버 1
    시간 : 88ms
    메모리 : 11928kb
*/

public class Main {

    /*
    n=1 3개
        00 01 10
        1  1  1
    n=2 7개
        00 01 10
        3  2  2
    n=3 17
        00 01 10
        7  5  5
    n=4 41
        00 01 10
        17 12 12

        dp[n+2] = dp[n] + dp[n+1]*2
     */

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int dp[] = new int[N + 1];

        dp[0] = 0;
        dp[1] = 3;
        if (N >= 2)
            dp[2] = 7;
        if (N >= 3)
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1] * 2) % 9901;
            }

        System.out.println(dp[N]);

    }
}
