// 시간 544 ms
// 메모리 294624kb
// 풀이시간 40분


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
	static int N,L,R;
	static int country[][];
	static boolean visited[][] ;
	static BufferedReader br;
	static Queue<int[]> que;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		country = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		boolean flag = true;
		while(flag) {
			flag = false;			
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						que = new LinkedList<int[]>();
						int sum = bfs(i,j);
						if(que.size() > 1) {
							int avg = sum / que.size();
							for (int[] cur : que) {
								country[cur[0]][cur[1]] = avg;
							}
							flag =true;
						}
					}
				}
			}
			if(!flag) break;
			time +=1;
		}
		
		System.out.println(time);
		
		
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	private static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		que = new LinkedList<>();
		que.offer(new int[] {r,c});
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		int sum = country[r][c];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(nr>=0 && nr <N && nc>= 0 && nc < N && !visited[nr][nc]) {
					int tmp = Math.abs(country[cur[0]][cur[1]] - country[nr][nc]);
					if(tmp >= L && tmp <= R) {
						q.offer(new int[] {nr,nc});
						que.offer(new int[] {nr,nc});
						sum += country[nr][nc];
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		
		return sum;
	}
}
