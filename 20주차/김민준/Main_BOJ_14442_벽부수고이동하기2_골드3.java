package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 1436ms
//메모리 370880kb
public class Main_BOJ_14442_벽부수고이동하기2_골드3 {
	static class Node{
		int r;
		int c;
		int smash;
		int cnt;
		
		public Node(int r, int c, int smash, int cnt) {
			this.r = r;
			this.c = c;
			this.smash = smash;
			this.cnt = cnt;
		}
	}
	
	static int N, M, K;
	static char[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		if(N == 1 && M == 1) {
			System.out.println(1);
		}else {			
			bfs();
		}
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visit = new boolean[N][M][K+1];
		q.add(new Node(0,0,K,1));
		visit[0][0][K] = true;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = temp.r + dr[d];
				int nc = temp.c + dc[d];
				
				if(nr == N-1 && nc == M-1) {
					System.out.println(temp.cnt+1);
					return;
				}
				if(!check(nr,nc)) continue;
				if(map[nr][nc] == '1') {
					if(temp.smash > 0 && !visit[nr][nc][temp.smash-1]) {
						q.add(new Node(nr,nc,temp.smash-1, temp.cnt+1));
						visit[nr][nc][temp.smash-1] = true;
					}
				}
				else{
					if(!visit[nr][nc][temp.smash]) {						
						q.add(new Node(nr,nc,temp.smash, temp.cnt+1));
						visit[nr][nc][temp.smash] = true;
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M; 
	}
}
