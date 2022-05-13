package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간 280ms
//메모리 15680kb
public class Main_BOJ_2580_스도쿠_골드4 {
	static class Loc{
		int r;
		int c;
		
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N = 9;
	static int[][] map = new int[N][N];
	static boolean[][] visitS = new boolean[N][N+1];
	static boolean[][] visitR = new boolean[N][N+1];
	static boolean[][] visitC = new boolean[N][N+1];
	static List<Loc> list;
	static int len;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					list.add(new Loc(i,j));
				}else {
					int r = (i/3)*3;
					int c = (j/3);
					visitS[r+c][map[i][j]] = true;
					visitR[j][map[i][j]] = true;				
					visitC[i][map[i][j]] = true;				
				}
			}
		}
		
		len = list.size();
		dfs(0);
	}
	private static void dfs(int cnt) {
		if(cnt == len) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
			return;
		}
		Loc temp = list.get(cnt);
		int r = (temp.r/3)*3;
		int c = temp.c/3;
		for (int i = 1; i <= 9; i++) {
			if(visitS[r+c][i]) continue;
			if(visitR[temp.c][i]) continue;
			if(visitC[temp.r][i]) continue;
			visitS[r+c][i] = true;
			visitR[temp.c][i] = true;
			visitC[temp.r][i] = true;
			map[temp.r][temp.c] = i;
			dfs(cnt+1);
			visitS[r+c][i] = false;
			visitR[temp.c][i] = false;
			visitC[temp.r][i] = false;
		}
	}
}
