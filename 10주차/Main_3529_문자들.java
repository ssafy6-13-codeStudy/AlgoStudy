import java.util.*;
import java.io.*;

public class Main_3529_문자들 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, max;
	static char[][] map;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			char[] buf = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = buf[j];
			}
		}
		dfs(0,0,0, new boolean[R][C], new boolean['Z'-'A'+1]);
		System.out.println(max);
	}
	private static void dfs(int r, int c,int cnt, boolean[][] visited, boolean[] alpha) {
		if(r == 0 && c == 0) {
			visited[r][c] = true;
			alpha[map[r][c]-'A'] = true;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >=0 && nr <R && nc >= 0 && nc < C) {
				if(!visited[nr][nc] && !alpha[map[nr][nc]-'A']) {
					visited[nr][nc] = true;
					alpha[map[nr][nc]-'A'] = true;
					dfs(nr, nc, cnt+1, visited, alpha);
					visited[nr][nc] = false;
					alpha[map[nr][nc]-'A'] = false;
				}
			}
		}
		
		max = Math.max(cnt+1, max);
		
	}
}
