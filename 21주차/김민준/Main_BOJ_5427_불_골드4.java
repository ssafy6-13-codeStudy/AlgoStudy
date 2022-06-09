package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 ms
//메모리 kb
public class Main_BOJ_5427_불_골드4 {
	static class Node{
		int r;
		int c;
		int cnt;
		
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int W, H;
	static char[][] map;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			Queue<Node> q = new LinkedList<>();
			Queue<Node> fq = new LinkedList<>();
			
			for (int i = 0; i < H; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = temp[j];
					if(map[i][j] == '@') {
						q.add(new Node(i,j,0));
					}else if(map[i][j] == '*') {
						fq.add(new Node(i,j,0));
					}
				}
			}
			
			bfs(q, fq);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void bfs(Queue<Node> q, Queue<Node> fq) {
		while(!q.isEmpty()) {
			int firecnt = fq.size();
			for (int i = 0; i < firecnt; i++) {
				Node ftemp = fq.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = ftemp.r + dr[d];
					int nc = ftemp.c + dc[d];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc] == '.' || map[nr][nc] == '@') {
						fq.add(new Node(nr,nc,0));
						map[nr][nc] = '*';
					}
				}
			}
			
			int cnt = q.size();
			for (int i = 0; i < cnt; i++) {
				Node temp = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = temp.r + dr[d];
					int nc = temp.c + dc[d];
					
					if(!check(nr,nc)) {
						sb.append(temp.cnt+1).append("\n");
						return;
					}
					if(map[nr][nc] == '.') {
						q.add(new Node(nr,nc,temp.cnt+1));
						map[nr][nc] = '@';
					}
				}
			}
		}
		sb.append("IMPOSSIBLE\n");
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
