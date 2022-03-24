package week13;

import java.util.Scanner;

public class Main_BOJ_1699_제곱수의합_실버3 {
	static int[] dp;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = i;
			for (int j = 1; j*j <= i; j++) {
				dp[i] = Math.min(dp[i-(j*j)]+1, dp[i]);
			}
		}
		
		System.out.println(dp[n]);
	}
}
