import java.io.*;
import java.util.*;

public class Main_16948_데스나이트 {
	static int map[][];
	static int N, sr,sc,er,ec;
	static BufferedReader br;
	static int result = -1;
	static int dr[]= {-2,-2,0,0,2,2};
	static int dc[] = {-1,1,-2,2,-1,1};
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {sr,sc,0});
		boolean visited[][] = new boolean[N][N];
		visited[sr][sc] = true;
		outer :
		while(!que.isEmpty()) {
			int cur[] = que.poll();
			
			for (int i = 0; i < dr.length; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(!(nr>=0 && nr<N && nc>= 0 && nc <N) || visited[nr][nc]) continue;
				if(nr == er && nc == ec) {
					result = cur[2]+1;
					break outer;
				}
				else {
					que.add(new int[] {nr,nc,cur[2]+1});
					visited[nr][nc] = true;
				}
			}
		}
		
		System.out.println(result);
	}
}
