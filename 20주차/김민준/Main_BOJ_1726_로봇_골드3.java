package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 104ms
//메모리 12572kb
public class Main_BOJ_1726_로봇_골드3 {
	static class Node{
		int r;
		int c;
		int d;
		int cnt;
		
		public Node(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static boolean[][] map;
	static Node start;
	static Node end;
	static int[] dr = {0,0,1,-1}; // 동서남북
	static int[] dc = {1,-1,0,0};
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().equals("1") ? false : true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken())-1;
		int sc = Integer.parseInt(st.nextToken())-1;
		int sd = Integer.parseInt(st.nextToken())-1;
		start = new Node(sr,sc,sd,0);
		
		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken())-1;
		int ec = Integer.parseInt(st.nextToken())-1;
		int ed = Integer.parseInt(st.nextToken())-1;
		end = new Node(er,ec,ed,0);
		
		result = 0;
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visit = new boolean[M][N][4];
		q.add(start);
		visit[start.r][start.c][start.d] = true;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			if(temp.r == end.r && temp.c == end.c && temp.d == end.d) {
				result = temp.cnt;
				return;
			}
			
			// turn
			if(temp.d == 0 || temp.d == 1) {
				if(!visit[temp.r][temp.c][2]) {
					q.add(new Node(temp.r, temp.c, 2, temp.cnt+1));
					visit[temp.r][temp.c][2] = true;
				}
				if(!visit[temp.r][temp.c][3]) {
					q.add(new Node(temp.r, temp.c, 3, temp.cnt+1));
					visit[temp.r][temp.c][3] = true;
				}
			}else {
				if(!visit[temp.r][temp.c][0]) {
					q.add(new Node(temp.r, temp.c, 0, temp.cnt+1));
					visit[temp.r][temp.c][0] = true;
				}
				if(!visit[temp.r][temp.c][1]) {
					q.add(new Node(temp.r, temp.c, 1, temp.cnt+1));
					visit[temp.r][temp.c][1] = true;
				}
			}
			
			// forward
			for (int i = 1; i <= 3; i++) {
				int nr = temp.r + dr[temp.d]*i;
				int nc = temp.c + dc[temp.d]*i;
				
				if(!check(nr,nc)) continue;
				if(!map[nr][nc]) break;
				if(visit[nr][nc][temp.d]) continue;
				q.add(new Node(nr, nc, temp.d, temp.cnt+1));
				visit[nr][nc][temp.d] = true;
			}
		}
	} 
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}
