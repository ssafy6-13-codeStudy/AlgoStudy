package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 ms
//메모리 kb
//코드길이 b
//

public class Main_BOJ_2225_합분해_골드5 {
	static int N, K;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1][N+1];
		
		// init
		for (int i = 1; i <= K; i++) {
			Arrays.fill(dp[i], 1);
		}
		
		for (int k = 2; k <= K; k++) {
			for (int n = 1; n <= N; n++) {
				dp[k][n] = (dp[k-1][n] + dp[k][n-1])%1000000000;
			}
		}
		
		System.out.println(dp[K][N]);
	}
}
