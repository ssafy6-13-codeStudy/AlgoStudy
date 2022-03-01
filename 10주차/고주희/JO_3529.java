import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 123ms
// 메모리 : 39196KB
public class Main{

	static int count;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		int[][] letters = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				letters[i][j] = str.charAt(j);
			}
		}
		
		count = 0;
		dfs(0,0,1, new boolean[26], new boolean[R][C], letters);
		
		System.out.println(count+1);
	}

	private static void dfs(int r, int c, int cnt, boolean[] alphabet, boolean[][] v, int[][] letters) {

		alphabet[letters[0][0]-'A'] = true;
		v[0][0] = true;
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		int R = letters.length;
		int C = letters[0].length;
		
		boolean flag = false;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=R || nc >= C || nr<0 || nc<0 || v[nr][nc]) continue;
			if(alphabet[letters[nr][nc]-'A']) continue;
			alphabet[letters[nr][nc]-'A'] = true;
			v[nr][nc] = true;
			flag = true;
			dfs(nr, nc, cnt+1, alphabet, v, letters);
			alphabet[letters[nr][nc]-'A'] = false;
			v[nr][nc] = false;
		}
		
		if(flag) count = Math.max(count, cnt);
	}

}
