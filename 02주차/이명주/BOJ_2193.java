import java.io.*;
import java.util.*;

/*
    이친수
    실버 3
    시간 : 76ms
    메모리 : 11488kb
 */

public class Main {

    /*
    1. 0 으로 시작하지 않는다.
    2. 1이 두 번 연속으로 나타나지 않는다.

    1 : 1
    2 : 10
    3 : 100 101
    4 : 1000 1010 1001
    5 : 10000 10100 10101 10010 10001
    6 : 100000 100001 101001 101000 101010 100101 100100 100010
     */

    static long dp[];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long [N+1];

        dp[1] = 1; // 1
        if(N!=1) dp[2] = 1; // 10

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);
    }
}