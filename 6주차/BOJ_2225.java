package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 남의 코드....^^
// 시간 : 152ms
// 메모리 : 12088KB
public class BOJ_2225 {

	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		long[][] dp = new long[K+1][N+1];
		for (int i = 0; i <= N; i++) {
			dp[1][i] = 1;
		}
		for (int i = 1; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				for (int j2 = 0; j2 <= j; j2++) {
					dp[i][j]=(dp[i][j]+dp[i-1][j-j2])%1000000000;;
				}
			}
		}
		System.out.println(dp[K][N]);
		
	}

	

}
