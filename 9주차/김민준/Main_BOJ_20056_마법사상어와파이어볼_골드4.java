package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


//시간 1224ms
//메모리 38476kb
//코드길이 2922b
public class Main_BOJ_20056_마법사상어와파이어볼_골드4 {
	static class Fire{
		int r;
		int c;
		int m;
		int s;
		int d;
		
		public Fire(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int N, M, K;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Fire> list = new ArrayList<>();
	static ArrayList<Fire>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Fire(r,c,m,s,d));
		}
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		// 1.이동
		for (int k = 0; k < K; k++) {
			for (Fire fire : list) {
				int nr = fire.r + dr[fire.d]*(fire.s%N);
				int nc = fire.c + dc[fire.d]*(fire.s%N);
				
				if(nr < 0) {
					nr += N;
				}else if(nr >= N) {
					nr -= N;
				}
				if(nc < 0) {
					nc += N;
				}else if(nc >= N) {
					nc -= N;
				}
				
				fire.r = nr;
				fire.c = nc;
				map[nr][nc].add(fire);
			}
			
			// 2.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 2개 이상이면
					if(map[i][j].size() >= 2) {
						
						int m = 0;
						int s = 0;
						boolean isOdd = true;
						boolean isEven = true;
						
						// 합쳐진다.
						for (Fire fire : map[i][j]) {
							m += fire.m;
							s += fire.s;
							if(fire.d %2 == 0) {
								isOdd = false;
							}else {
								isEven = false;
							}
							list.remove(fire);
						}
						
						// 4개로 나눠진다.
						m = m / 5;
						s = s / map[i][j].size();
						
						if(m > 0) {
							if(isOdd || isEven) {
								list.add(new Fire(i, j, m, s, 0));
								list.add(new Fire(i, j, m, s, 2));
								list.add(new Fire(i, j, m, s, 4));
								list.add(new Fire(i, j, m, s, 6));
							}else {
								list.add(new Fire(i, j, m, s, 1));
								list.add(new Fire(i, j, m, s, 3));
								list.add(new Fire(i, j, m, s, 5));
								list.add(new Fire(i, j, m, s, 7));
							}
						}
					}
					map[i][j].clear();
				}
			}
		}
		
		// result
		int sum = 0;
		for (Fire fire : list) {
			sum += fire.m;
		}
		
		System.out.println(sum);
	}
}
