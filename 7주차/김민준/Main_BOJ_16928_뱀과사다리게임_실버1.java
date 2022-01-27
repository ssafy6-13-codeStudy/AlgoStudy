package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 128ms
//메모리 14488kb
//코드길이 1556b

public class Main_BOJ_16928_뱀과사다리게임_실버1{
	static class Loc{
		int loc;
		int cnt;
		
		public Loc(int loc, int cnt) {
			this.loc = loc;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static int[] map;
	static boolean[] visit;
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[101];
		visit = new boolean[101];
		
		for (int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start] = end;
		}
		
		bfs();
		System.out.println(count);
	}

	private static void bfs() {
		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(1, 0));
		visit[1] = true;
		
		while(!q.isEmpty()) {
			Loc temp = q.poll();
			for (int i = 6; i >= 1; i--) {
				int next = temp.loc+i;
				if(next > 100 || visit[next]) continue;
				if(next == 100) {
					count = temp.cnt+1;
					return;
				}
				if(map[next] != 0) {
					q.add(new Loc(map[next],temp.cnt+1));
					visit[map[next]] = true;
				}else {
					q.add(new Loc(next,temp.cnt+1));
					visit[next] = true;
				}
			}
		}
	}
}
