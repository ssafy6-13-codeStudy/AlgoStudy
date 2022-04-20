import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 88ms
// 메모리 : 11876KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dr = {0,0,-1,1};
		int[] dc = {1,-1,0,0};
		int[] dice = {0,0,0,0,0,0}; // 위 동 서 남 북 아래
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken())-1;
			int nr = x +dr[d];
			int nc = y +dc[d];
			if(nr>=N || nc>=M || nr<0 || nc<0) continue;
			x = nr; y = nc;
			if(d==0) {
				replacedice(dice, 2, 1);
			}else if(d==1) {
				replacedice(dice, 1, 2);
			}else if(d==2) {
				replacedice(dice, 3, 4);
			}else {
				replacedice(dice, 4, 3);
			}
			if(map[nr][nc]==0) {
				map[nr][nc] = dice[5];
			}else {
				dice[5] = map[nr][nc];
				map[nr][nc] = 0;
			}
			sb.append(dice[0]).append("\n");
		}
		System.out.println(sb);
	}

	private static void replacedice(int[] dice, int i, int j) {
		int tmp = dice[i];
		dice[i] = dice[5];
		dice[5] = dice[j];
		dice[j] = dice[0];
		dice[0] = tmp;		
	}

}
