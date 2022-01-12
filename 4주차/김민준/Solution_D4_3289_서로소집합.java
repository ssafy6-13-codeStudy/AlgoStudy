package week4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3289_서로소집합 {
	static int T;
	static int N, M;
	
	static int[] p;
	static int[] r;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			StringBuffer sb = new StringBuffer();
			
			init();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(num == 0) {
					union(x, y);
				}else {
					if(find(x) == find(y)) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}
			
			System.out.println("#"+t+" "+sb.toString());
		}
	}
	
	public static void init() {
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}
	
	public static int find(int x) {
		if(p[x] == x) {
			return p[x];
		}
		else {
			p[x] = find(p[x]);
			return p[x];
		}
	}
	
	public static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py) {
			return;
		}
		if(r[px] >= r[py]) {
			p[py] = p[px];
			r[px] += r[py];
		}else {
			p[px] = p[py];
			r[py] += r[px];
		}
	}
}
