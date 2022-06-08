import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 시간 : 76ms
// 메모리 : 11608KB
public class Main {

	public static void main(String[] args) throws IOException {

		char[][] map = new char[8][8];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int sr = 7;
		int sc = 0;
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {sr, sc, 0});
		int[] dr = {-1,-1,-1,0,0,0,1,1,1};
		int[] dc = {-1,0,1,-1,0,1,-1,0,1};
		
		int time = 0;
		int answer = 0;
		boolean[][] v = new boolean[8][8];
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			if(cur[0]==0 && cur[1]==7) {
				answer = 1;
				break;
				
			}
			if(cur[2]!=time) {
				time++;
				move_wall(map);
				v = new boolean[8][8];
			}
			if(map[cur[0]][cur[1]]=='#') continue;
			for (int d = 0; d < 9; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr>=8 || nc >= 8 || nr < 0 || nc < 0) continue;
				if(map[nr][nc]=='#' || v[nr][nc]) continue;
				v[nr][nc] = true;
				que.offer(new int[] {nr, nc, cur[2]+1});
			}
			
		}
		System.out.println(answer);
	}

	private static void move_wall(char[][] map) {

		for (int i = 7; i > 0; i--) {
			for (int j = 0; j < 8; j++) {
				map[i][j] = map[i-1][j];
			}
		}
		
		for (int i = 0; i < 8; i++) {
			map[0][i] = '.';
		}
	}

}
