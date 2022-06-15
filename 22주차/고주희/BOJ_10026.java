import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 시간 : 104ms
// 메모리 : 13920KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = str[j];
			}
		}
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		//v[0] 적록색약, v[1] 아님
		boolean[][] v = new boolean[N][N];
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(v[i][j]) continue;
				count++;
				Queue<int[]> que = new LinkedList<>();
				que.offer(new int[] {i, j});
				v[i][j] = true;
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr >= N || nc >= N || nr < 0 || nc < 0) continue;
						if(v[nr][nc] || map[nr][nc]!=map[i][j]) continue;
						v[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
		}
		System.out.print(count+" ");
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='G') map[i][j] = 'R';
			}
		}
		
		v = new boolean[N][N];
		count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(v[i][j]) continue;
				count++;
				Queue<int[]> que = new LinkedList<>();
				que.offer(new int[] {i, j});
				v[i][j] = true;
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr >= N || nc >= N || nr < 0 || nc < 0) continue;
						if(v[nr][nc] || map[nr][nc]!=map[i][j]) continue;
						v[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
			}
		}
		System.out.println(count);
	}

}
