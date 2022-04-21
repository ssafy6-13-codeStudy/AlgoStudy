package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 280ms
//메모리 12936kb
public class Main_BOJ_17070_파이프옮기기1_골드5 {
	
	static int N;
	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0};
	static int[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
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
		
		dfs(0,1,0);
		System.out.println(dp[N-1][N-1]);
	}

	public static void dfs(int r, int c, int type) {
		if(r == N-1 && c == N-1) {
			return;
		}
		for (int d = 0; d < 3; d++) {
			if(d == 2 && type == 0) continue;
			if(d == 0 && type == 2) continue;
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(!check(nr,nc) || map[nr][nc] == 1) continue;
			if(d == 1) {
				if(map[r+dr[0]][c+dc[0]] == 1 || map[r+dr[2]][c+dc[2]] == 1) continue;
			}
			dfs(nr,nc,d);
			dp[nr][nc] += 1;
		}
	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
