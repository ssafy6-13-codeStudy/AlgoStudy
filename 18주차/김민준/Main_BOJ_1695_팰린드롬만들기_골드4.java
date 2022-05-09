package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간 288ms
//메모리 111516kb
public class Main_BOJ_1695_팰린드롬만들기_골드4 {
	static int[] list;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		list = new int[N];
		dp = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(dfs(0, N-1));
	}

	private static int dfs(int start, int end) {
		if(start >= end) {
			return 0;
		}
		
		if(dp[start][end] != -1) {
			return dp[start][end]; 
		}
		
		if(list[start] == list[end]) {
			dp[start][end] = dfs(start+1,end-1);
		}else{
			dp[start][end] = Math.min(dfs(start+1,end) + 1, dfs(start, end-1) + 1);
		}
		return dp[start][end];
	}
}
