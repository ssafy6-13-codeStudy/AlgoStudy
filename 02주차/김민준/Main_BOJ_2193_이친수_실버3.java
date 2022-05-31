package week2;

import java.util.Scanner;

//시간 208ms
//메모리 17748kb
//코드길이 452

public class Main_BOJ_2193_이친수_실버3 {
	static int N;
	static long[][] dp;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new long[N+1][2];
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i-1][0]+dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}
		
		System.out.println(dp[N][0]+dp[N][1]);
	}

}
