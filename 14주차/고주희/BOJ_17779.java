import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 432ms
// 메모리 : 47072KB
public class BOJ_17779 {

	static int gerrymandering;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][]map = new int[N+1][N+1];
		
		int all = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				all +=map[i][j];
			}
		}
		
		gerrymandering = Integer.MAX_VALUE;
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N; j++) {
				findds(i, j, map, all);
			}
		}
		System.out.println(gerrymandering);
		
	}

	private static void findds(int y, int x, int[][] map, int all) {

		int N = map.length-1;
		boolean flag = false;
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		for (int d1 = 1; d1 <= N-x; d1++) {
			
			for (int d2 = 1; d2 <= N-d1-x; d2++) {
				if(y-d1<1 || y+d2>N) {
					break;
				}
				flag = true;
				boolean[][] v = new boolean[N+1][N+1];
				int[] total = new int[5];
				Queue<int[]> que = new LinkedList<>();
				for (int i = 0; i <= d1; i++) {
						v[y-i][x+i] = true;
						v[y+d2-i][x+d2+i] = true;
						if(d1>d2 && i!=0) {
							que.offer(new int[] {y-i+1, x+i});
							v[y-i+1][x+i] = true;
						}
				}
				for (int i = 0; i <=d2 ; i++) {
						v[y+i][x+i] = true;
						v[y-d1+i][x+d1+i] = true;
						if(d2>d1 && i!=d2) {
							que.offer(new int[] {y-d1+i+1, x+d1+i});
							v[y-d1+i+1][x+d1+i] = true;
						}
				}
				if(d1==d2) {
					que.offer(new int[] {y, x+1});
					v[y][x+1] = true;
				}
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr>N || nr<1 || nc>N || nc<1 || v[nr][nc]) continue;
						v[nr][nc] = true;
						que.offer(new int[] {nr, nc});
					}
				}
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if(v[j][i]) {
							total[4]+=map[j][i];
							continue;
						}
						if(i<=x+d1 && j<y) { // 1구역
							if(v[j][i]) continue;
							total[0]+=map[j][i];
						}else if(i<x+d2 && j>=y && j<=N) { // 3구역
							if(v[j][i]) continue;
							total[1]+=map[j][i];
						}else if(i>=x+d1 && i<=N && j<=y-d1+d2) { //2구역
							if(v[j][i]) continue;
							total[2]+=map[j][i];
						}else if(i>=x+d2 && i<=N && y-d1+d2<=j && j<=N) { //4구역
							if(v[j][i]) continue;
							total[3]+=map[j][i];
						}
					}
				}
				if(flag) {
					int max = -1;
					int min = Integer.MAX_VALUE;
					for (int i : total) {
						if(max<i) max = i;
						if(min>i) min = i;
					}
					gerrymandering = Math.min(max-min, gerrymandering);
					flag = false;
				}
			}
		}
		return;
	}

}

