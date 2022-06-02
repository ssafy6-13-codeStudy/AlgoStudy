	// 369600kb
  // 1664ms

import java.io.*;
import java.util.*;


public class Main_14442_벽부수고이동하기2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][] map;
	static int N,M,K, answer = Integer.MAX_VALUE;
	static int dr[] = {1,0,0,-1};
	static int dc[] = {0,1,-1,0};
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for(int i=0; i<N; i++) {
			char[] buf = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = buf[j] == '1' ? false : true;
			}
		}
		bfs();
		if(answer == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(answer);
		}
		
	}
	private static void bfs() {
		Queue<position> que = new LinkedList<>();
		boolean[][][] visited= new boolean[N][M][K+1];
		visited[0][0][0] = true;
		que.offer(new position(0,0,1,0));
		
		while(!que.isEmpty()) {
			position cur = que.poll();
			if(cur.r == N-1 && cur.c == M-1) {
				answer = Math.min(answer, cur.time);
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int chance = cur.wall;
				if(nr>=0 && nr < N && nc>=0 && nc < M) {
					if(map[nr][nc] == false) {
						if(chance<K && !visited[nr][nc][chance+1]) {
							visited[nr][nc][chance+1] = true;
							que.offer(new position(nr,nc,cur.time+1, chance+1));
						}
					}else{
						if(!visited[nr][nc][chance]) {
							visited[nr][nc][chance] = true;	
							que.offer(new position(nr,nc,cur.time+1, chance));
						}
					}
				}
			}
		}
		
	}
	
	static public class position {
		int r;
		int c; 
		int time;
		int wall;
		public position(int r, int c, int time, int wall) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.wall = wall;
		}		
	}
	
	
}
