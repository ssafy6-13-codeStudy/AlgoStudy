// 16520kb
// 132ms

import java.util.*;
import java.io.*;

public class Main_6593_상범빌딩_골드5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int L,R,C;
	static char[][][] building;
	static Position src,dst;
	static int answer = Integer.MAX_VALUE;
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		
		while(true) {
			answer = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L ==0 && R==0 && C==0) break;
			
			building = new char[L][R][C];

			for(int i=0; i<L; i++) {
				for(int j=0; j<R; j++) {
					char[] buf = br.readLine().toCharArray();
					for(int k=0; k<C; k++) {
						char c = buf[k];
						if(c == 'S') {
							src = new Position(i,j,k,0);
						}else if(c=='E') {
							dst = new Position(i,j,k,0);
						}
						building[i][j][k] = c;
					}
				}
				br.readLine();
			}
			boolean visited[][][] = new boolean[L][R][C];
			visited[src.l][src.r][src.c] = true;
			bfs();
			StringBuilder sb = new StringBuilder();
			if(answer < Integer.MAX_VALUE) {
				sb.append("Escaped in ");
				sb.append(answer);
				sb.append(" minute(s).");
			}else {
				sb.append("Trapped!");
			}
			System.out.println(sb.toString());
		}
	}
	
	
	
	private static void bfs() {
		Queue<Position> que = new LinkedList<>();
		que.offer(src);
		boolean[][][] visited = new boolean[L][R][C];
		visited[src.l][src.r][src.c] = true;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			
			if(cur.l == dst.l && cur.r == dst.r && cur.c == dst.c) {
				answer = cur.cnt;
				return;
			}
			
			if(cur.l != L-1 && building[cur.l+1][cur.r][cur.c] != '#') {
				
				if(cur.l+1 == dst.l && cur.r == dst.r && cur.c == dst.c) {
					answer = cur.cnt+1;
					return;
				}
				
				if(!visited[cur.l+1][cur.r][cur.c]) {
					visited[cur.l][cur.r][cur.c]= true;
					que.offer(new Position(cur.l+1, cur.r, cur.c, cur.cnt+1));
				}
			}
			
			if(cur.l != 0 && building[cur.l-1][cur.r][cur.c] != '#') {
				
				if(cur.l+1 == dst.l && cur.r == dst.r && cur.c == dst.c) {
					answer = cur.cnt+1;
					return;
				}
				
				if(!visited[cur.l-1][cur.r][cur.c]) {
					visited[cur.l][cur.r][cur.c]= true;
					que.offer(new Position(cur.l-1, cur.r, cur.c, cur.cnt+1));
				}
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr>=0 && nr<R && nc >=0 && nc<C && building[cur.l][nr][nc] != '#') {
					
					if(cur.l == dst.l && nr == dst.r && nc == dst.c) {
						answer = cur.cnt+1;
						return;
					}
					
					if(!visited[cur.l][nr][nc]) {
						visited[cur.l][nr][nc] = true;
						que.offer(new Position(cur.l, nr,nc, cur.cnt+1));
					}
				}
			}
			
			
		}
	}

	public static class Position {
		int l;
		int r;
		int c;
		int cnt ;
		
		public Position(int x, int y, int z, int cnt) {
			this.l = x;
			this.r = y;
			this.c = z;
			this.cnt = cnt ;
		}
	}
}
