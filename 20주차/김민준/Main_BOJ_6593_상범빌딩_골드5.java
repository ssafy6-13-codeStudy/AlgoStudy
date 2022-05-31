package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 128ms
//메모리 15160kb
public class Main_BOJ_6593_상범빌딩_골드5 {
	static class Node{
		int r;
		int c;
		int h;
		int time;
		
		public Node(int r, int c, int h, int time) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.time = time;
		}
	}
	
	static int L, R, C;
	static char[][][] map;
	static int[] dr = {0,1,0,-1,0,0};
	static int[] dc = {1,0,-1,0,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	static Node start;
	static Node end;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L < 1 && R < 1 && C < 1) {
				break;
			}
			
			map = new char[L][R][C];
			
			for (int h = 0; h < L; h++) {
				for (int i = 0; i < R; i++) {
					char[] temp = br.readLine().toCharArray();
					for (int j = 0; j < C; j++) {
						map[h][i][j] = temp[j];
						if(map[h][i][j] == 'S') {
							start = new Node(i,j,h,0);
						}else if(map[h][i][j] == 'E') {
							end = new Node(i,j,h,0);
						}
					}
				}
				br.readLine();
			}
			
			bfs();
		}
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visit = new boolean[L][R][C];
		q.add(start);
		visit[start.h][start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 6; d++) {
				int nh = temp.h + dh[d];
				int nr = temp.r + dr[d];
				int nc = temp.c + dc[d];
				
				if(!check(nr,nc,nh) || visit[nh][nr][nc] || map[nh][nr][nc] == '#') continue;
				if(nr == end.r && nc == end.c && nh == end.h) {
					System.out.println("Escaped in "+(temp.time+1)+" minute(s).");
					return;
				}
				q.add(new Node(nr,nc,nh,temp.time+1));
				visit[nh][nr][nc] = true;
			}
		}
		System.out.println("Trapped!");
	}
	
	public static boolean check(int r, int c, int h) {
		return r >= 0 && r < R && c >= 0 && c < C && h >= 0 && h < L;
	}
}
