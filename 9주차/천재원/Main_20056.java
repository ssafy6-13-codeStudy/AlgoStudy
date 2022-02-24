import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20056_마법사상어와파이어볼 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M,K;
	static int map[][];
	static List<fireball> list = new ArrayList<>();
	static int dr[] = {-1,-1,0,1,1,1,0,-1};
	static int dc[] = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int t=0; t<M; t++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			map[r][c] += 1;
			list.add(new fireball(
					r, 
					c, 
					m, 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())));
		}
		
		for(int k=0; k<K; k++) {
			for(fireball f : list) {
				move(f);
			}
			combination();

		}
		
		int sum =0;
		for(fireball f : list) {
			sum += f.m;
		}
		System.out.println(sum);
	}
	
	private static void combination() {
		for(int i=1; i<=N; i++) {
			for (int j = 1; j < N; j++) {
				if(map[i][j] != 0) {
					int cnt =0;
					int oddCnt = 0;
					int evenCnt = 0;
					fireball new_f = new fireball(i, j, 0, 0, 0);
					for(Iterator<fireball> iter = list.iterator(); iter.hasNext();) {
						fireball f = iter.next();
						if(f.r == i && f.c == j) {
							if(f.m%2 == 1) oddCnt +=1;
							else evenCnt +=1;
							cnt +=1;
							new_f.m += f.m;
							new_f.s += f.s;
							list.remove(f);
						}
					}
					if(cnt == 0) continue;
					new_f.m = (int)new_f.m/5;
					new_f.s = (int)new_f.s/cnt;
					

					// 질량 0이면 사라진다
					if(new_f.m == 0) continue;
					// 4개로 분리
					if(oddCnt == cnt || evenCnt == cnt) {
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 0));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 2));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 4));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 6));
					}else {
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 1));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 3));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 5));
						list.add(new fireball(new_f.r, new_f.c, new_f.m, new_f.s, 7));
					}
					
				}
			}
		}
	}

	private static void move(fireball f) {
		int nr,nc; 
		map[f.r][f.c] -= 1;
		if(f.r + dr[f.d]*f.s < 1) { // 1행 미만으로 올라갈 경우
			nr = f.r + dr[f.d]*f.s + N;
		}else if(f.r + dr[f.d]*f.s > N) { // N행 이상으로 내려갈 경우
			nr = f.r + dr[f.d]*f.s - N;
		}else {
			nr = f.r + dr[f.d]*f.s;
		}
		if(f.c +dc[f.d]*f.s < 1) { // 1열 미만으로 이동할 경우
			nc = f.c + dc[f.d]*f.s + N; 
		}else if(f.c+dc[f.d]*f.s > N) {
			nc = f.c + dc[f.d]*f.s - N;
		}else {
			nc = f.c + dc[f.d]*f.s;
		}
		f.r = nr; f.c = nc;
		map[f.r][f.c] +=1; 
	}

	static class fireball{
		int r;
		int c;
		int m;
		int s;
		int d;
		
		
		public fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
