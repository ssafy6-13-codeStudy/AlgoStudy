import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 1712ms
// 메모리 : 372948KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j]-'0';
			}
		}
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {0,0,1,K});
		boolean[][][] v = new boolean[N][M][K+1];
		v[0][0][K] = true;
		
		int answer = -1;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
            if(cur[0]==N-1 && cur[1]==M-1) {
					answer = cur[2];
					break;
				}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr >= N || nc >= M || nr < 0 || nc < 0) continue;
				if(map[nr][nc]==1) {
					if(cur[3]==0) continue;
					if(v[nr][nc][cur[3]-1]) continue;
					v[nr][nc][cur[3]-1] = true;
					que.offer(new int[] {nr, nc, cur[2]+1, cur[3]-1});
				} else {
					if(v[nr][nc][cur[3]]) continue;
					v[nr][nc][cur[3]] = true;
					que.offer(new int[] {nr, nc, cur[2]+1, cur[3]});
				}
			}
		}
		System.out.println(answer);
	}

}
