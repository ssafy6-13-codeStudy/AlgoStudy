package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//시간 328ms
//메모리 49644kb
public class Main_BOJ_15683_감시_골드4 {
	public static class CCTV{
		int r;
		int c;
		int type;
		
		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	// 1 = type, 2 = bound, 3 = nums 
	static int[][][] ds = {
			{{0}},
			{{0},{1},{2},{3}},
			{{0,2},{1,3}},
			{{0,3},{1,0},{2,1},{3,2}},
			{{0,2,3},{1,3,0},{2,0,1},{3,1,2}},
			{{0,1,2,3}}	
	};
	static List<CCTV> list;
	static int len;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new CCTV(i,j,map[i][j]));
				}
			}
		}
		
		len = list.size();
		min = Integer.MAX_VALUE;
		perm(0,new int[len]);
		System.out.println(min);
	}
	
	private static void perm(int cnt, int[] nums) {
		if(cnt == len) {
			int[][] copymap = new int[N][M];
			copy(copymap);
			
			for (int i = 0; i < len; i++) {
				CCTV temp = list.get(i);
				int bound_len = ds[temp.type][nums[i]].length;
				int[] bound = ds[temp.type][nums[i]];
				for (int j = 0; j < bound_len; j++) {
					draw(dr[bound[j]], dc[bound[j]], temp, copymap);
				}
			}
			
			int result = counting(copymap);
			min = Math.min(min, result);
			
			return;
		}
		int dslen = ds[list.get(cnt).type].length;
		for (int i = 0; i < dslen; i++) {
			nums[cnt] = i;
			perm(cnt+1, nums);
		}
	}
	
	public static void draw(int dr, int dc, CCTV temp, int[][] copymap) {
		int nr = temp.r;
		int nc = temp.c;
		while(true) {
			nr += dr;
			nc += dc;
			if(!check(nr,nc)) return;
			if(map[nr][nc] == 6) return;
			if(map[nr][nc] == 0) {
				copymap[nr][nc] = -1;
			}
		}
	}

	private static void copy(int[][] copymap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}

	public static int counting(int[][] copymap) {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copymap[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
