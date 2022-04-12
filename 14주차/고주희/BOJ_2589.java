import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 464ms
// 메모리 : 262924KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int max = -1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='L') {
					int tmp = bfs(map, i, j);
                    if(max<tmp) max = tmp;
				}
			}
		}
		System.out.println(max);
	}

	private static int bfs(int[][] map, int r, int c) {

		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		int R = map.length;
		int C = map[0].length;
		int max = 0;
		boolean[][] v = new boolean[R][C];
		v[r][c] = true;
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r,c,0});
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				if(nr>=R || nc>=C || nr<0 || nc<0 || v[nr][nc] || map[nr][nc]=='W') continue;
				v[nr][nc] = true;
				max = cur[2]+1;
				que.offer(new int[] {nr,nc, cur[2]+1});
			}
		}
		return max;
	}

}
