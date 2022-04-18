package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 168ms
//메모리 16016kb
public class Main_BOJ_1012_유기농배추_실버2 {
	
	static int T, M, N, K;
	static int X, Y;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new boolean[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				map[r][c] = true;
			}
			
			visit = new boolean[N][M];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] && !visit[i][j]) {		
						cnt++;
						bfs(i,j);
					}
				}
			}
			
			System.out.println(cnt);
		}
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visit[r][c] = true;
		q.add(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || !map[nr][nc]) continue;
				visit[nr][nc] = true;
				q.add(new int[] {nr,nc});
			}
		}
	}
}
