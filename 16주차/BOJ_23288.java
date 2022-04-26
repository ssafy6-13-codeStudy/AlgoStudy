import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 196ms
// 메모리 : 33892KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		int[] dice = {1, 2, 3, 4, 5, 6}; // 위, 북, 동, 서, 남, 아래
		int d = 3;
		int dicer = 0;
		int dicec = 0;
		
		for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j*2)-'0';
			}
		}
		int total = 0;
		for (int t = 0; t < K; t++) {
			int nr = dicer + dr[d];
			int nc = dicec + dc[d];
			if(nr>=N || nc >= M || nr<0 || nc<0) {
				d+=2;
				if(d>=4) d %= 4;
				nr = dicer + dr[d];
				nc = dicec + dc[d];
			}
			if(d==0) {
				int tmp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = dice[1];
				dice[1] = tmp;
			}else if(d==1) {
				int tmp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = dice[3];
				dice[3] = tmp;
			}else if(d==2) {
				int tmp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[4];
				dice[4] = tmp;
			}else {
				int tmp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = dice[2];
				dice[2] = tmp;
			}
			Queue<int[]> que = new LinkedList<>();
			que.offer(new int[] {nr, nc});
			boolean[][] v = new boolean[N][M];
			v[nr][nc] = true;
			int score = 1;
			int standard = map[nr][nc];
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				for (int k = 0; k < 4; k++) {
					int kr = cur[0] + dr[k];
					int kc = cur[1] + dc[k];
					if(kr>=N || kc >= M || kr<0 || kc<0 || v[kr][kc] || map[kr][kc]!=standard) continue;
					score++;
					v[kr][kc] = true;
					que.offer(new int[] {kr, kc});
				}
			}
			total += score*standard;
			
			if(dice[5]>standard) {
				d -= 1;
				if(d==-1) d = 3;
			}else if(dice[5]<standard) {
				d += 1;
				if(d==4) d = 0;
			}
			
			dicer = nr;
			dicec = nc;
		}
		System.out.println(total);
	}

}
