package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 232ms
//메모리 26472kb
//코드길이 2730b

public class Main_BOJ_21610_마법사상어와비바라기_골드5{
	static class Loc{
		int r;
		int c;
		
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {-1,-1,0,1,1,1,0,-1};
	static int[][] map;
	static boolean[][] visit;
	static Queue<Loc> cloud;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 비바라기 시전
		cloud = new LinkedList<>();
		cloud.add(new Loc(N-1,0));
		cloud.add(new Loc(N-1,1));
		cloud.add(new Loc(N-2,0));
		cloud.add(new Loc(N-2,1));
		
		for (int i = 0; i < M; i++) {
			// 구름 기록 초기화
			visit = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken()) -1;
			int S = Integer.parseInt(st.nextToken());
			
			while(!cloud.isEmpty()) {
				Loc temp = cloud.poll(); // 구름이 미리 사라짐
				int nr = temp.r;
				int nc = temp.c;
				
				// 1. 모든 구름이 d 방향으로 s칸 이동
				nr += (dr[D]*S)%N;
				nc += (dc[D]*S)%N;
				if(nr > N-1) nr -= N;
				else if(nr < 0) nr += N;
				if(nc > N-1) nc -= N;
				else if(nc < 0) nc += N;
				// 2. 비가 내린다.
				map[nr][nc] += 1;
				visit[nr][nc] = true;
			}
			
			// 4. 물복사 버그를 쓴다. 
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(visit[r][c]) {
						int cnt = 0;
						for (int d = 1; d < 8; d+=2) {
							int nr = r+dr[d];
							int nc = c+dc[d];
							
							if(!check(nr,nc)) continue;
							if(map[nr][nc] > 0) {
								cnt++;
							}
						}
						map[r][c] += cnt;
					}
				}
			}
			
			// 5. 물이 2 이상이면 구름 생긴다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(!visit[r][c]) {
						if(map[r][c] >= 2) {
							map[r][c] -= 2;
							cloud.add(new Loc(r,c));
						}
					}
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
