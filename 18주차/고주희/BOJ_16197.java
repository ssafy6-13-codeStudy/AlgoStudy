import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 196ms
// 메모리 : 39336KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[][] coins = new int[2][2];
		int co = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char tmp = str.charAt(j);
				if(tmp=='o') {
					map[i][j] = 2;
					coins[co][0] = i;
					coins[co++][1] = j;
				}
				else if(tmp=='#') map[i][j] = 1;
			}
		}
		System.out.println(bfs(coins, map));
	}

	private static int bfs(int[][] coins, int[][] map) {
		int[] dr = {0,0,-1,1};
		int[] dc = {-1,1,0,0};
		int N = map.length;
		int M = map[0].length;
		int[][] v = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {coins[0][0], coins[0][1], coins[1][0], coins[1][1], 0});
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r1 = cur[0];
			int c1 = cur[1];
			int r2 = cur[2];
			int c2 = cur[3];
			int cnt = cur[4];
			if(cnt>10) return -1;
			for (int d = 0; d < 4; d++) {
				int nr1 = r1 + dr[d];
				int nc1 = c1 + dc[d];
				int nr2 = r2 + dr[d];
				int nc2 = c2 + dc[d];
				if(!checked(nr1, nc1, map) && !checked(nr2, nc2, map)) {
					continue;
				}
				else if(!checked(nr1, nc1, map) || !checked(nr2, nc2, map)) {
					return cnt+1 > 10? -1 : cnt+1;
				}
				if(map[nr1][nc1]==1 && map[nr2][nc2]==1) {
					continue;
				}
				else if(map[nr1][nc1]==1) {
					que.offer(new int[] {r1, c1, nr2, nc2, cnt+1});
				}
				else if(map[nr2][nc2]==1) {
					que.offer(new int[] {nr1, nc1, r2, c2, cnt+1});
				}
				else que.offer(new int[] {nr1, nc1, nr2, nc2, cnt+1});
			}
		}
		return -1;
	}

	private static boolean checked(int nr, int nc, int[][] map) {
		int N = map.length;
		int M = map[0].length;
		return nr<N && nc<M && nr>=0 && nc>=0;
	}

}
