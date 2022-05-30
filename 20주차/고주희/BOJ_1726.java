import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 96ms
// 메모리 : 12616KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken())-1;
		int sc = Integer.parseInt(st.nextToken())-1;
		int sd = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken())-1;
		int ec = Integer.parseInt(st.nextToken())-1;
		int ed = Integer.parseInt(st.nextToken())-1;
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr, sc, sd, 0, 0});
		boolean[][][] v = new boolean[M][N][4];
		v[sr][sc][sd] = true;
		
		int[] dr = {0,0,1,-1};
		int[] dc = {1,-1,0,0};
		int answer = Integer.MAX_VALUE;
		int ad = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0], c = cur[1];
			if(r==er && c==ec && cur[2]==ed) {
				int tmp = cur[3];
				if(answer>tmp) answer = tmp;
                
			}
			int d = 0;
			if(cur[2]==0 || cur[2]==1) {
				d = 2;
			}
			if(!v[r][c][d]) {
				v[r][c][d] = true;
				que.offer(new int[] {r, c, d, cur[3]+1});
			}
			d += 1;
			if(!v[r][c][d]) {
				v[r][c][d] = true;
				que.offer(new int[] {r, c, d, cur[3]+1});
			}
			
			int nr = r + dr[cur[2]];
			int nc = c + dc[cur[2]];
			if(nr<0 || nc <0 || nr>=M || nc>=N) continue;
			if(map[nr][nc]==1) continue;
			if(!v[nr][nc][cur[2]]) {
                v[nr][nc][cur[2]] = true;
                que.offer(new int[] {nr, nc, cur[2], cur[3]+1});
            }
            nr += dr[cur[2]];
			nc += dc[cur[2]];
			if(nr<0 || nc <0 || nr>=M || nc>=N) continue;
			if(map[nr][nc]==1) continue;
			if(!v[nr][nc][cur[2]]) {
                v[nr][nc][cur[2]] = true;
                que.offer(new int[] {nr, nc, cur[2], cur[3]+1});
            }
			nr += dr[cur[2]];
			nc += dc[cur[2]];
			if(nr<0 || nc <0 || nr>=M || nc>=N) continue;
			if(map[nr][nc]==1) continue;
			if(!v[nr][nc][cur[2]]) {
                v[nr][nc][cur[2]] = true;
                que.offer(new int[] {nr, nc, cur[2], cur[3]+1});
            }
		}
		System.out.println(answer);
		
	}

}
