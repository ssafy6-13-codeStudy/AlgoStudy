import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 452ms
// 메모리 37140KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int sand = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sand +=map[i][j];
			}
		}
		
		// 왼 아 오 위
		int[][] dr = {
				{-1,1,-2,-1,1,2,-1,1,0, 0},
				{-1,-1,0,0,0,0,1,1,2,1},
				{-1,1,-2,-1,1,2,-1,1,0,0},
				{1,1,0,0,0,0,-1,-1,-2,-1},
				{0, 1, 0, -1}
		};
		int[][] dc = {
				{1,1,0,0,0,0,-1,-1,-2,-1},
				{-1,1,-2,-1,1,2,-1,1,0,0},
				{-1,-1,0,0,0,0,1,1,2,1},
				{-1,1,-2,-1,1,2,-1,1,0,0},
				{-1,0,1,0}
		};
		
		int[] dt = {1,1,2,7,7,2,10,10,5,55};
		
		
		int d = 0;
		int r = N/2;
		int c = N/2;
		int count = 1;
		outer : while(true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < count; j++) {
					int nr = r+dr[4][d];
					int nc = c+dc[4][d];
					if(r==0 && c==0) break outer;
					int rest = map[nr][nc];
					for (int k = 0; k < 10; k++) {
						int nnr = nr + dr[d][k];
						int nnc = nc + dc[d][k];
						
						if(nnr<0 || nnc<0 || nnr>=N || nnc>=N) {
							if(k!=9)rest -= map[nr][nc]*dt[k]/100;
							continue;
						}
						if(k!=9) {
						rest -= map[nr][nc]*dt[k]/100;
						map[nnr][nnc] += map[nr][nc]*dt[k]/100;
						}else {
							map[nnr][nnc] += rest;
						}
					}
					map[nr][nc] = 0;
					r = nr;
					c = nc;
				}
				d++;
				if(d==4) d=0;
			}
			count++;
		}
		int rest = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rest += map[i][j];
			}
		}
		System.out.println(sand - rest);
 	}

}
