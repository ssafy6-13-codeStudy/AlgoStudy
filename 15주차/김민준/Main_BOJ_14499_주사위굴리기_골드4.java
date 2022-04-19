package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 84ms
//메모리 12004kb
public class Main_BOJ_14499_주사위굴리기_골드4 {
	static int N, M;
	static int r, c, K;
	static int[][] map;
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};
	static int[] dice = {0,0,0,0,0,0}; // 바닥, 동, 서, 북, 남, 위
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!check(nr,nc)) continue;
			// 주사위 굴리기
			roll(d);
			
			if(map[nr][nc] == 0) {
				map[nr][nc] = dice[0];
			}else {
				dice[0] = map[nr][nc];
				map[nr][nc] = 0;
			}
			r = nr;
			c = nc;
			sb.append(dice[5]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void roll(int d) {
		int bottom = dice[0];
		dice[0] = dice[d];
		dice[d] = dice[5];
		if(d == 1) {
			dice[5] = dice[2];
			dice[2] = bottom;
		}else if(d == 2) {
			dice[5] = dice[1];
			dice[1] = bottom;
		}else if(d == 3) {
			dice[5] = dice[4];
			dice[4] = bottom;
		}else {
			dice[5] = dice[3];
			dice[3] = bottom;
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
