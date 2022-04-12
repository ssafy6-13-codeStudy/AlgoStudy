import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 316ms
// 메모리 : 71352KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dr = {-2,-2,-1,-1,1,1,2,2};
		int[] dc = {-1,1,-2,2,-2,2,-1,1};
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean[][] v = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			Queue<int[]> que = new LinkedList<>();
			que.offer(new int[] {sr, sc, 0});
			v[sr][sc] = true;
			int min = Integer.MAX_VALUE;
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				int r = cur[0], c = cur[1], cnt = cur[2];
				if(r==er && c==ec) {
					min = cnt;
					break;
				}
				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= N || nc >= N || nr<0 || nc<0 || v[nr][nc]) continue;
					v[nr][nc] = true;
					que.offer(new int[] {nr,nc, cnt+1});
				}
			}
			System.out.println(min);
		}
	}

}
