package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 200ms
// 메모리 : 29908KB
public class BOJ_21610 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dr = {0,-1,-1,-1,0,1,1,1};
		int[] dc = {-1,-1,0,1,1,1,0,-1};
		int[] cross = {1,3,5,7};
		Queue<int[]> que = new LinkedList<>();
		
		que.offer(new int[] {N,1});
		que.offer(new int[] {N,2});
		que.offer(new int[] {N-1,1});
		que.offer(new int[] {N-1,2});
		
		int total = 0;
		for (int i = 0; i < M; i++) {
			boolean[][] v = new boolean[N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken())%N;
			
			Queue<int[]> nque = new LinkedList<>();
			
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				int r = cur[0]+dr[d]*s, c=cur[1]+dc[d]*s;
				if(r>=N+1) r -= N;
				if(c>=N+1) c -= N;
				if(c<=0) c += N;
				if(r<=0) r += N;
				v[r][c] = true;
				map[r][c]++;
				nque.offer(new int[] {r,c});
			}
			for (int[] js : nque) {
				for (int cr : cross) {
					int nr = js[0]+dr[cr];
					int nc = js[1]+dc[cr];
					if(nr<1 || nc<1 || nr>N || nc>N) continue;
					if(map[nr][nc]>=1) map[js[0]][js[1]]++;
				}
			}
			for (int j = 1; j <= N; j++) {
				for (int l = 1; l <= N; l++) {
					if(map[j][l]>=2 && !v[j][l]) {
						que.offer(new int[] {j,l});
						map[j][l]-=2;
					}
					if(i==M-1) total+=map[j][l];
				}
			}
		}
		System.out.println(total);
	}

}
