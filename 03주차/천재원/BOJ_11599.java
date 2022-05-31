import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11599_PuyoPuyo {
	static final int N = 12, M=6;
	static char map[][] = new char[N][M];
	static int combo;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i <N; i++) {
			char[] buf = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = buf[j];
			}
		}
		
		boolean isCrushed = false;
		while(true) {
			isCrushed = false;
			boolean[][] delete = new boolean[N][M];
			
			for (int i = N-1; i >=0; i--) {
				for (int j = 0; j < M; j++) {
					if(delete[i][j] || map[i][j] == '.') continue;
					Queue<int[]> que = new LinkedList<int[]>();
					boolean[][] visited = new boolean[N][M];
					que.add(new int[] {i,j});
					visited[i][j] = true;
					int cnt = 1;
					while(!que.isEmpty()) {
						int[] cur = que.poll();
						for (int d = 0; d < dr.length; d++) {
							int nr = cur[0]+dr[d];
							int nc = cur[1]+dc[d];
							if(nr<0 || nr >= N || nc< 0 || nc >= M ) continue;
							if(!visited[nr][nc]) {
								if(map[cur[0]][cur[1]] == map[nr][nc] ) {
									que.add(new int[] {nr,nc});
									visited[nr][nc] = true;
									cnt +=1;
								}
							}
						}
					}
					if(cnt >= 4) {
						for (int r = 0; r < N; r++) {
							for (int c = 0; c < M; c++) {
								if(visited[r][c]) delete[r][c] = true;
							}
						}
					}
					
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(delete[i][j]) {
						map[i][j] = '.';
						isCrushed = true;
					}
				}
			}
			
			for (int j = 0; j < M; j++) {
				while(true) {
					int top = -1;
					int bottom = -1;
					for (int i = N-1; i >=0; i--) {
						if(map[i][j] == '.') {
							bottom = i;
							break;
						}
					}
					for (int i = bottom; i >=0; i--) {
						if(map[i][j] != '.') {
							top = i;
							break;
						}
					}
					
					if(top == -1) break;
					char tmp = map[top][j];
					map[top][j] = map[bottom][j];
					map[bottom][j] = tmp;
					
				}
			}	

			if(!isCrushed) break;
			combo +=1;
		}
		
		System.out.println(combo);
	}
}
