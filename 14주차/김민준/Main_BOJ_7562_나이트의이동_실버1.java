package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 304ms
//메모리 73100kb
public class Main_BOJ_7562_나이트의이동_실버1 {
	
	static int[] dr = {-2,-1,1,2,2,1,-1,-2};
	static int[] dc = {1,2,2,1,-1,-2,-2,-1};
	static int T, I;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			I = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			result = 0;
			bfs(sr,sc,er,ec);
			System.out.println(result);
		}
	}
	
	public static void bfs(int sr, int sc, int er, int ec) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sr,sc,0});
		boolean[][] visit = new boolean[I][I];
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			if(temp[0] == er && temp[1] == ec) {
				result = temp[2];
				return;
			}
			for (int d = 0; d < 8; d++) {
				int nr = temp[0]+dr[d];
				int nc = temp[1]+dc[d];
				if(nr < 0 || nr >= I || nc < 0 || nc >= I) continue;
				if(visit[nr][nc]) continue;
				q.add(new int[] {nr,nc,temp[2]+1});
				visit[nr][nc] = true;
			}
		}
	}
}
