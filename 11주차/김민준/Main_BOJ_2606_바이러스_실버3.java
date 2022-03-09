package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 136ms
//메모리 14256kb
//코드길이 1177b
public class Main_BOJ_2606_바이러스_실버3 {
	static int N, M;
	static boolean[][] map;
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from][to] = true;
			map[to][from] = true;
		}
		cnt = 0;
		bfs(1);
		System.out.println(cnt);
	}
	
	private static void bfs(int num) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[N+1];
		q.offer(num);
		visit[num] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if(map[temp][i] && !visit[i]) {
					q.offer(i);
					visit[i] = true;
					cnt++;
				}
			}
		}
	}
}
