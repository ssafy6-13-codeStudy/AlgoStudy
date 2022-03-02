package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 294ms
//메모리 42392kb
public class Main_JO_3529_문자들 {
	static int R, C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] map;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		cnt = 0;
		boolean[] alpha = new boolean['Z'-'A'+1];
		boolean[][] visit = new boolean[R][C];
		alpha[map[0][0]-'A'] = true;
		visit[0][0] = true;
		dfs(0,0,alpha, visit, String.valueOf(map[0][0]));
		System.out.println(cnt);
	}
	
	public static void dfs(int r, int c, boolean[] alpha, boolean[][] visit, String word) {
		cnt = Math.max(cnt, word.length());
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if(visit[nr][nc] || alpha[map[nr][nc]-'A']) continue;
			visit[nr][nc] = true;
			alpha[map[nr][nc]-'A'] = true;
			dfs(nr,nc,alpha,visit,word+map[nr][nc]);
			visit[nr][nc] = false;
			alpha[map[nr][nc]-'A'] = false;
		}
	}
}
