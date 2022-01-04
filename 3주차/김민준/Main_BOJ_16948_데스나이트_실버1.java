package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 140ms
//메모리 14640kb
//코드길이 1695

public class Main_BOJ_16948_데스나이트_실버1 {
	static class Loc{
		int r;
		int c;
		int cnt;
		
		public Loc(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N;
	static boolean[][] map;
	static int sr, sc, er, ec;
	static int[] dr = {-2,-2,0,0,2,2};
	static int[] dc = {-1,1,-2,2,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		System.out.println(cal());
	}

	private static int cal() { // 메모리초과 -> 중복위치 제거
		Queue<Loc> q = new LinkedList<>();
		// 스타트 위치
		q.offer(new Loc(sr,sc,0));
		map[sr][sc] = true;
		while(!q.isEmpty()) {
			Loc curr = q.poll();
			for (int d = 0; d < 6; d++) {
				int nr = curr.r+dr[d];
				int nc = curr.c+dc[d];
				//범위 체크
				if(!check(nr,nc)) continue;
				//중복 체크
				if(map[nr][nc]) continue;
				//도착위치와 같으면 리턴
				if(nr == er && nc == ec) {
					return curr.cnt+1;
				}
				q.offer(new Loc(nr,nc,curr.cnt+1));
				map[nr][nc] = true;
			}
		}
		// 아니면 -1 리턴
		return -1;
	}
	
	public static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
