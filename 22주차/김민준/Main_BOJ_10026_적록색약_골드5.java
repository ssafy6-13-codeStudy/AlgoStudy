package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//시간 96ms
//메모리 13392kb
public class Main_BOJ_10026_적록색약_골드5 {
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;
	static boolean[][][] visit;
	static char[][][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[2][N][N];
		
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[0][i][j] = temp[j];
				if(temp[j] == 'G') {
					map[1][i][j] = 'R';
				}else {
					map[1][i][j] = temp[j];
				}
			}
		}
		
		int count = 0;
		int rgcount = 0;
		visit = new boolean[2][N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[0][i][j]) {
					bfs(i,j,0);
					count++;
				}
				if(!visit[1][i][j]) {
					bfs(i,j,1);
					rgcount++;
				}
			}
		}
		
		System.out.println(count + " " + rgcount);
	}
	
	public static void bfs(int r, int c, int flag) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r,c));
		visit[flag][r][c] = true;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = temp.r + dr[d];
				int nc = temp.c + dc[d];
				
				if(!check(nr,nc) || visit[flag][nr][nc]) continue;
				if(map[flag][nr][nc] == map[flag][r][c]) {
					q.add(new Node(nr, nc));
					visit[flag][nr][nc] = true;
				}
			}
		}
	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
