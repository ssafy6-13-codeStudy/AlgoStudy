import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 180ms
// 메모리 : 20072KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H + 2][W + 2];
			List<int[]> hole = new ArrayList<>();
			for (int i = 1; i <= H; i++) {
				String str = br.readLine();
				for (int j = 1; j <= W; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}
			boolean[] key = new boolean[26];
			String str = br.readLine();
			if(str.charAt(0) != '0') {				
				char[] tmp = str.toCharArray();
				for(char c : tmp) {
					key[c - 'a'] = true;
				}
			}
			
			int n = goIntoMap(map, key);
			System.out.println(n);
		}
	}

	private static int goIntoMap(char[][] map, boolean[] key) {

		int H = map.length;
		int W = map[0].length;
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {0,0,1});
		boolean[][] v = new boolean[H][W];
		List<int[]> notKey = new ArrayList<>();
		v[0][0] = true;
		int cnt = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr >= H || nr < 0 || nc >= W || nc < 0) continue;
				if(v[nr][nc] || map[nr][nc]=='*') continue;
				if(map[nr][nc] >= 'a') {
					key[map[nr][nc] - 'a'] = true;
					v[nr][nc] = true;
					que.add(new int[] {nr, nc});
					for (int i = notKey.size() - 1; i >= 0; i--) {
						int[] tmp = notKey.get(i);
						if(tmp[2] == map[nr][nc]-'a') {
							que.offer(new int[] {tmp[0], tmp[1]});
							notKey.remove(i);
						}
					}
					
				} else if(map[nr][nc] >= 'A') {
					if(key[map[nr][nc] - 'A']) {
						map[nr][nc] = '.';
						v[nr][nc] = true;
						que.add(new int[] {nr, nc});
					} else {
						notKey.add(new int[] {nr, nc, map[nr][nc]-'A'});
					}
				} else if(map[nr][nc]=='$') {
					map[nr][nc] = '.';
					cnt++;
					v[nr][nc] = true;
					que.add(new int[] {nr, nc});
				} else {					
					v[nr][nc] = true;
					que.add(new int[] {nr, nc});
				}
			}
		}
		return cnt;
	}

}
