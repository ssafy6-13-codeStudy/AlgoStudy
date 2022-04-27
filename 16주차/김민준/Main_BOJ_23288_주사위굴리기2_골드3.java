package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 168ms
//메모리 32964kb
public class Main_BOJ_23288_주사위굴리기2_골드3 {
	
	static int N, M, K;
	static int[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[] dice = {1,3,4,2,5,6}; // 위 , 동, 서, 북, 남, 아래
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		int r = 0;
		int c = 0;
		int d = 0; // 처음엔 동쪽
		for (int i = 0; i < K; i++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!check(nr,nc)) {
				d = (d+2)%4;
				nr = r + dr[d];
				nc = c + dc[d];
			}
			roll(d);
			
			//점수 획득
			score += cal(nr,nc);
			
			//방향결정
			if(dice[5] > map[nr][nc]) {
				d = (d+1)%4;
			}else if(dice[5] < map[nr][nc]) {
				d = (d+3)%4;
			}
			r = nr;
			c = nc;
		}
		
		System.out.println(score);
	}

	private static int cal(int r, int c) {
		int base = map[r][c];
		int count = 1;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		boolean[][] visit = new boolean[N][M];
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				
				if(!check(nr,nc) || visit[nr][nc]) continue;
				if(base != map[nr][nc]) continue;
				q.add(new int[] {nr,nc});
				visit[nr][nc] = true;
				count++;
			}
		}
		return base*count;
	}

	private static void roll(int d) {
		int top = dice[0];
		int bottom = dice[5];
		if(d == 0) {
			dice[0] = dice[2];
			dice[5] = dice[1];
			dice[1] = top;
			dice[2] = bottom;
		}else if(d == 1) {
			dice[0] = dice[3];
			dice[5] = dice[4];
			dice[4] = top;
			dice[3] = bottom;
		}else if(d == 2) {
			dice[0] = dice[1];
			dice[5] = dice[2];
			dice[2] = top;
			dice[1] = bottom;
		}else {
			dice[0] = dice[4];
			dice[5] = dice[3];
			dice[3] = top;
			dice[4] = bottom;
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
