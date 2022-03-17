package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 560ms
//메모리 36804kb
//코드길이 2618b
public class Main_BOJ_4179_불_골드4 {
	public static class Loc{
		int r;
		int c;
		int cnt;
		
		public Loc(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int R, C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] map;
	static Queue<Loc> q;
	static Queue<Loc> fq;
	static String result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		q = new LinkedList<>();
		fq = new LinkedList<>();
		
		for(int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j];
				if(temp[j] == 'J') {
					q.add(new Loc(i,j,1));
				}
				if(temp[j] == 'F') {
					fq.add(new Loc(i,j,0));
				}
			}
		}
		result = "IMPOSSIBLE";
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		while(true) {
			// 불 확산
			int fcnt = fq.size();
			while(fcnt > 0) {
				Loc fire = fq.poll();
				for(int d = 0; d < 4; d++) {
					int nr = fire.r + dr[d];
					int nc = fire.c + dc[d];
					if(!check(nr,nc)) continue;
					if(map[nr][nc] == '.' || map[nr][nc] == 'J') {
						fq.offer(new Loc(nr, nc, 0));
						map[nr][nc] = 'F';
					}
				}
				fcnt--;
			}
			//사람이동
			int cnt = q.size();
			while(cnt > 0) {
				Loc temp = q.poll();
				for(int d = 0; d < 4; d++) {
					int nr = temp.r + dr[d];
					int nc = temp.c + dc[d];
					// 가장자리 일때 탈출
					if(!check(nr,nc)) {
						result = String.valueOf(temp.cnt);
						return;
					}
					if(map[nr][nc] == '.') {
						q.offer(new Loc(nr,nc,temp.cnt+1));
						map[nr][nc] = 'J';
					}
				}
				cnt--;
			}
			if(q.isEmpty()) return;
		}
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
