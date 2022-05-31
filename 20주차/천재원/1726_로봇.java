import java.util.*;
import java.io.*;

// 메모리 11252 kb
// 시간 96ms


public class Main_1726_로봇 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st ;
	static int M,N;
	static int dr[] = {0,0,1,-1};
	static int dc[] = {1,-1,0,0};
	static boolean map[][] ;
	static Position src, dst;
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().equals("1") ? true : false;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		src = new Position(Integer.parseInt(st.nextToken())-1,
				Integer.parseInt(st.nextToken())-1,
				0,
				Integer.parseInt(st.nextToken())-1);
		
		st = new StringTokenizer(br.readLine());
		dst = new Position(Integer.parseInt(st.nextToken())-1,
				Integer.parseInt(st.nextToken())-1,
				0,
				Integer.parseInt(st.nextToken())-1);
		
		bfs();	
	}
	
	private static void bfs() {
		Queue<Position> que = new LinkedList<>();
		que.offer(src);
		boolean visited[][][] = new boolean[M][N][4];
		visited[src.x][src.y][src.dir] = true;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			
			if(cur.x == dst.x && cur.y == dst.y && cur.dir == dst.dir) {
				System.out.println(cur.cnt);
				return;
			}
			
			int r = cur.x;
			int c = cur.y;
			int d = cur.dir;
			int p = cur.cnt;
		
			
			if( d == 0 || d == 1) { // 동서
					if(!visited[r][c][2]) {
						que.offer(new Position(r,c,p+1,2));
						visited[r][c][2] = true;
					}
					if(!visited[r][c][3]) {
						que.offer(new Position(r,c,p+1,3));
						visited[r][c][3] = true;
					}
			}else { // 남북
					if(!visited[r][c][0]) {
						que.offer(new Position(r,c,p+1,0));
						visited[r][c][0] = true;
					}
					if(!visited[r][c][1]) {
						que.offer(new Position(r,c,p+1,1));
						visited[r][c][1] = true;
					}
			}
			
			
			for(int i=1; i<=3; i++) {
				int nr = r + dr[d] * i;
				int nc = c + dc[d] * i;
				
				if(!(nr>=0 && nr<M && nc>=0 && nc<N)) continue;
				if(map[nr][nc]) break;
				if(visited[nr][nc][d]) continue;
				
				visited[nr][nc][d] = true;
				que.offer(new Position(nr,nc,p+1, d));
				
			}
			
			
			
		}
		
	}

	public static class Position{
		int x;
		int y;
		int cnt;
		int dir;
		
		public Position(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
