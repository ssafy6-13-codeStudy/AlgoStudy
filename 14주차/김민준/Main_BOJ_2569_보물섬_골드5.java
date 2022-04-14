package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 536ms
//메모리 294992kb
public class Main_BOJ_2569_보물섬_골드5 {
	public static class Loc{
		int r;
		int c;
		int cnt;
		
		public Loc(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N, M;
	static char[][] map;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					bfs(i,j);
				}
			}
		}
		System.out.println(max);
	}

	private static void bfs(int r, int c) {
		Queue<Loc> q = new LinkedList<>();
		q.offer(new Loc(r,c,0));
		boolean[][] visit = new boolean[N][M];
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			Loc temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = temp.r + dr[d];
				int nc = temp.c + dc[d];
				
				if(!check(nr,nc) || visit[nr][nc] || map[nr][nc] == 'W') {
					continue;
				}
				
				q.offer(new Loc(nr,nc,temp.cnt+1));
				max = Math.max(max, temp.cnt+1);
				visit[nr][nc] = true;
				
			}
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
