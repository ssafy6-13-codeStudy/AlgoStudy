package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//시간 84ms
//메모리 11604kb
public class Main_BOJ_16954_움직이는미로탈출_골드4 {
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
	
	static int N = 8;
	static char[][] map = new char[8][8];
	static int[] dr = {0,0,1,0,-1,-1,1,-1,1};
	static int[] dc = {0,1,0,-1,0,1,1,-1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Node> wq = new LinkedList<>();
		
		for (int i = 0; i < 8; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				map[i][j] = temp[j];
				if(map[i][j] == '#') {
					wq.add(new Node(i,j,0));
				}
			}
		}
		
		bfs(wq);
	}
	
	public static void bfs(Queue<Node> wq) {
		Queue<Node> q = new LinkedList<>();
		Queue<Node> wq_past = new LinkedList<>();
		Queue<Node> wq_future = new LinkedList<>();
		boolean[][][] visit = new boolean[8][8][8+1];
		q.add(new Node(7,0,0));
		visit[7][0][0] = true;
		boolean[][] visit_future = new boolean[8][8];
		
		while(!q.isEmpty()) {
			int wcnt = wq.size();
			for (int i = 0; i < wcnt; i++) {
				Node temp = wq.poll();
				wq_past.add(temp);
				
				int nr = temp.r+1;
				int nc = temp.c;
				if(!check(nr,nc)) continue;
				wq.add(new Node(nr,nc,0));
				wq_future.add(new Node(nr,nc,0));
				visit_future[nr][nc] = true;
			}
			int cnt = q.size();
			for (int i = 0; i < cnt; i++) {
				Node temp = q.poll();
				int time = temp.cnt+1 > 8 ? 8 : temp.cnt+1;
				
				for (int d = 0; d < 9; d++) {
					int nr = temp.r + dr[d];
					int nc = temp.c + dc[d];
					if(!check(nr,nc) || visit[nr][nc][time] || map[nr][nc] != '.') continue;
					if(nr == 0 && nc == 7) {
						System.out.println(1);
						return;
					}
					if(visit_future[nr][nc]) continue;
					
					q.add(new Node(nr,nc,time));
					visit[nr][nc][time] = true;
				}
			}
			
			while(!wq_past.isEmpty()) {
				Node temp = wq_past.poll();
				map[temp.r][temp.c] = '.';
			}
			while(!wq_future.isEmpty()) {
				Node temp = wq_future.poll();
				map[temp.r][temp.c] = '#';
				visit_future[temp.r][temp.c] = false;
			}
		}
		System.out.println(0);
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}
}
