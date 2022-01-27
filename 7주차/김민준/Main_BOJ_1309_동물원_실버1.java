package week7;

import java.util.Scanner;

//시간 220ms
//메모리 18840kb
//코드길이 578b

public class Main_BOJ_1309_동물원_실버1{
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[3][N+1];
		
		dp[0][1] = 1;
		dp[1][1] = 1;
		dp[2][1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[0][i] = (dp[0][i-1]+dp[1][i-1]+dp[2][i-1])%9901;
			dp[1][i] = (dp[0][i-1]+dp[2][i-1])%9901;
			dp[2][i] = (dp[0][i-1]+dp[1][i-1])%9901;
		}
		
		System.out.println((dp[0][N]+dp[1][N]+dp[2][N])%9901);
	}
}
