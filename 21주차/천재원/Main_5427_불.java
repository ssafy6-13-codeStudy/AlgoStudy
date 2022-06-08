// 122044kb
// 1048ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
	static int W,H;
	static char[][] Map;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static Queue<int[]> fires;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			Map = new char[H][W];
			fires = new LinkedList<>();
			int answer = Integer.MAX_VALUE;
			int r=-1,c=-1;
			int[][] visited = new int[H][W];
			for(int i=0; i<H; i++) {
				char[] buf = br.readLine().toCharArray();
				for(int j=0; j<W; j++) {
					Map[i][j] = buf[j];
					if(buf[j] == '@') {
						r=i; c=j;
						visited[i][j] = 1;
					}else if(buf[j] == '*') {
						fires.offer(new int[] {i,j});
						visited[i][j] = -1;
					}else if(buf[j] =='#') {
						visited[i][j] = -1;
					}
				}
			}
			
			Queue<Pos> que = new LinkedList<>();
			que.offer(new Pos(r,c,0));
			visited[r][c] = 1;
			while(!que.isEmpty()) {
				
				// 불
				for(int i=0, end = fires.size(); i<end; i++) {
					int cur[] = fires.poll();
					for(int d=0; d<4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr>=0 && nr<H && nc>=0 && nc<W && visited[nr][nc] != -1) {
							visited[nr][nc] = -1;
							fires.offer(new int[] {nr,nc});
						}
					}
					
				}
				
				
				// 사람
				for(int i=0, end = que.size(); i<end; i++) {
					Pos cur = que.poll();
					if(cur.r == 0 || cur.r == H-1 || cur.c == 0 || cur.c == W-1 ) {
						answer = Math.min(answer, cur.time+1);
						continue;
					}
					for(int d=0; d<4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];

						if(nr>=0 && nr<H && nc>=0 && nc<W) {
							if(visited[nr][nc] != -1 && visited[nr][nc] != 1) {
								visited[nr][nc] = 1;
								que.offer(new Pos(nr,nc,cur.time+1));
							}
						}
					}
				}
			}
			if(answer < Integer.MAX_VALUE) sb.append(answer+"\n");
			else sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb.toString());
	}
	
	public static class Pos{
		int r;
		int c;
		int time;
		
		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
