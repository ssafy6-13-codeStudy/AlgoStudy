import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 120ms
// 메모리 : 13572KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			boolean[][] map = new boolean[N][M];
			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = true;
			}
			int[] dr = {-1,0,1,0};
			int[] dc = {0,-1,0,1};
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]) {
						Queue<int[]> que = new LinkedList<int[]>();
						que.offer(new int[] {i, j});
						map[i][j] = false;
						while(!que.isEmpty()) {
							int[] cur = que.poll();
							for (int d = 0; d < 4; d++) {
								int nr = cur[0] + dr[d];
								int nc = cur[1] + dc[d];
								if(nr<0 || nc<0 || nr>=N || nc>=M || !map[nr][nc]) continue;
								map[nr][nc] = false;
								que.offer(new int[] {nr, nc});
							}
						}
						count++;
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

}
