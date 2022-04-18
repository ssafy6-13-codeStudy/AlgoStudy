package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 124ms
//메모리 14184kb
public class Main_BOJ_2644_촌수계산_실버2 {
	static int N;
	static int a, b;
	static int M;
	static boolean[][] relation;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		relation = new boolean[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			relation[x][y] = true;
			relation[y][x] = true;
		}
		
		bfs(a,b);
	}

	private static void bfs(int s, int e) {
		boolean[] visit = new boolean[N+1];
		Queue<int[]> q = new LinkedList<>();
		visit[s] = true;
		q.offer(new int[] {s,0});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			if(temp[0] == e) {
				System.out.println(temp[1]);
				return;
			}
			
			for (int i = 1; i <= N; i++) {
				if(relation[temp[0]][i] && !visit[i]) {
					q.offer(new int[] {i,temp[1]+1});
					visit[i] = true;
				}
			}
		}
		System.out.println(-1);
	}
}
