package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 : 180ms
// 메모리 : 18262KB

public class BOJ_16928 {
	
	// priorityqueue에 넣기 위해 comparable 넣은 클래스
	public static class Game implements Comparable<Game>{
		int start;
		int end;
		
		public Game(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Game o) {
			return Integer.compare(this.end, o.end);
		}

		
	}
	static int min;
	public static void main(String[] args) throws IOException {

		// 각각 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] labber = new int[N][2];
		int[][] snake = new int[N][2];
		
		for (int i = 0; i < N; i++) {			
			st = new StringTokenizer(br.readLine());
			labber[i][0] = Integer.parseInt(st.nextToken());
			labber[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			snake[i][0] = Integer.parseInt(st.nextToken());
			snake[i][1] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		go(labber, snake);
		System.out.println(min);
		
	}
	private static void go(int[][] labber, int[][] snake) {
		
		boolean[] v = new boolean[101];
		
		PriorityQueue<Game> que = new PriorityQueue<>();
		que.offer(new Game(1,0));
		v[1] = true;
		
		while(!que.isEmpty()) {
			Game now = que.poll();
			if(now.start>=100) {
				min = now.end;
				return;
			}
			for (int i = 1; i <= 6; i++) {
				if(now.start+i<=100 && v[now.start+i]) continue;
				for (int[] game : labber) {
					if(game[0]==now.start+i) {
						que.offer(new Game(game[1], now.end+1));
						v[game[0]] = true;
					}
				}
				for (int[] game : snake) {
					if(game[0]==now.start+i) {
						que.offer(new Game(game[1], now.end+1));
						v[game[0]] = true;
					}
				}
				if(now.start+i<=100 && !v[now.start+i]) {
					que.offer(new Game(now.start+i, now.end+1));
					v[now.start] = true;
				}
			}
		}
	}

}
