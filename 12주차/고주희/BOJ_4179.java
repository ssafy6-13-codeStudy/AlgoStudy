package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 560ms
// 메모리 : 74412KB
public class BOJ_4179 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		Queue<int[]> jque = new LinkedList<int[]>();
		Queue<int[]> fire = new LinkedList<>();
		boolean[][] v = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='J') {
					jque.offer(new int[] {i, j, 0});
					v[i][j] = true;
				}else if(map[i][j]=='F') {
					fire.offer(new int[] {i,j});
				}
			}
		}
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		int min = Integer.MAX_VALUE;
		
		
		while(!jque.isEmpty()) {
			int len = fire.size();
			for (int i = 0; i < len; i++) {
				int[] f = fire.poll();
				for (int d = 0; d < 4; d++) {
					int nr = f[0] + dr[d];
					int nc = f[1] + dc[d];
					if(nr>=R || nc>=C || nr<0 || nc<0) continue;
					if(map[nr][nc]=='#' || map[nr][nc]=='F') continue;
					map[nr][nc] = 'F';
					fire.offer(new int[] {nr,nc});
				}
			}
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			int jlen = jque.size();
			for (int i = 0; i < jlen; i++) {
				int[] cur = jque.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr>=R || nc>=C || nr<0 || nc<0) {
						min = Math.min(min, cur[2]+1);
						continue;
					}
					if(map[nr][nc]=='#' || map[nr][nc]=='F') continue;
					if(v[nr][nc]) continue;
					v[nr][nc] = true;
//					System.out.println("jihun");
//					System.out.println(nr+" "+nr);
					jque.offer(new int[] {nr,nc,cur[2]+1});
				}
			}
			
			
			
		}
		if(min==Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
		else System.out.println(min);
	}

}
