package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 88ms
//메모리 12040kb
public class Main_BOJ_16197_두동전_골드4 {
	public static class Node{
		Coin a;
		Coin b;
		int cnt;
		
		public Node(Coin a, Coin b, int cnt) {
			this.a = a;
			this.b = b;
			this.cnt = cnt;
		}
	}
	
	public static class Coin{
		int r;
		int c;
		
		public Coin(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static char[][] map;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {-1,1,0,0};
	static List<Coin> list;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray(); 
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j];
				if(map[i][j] == 'o') {
					list.add(new Coin(i,j));
				}
			}
		}
		result = -1;
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][][] visit = new boolean[N][M][N][M];
		q.add(new Node(list.get(0), list.get(1), 0));
		visit[list.get(0).r][list.get(0).c][list.get(1).r][list.get(1).c] = true;
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr0 = temp.a.r + dr[d]; 
				int nc0 = temp.a.c + dc[d];
				int nr1 = temp.b.r + dr[d]; 
				int nc1 = temp.b.c + dc[d];
				boolean flag0 = true;
				boolean flag1 = true;
				
				if(!check(nr0, nc0)) flag0 = false;
				if(!check(nr1, nc1)) flag1 = false;
				
				if(!(flag0 || flag1)) continue;
				else if(!(flag0 && flag1)) {
					result = temp.cnt+1;
					return;
				}
				if(temp.cnt+1 >= 10) continue;
				if(visit[nr0][nc0][nr1][nc1]) continue;
				if(nr0 == nr1 && nc0 == nc1) continue;
				if(map[nr0][nc0] == '#' && map[nr1][nc1] == '#') continue;
				if(map[nr0][nc0] == '#') {
					nr0 = temp.a.r;
					nc0 = temp.a.c;
				}
				if(map[nr1][nc1] == '#') {
					nr1 = temp.b.r;
					nc1 = temp.b.c;
				}
				
				visit[nr0][nc0][nr1][nc1] = true;
				q.add(new Node(new Coin(nr0,nc0), new Coin(nr1,nc1), temp.cnt+1));
			}
		}
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
