package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 544ms
//메모리 37300kb
//코드길이 1634b

public class Main_BOJ_1937_욕심쟁이판다_골드3 {
	static int[][] map;
	static int[][] dp;
	static int N;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i,j);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}

	private static int dfs(int r, int c) {
		// 기록이 있다면
		if(dp[r][c] > 0) {
			return dp[r][c];
		}
		
		dp[r][c] = 1; // 처음 1칸
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 맵 크기, 방문여부 체크
			if(!check(nr, nc)) continue;
			
			// 다음 지역이 대나무가 더 많다면
			if(map[nr][nc] > map[r][c]) {
				dp[r][c] = Math.max(dp[r][c], dfs(nr,nc)+1);
			}
		}
		
		return dp[r][c];
	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}