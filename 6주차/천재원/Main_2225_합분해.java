// 112ms
// 13020 kb

import java.util.Scanner;

public class Main_2225_합분해 {
	static int N,K, result;
	static int[][] dp; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			dp[i][1] = 1;
		}
		
		for (int i = 1; i <= K; i++) {
			dp[1][i] = i;
		}
		
		for(int i=2; i<=N; i++) {
			for (int j = 2; j <=K; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000000;
			}
		}
		
		
		System.out.println(dp[N][K]);
	}
	
	
}
