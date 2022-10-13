import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 84ms
// 메모리 : 11816KB
public class Main {

	static class Fish {
		int idx;
		int dir;
		int r;
		int c;
		
		public Fish(int idx, int dir, int r, int c) {
			super();
			this.idx = idx;
			this.dir = dir;
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[4][4];
		Fish[] fish = new Fish[16];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken())-1;
				map[i][j] = idx;
				fish[idx] = new Fish(idx, dir, i, j);
			}
		}
		
		Fish zero = fish[map[0][0]];
		int d = zero.dir;
		int idx = zero.idx + 1;
		fish[map[0][0]].idx = -1;
		map[0][0] = -1;
		max = -1;
		dfs(0, 0, d, idx, map, fish);
		System.out.println(max);
	}
	static int max;
	private static void dfs(int sr, int sc, int sd, int eat, int[][] map, Fish[] fish) {

		int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc = {0, -1, -1,-1, 0, 1, 1, 1};
		
		// 복사
		int[][] tmp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		Fish[] new_fish = new Fish[16];
		for (int i = 0; i < 16; i++) {
			new_fish[i] = new Fish(fish[i].idx, fish[i].dir, fish[i].r, fish[i].c);
		}
		
		// 물고기 이동
		for (Fish fs : new_fish) {
			if(fs.idx == -1) continue;
			int r = fs.r;
			int c = fs.c;
			int dir = fs.dir;
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[(dir + i) % 8];
				int nc = c + dc[(dir + i) % 8];
				if(nr >= 4 || nc >= 4 || nr < 0 || nc < 0) continue;
				if(nr == sr && nc == sc) continue;
				fs.dir = (dir + i) % 8;
				swap(fs, nr, nc, new_fish, tmp);
				break;
			}
		}
		
		
		boolean flag = false;
		for (int k = 1; k <= 3; k++) {
			int nr = sr + dr[sd] * k;
			int nc = sc + dc[sd] * k;
			if(nr >= 4 || nc >= 4 || nr < 0 || nc < 0) continue;
			if(tmp[nr][nc] == -1) continue;
			int idx = tmp[nr][nc];
			tmp[nr][nc] = -1;
			int dir = new_fish[idx].dir;
			int eaten = eat + idx + 1;
			new_fish[idx].idx = -1;
			flag = true;
			dfs(nr, nc, dir, eaten, tmp, new_fish);
			tmp[nr][nc] = idx;
			new_fish[idx].idx = idx;
		}
		if(!flag) {
			max = Math.max(max, eat);
		}
	}
	private static void swap(Fish fs, int fr, int fc, Fish[] fishs, int[][] map) {

		int r = fs.r;
		int c = fs.c;
		int dir = fs.dir;
		
		int tmp = map[fr][fc];
		if(tmp == -1) {
			fs.r = fr;
			fs.c = fc;
			map[fr][fc] = fs.idx;
			map[r][c] = -1;
			return;
		}
		Fish fish = fishs[tmp];
		
		fs.r = fish.r;
		fs.c = fish.c;
		
		fish.r = r;
		fish.c = c;
		
		map[fish.r][fish.c] = fish.idx;
		map[fs.r][fs.c] = fs.idx;
	}

}
