package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 860ms
//메모리 298232kb
//코드길이 2807b

public class Main_BOJ_16234_인구이동_골드5 {
	static class Loc{
		int r;
		int c;
		int flag;
		
		public Loc(int r, int c, int flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
	}
	
	static int N, L, R;
	static int[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean check;
	static int[][] save; // save 배열에 sum 과 count를 저장하여 시간 단축
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(migration());
	}

	private static int migration() {
		int count = 0;
		while(true) {
			int[][] visit = new int[N][N];
			save = new int[N*N+1][2];
			
			// 1. 국경선 열기
			int flag = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j] == 0) {
						visit = bfs(i,j,visit,flag++);
					}
					
				}
			}
			
			if(check) {
				check = false;
				count++;
			}else {
				return count;
			}
			
			// 2. 연합 이루기
			for (int f = 1; f <= flag; f++) {
				if(save[f][1] <= 1) continue; // == 0  에서 <= 1로 시간단축
				int val = (int)(save[f][0]/save[f][1]);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(visit[i][j] == f) {
							map[i][j] = val;
						}
					}
				}
			}
		}
	}

	private static int[][] bfs(int r, int c, int[][] visit, int flag) {
		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(r,c,flag));
		visit[r][c] = flag;
		save[flag][0] += map[r][c];
		save[flag][1] += 1;
		
		while(!q.isEmpty()) {
			Loc temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = temp.r+dr[d];
				int nc = temp.c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(visit[nr][nc] == flag) continue;
				if(Math.abs(map[nr][nc] - map[temp.r][temp.c]) >= L && Math.abs(map[nr][nc] - map[temp.r][temp.c]) <= R) {
					visit[nr][nc] = temp.flag;
					q.add(new Loc(nr,nc,temp.flag));
					save[flag][0] += map[nr][nc];
					save[flag][1]++;
					check = true;
				}
			}
		}
		return visit;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
