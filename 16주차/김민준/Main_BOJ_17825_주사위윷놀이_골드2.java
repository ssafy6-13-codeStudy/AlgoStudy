package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 136ms
//메모리 12872kb
public class Main_BOJ_17825_주사위윷놀이_골드2 {
	public static class Loc{
		int r;
		int c;
		
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] nums;
	static int max;
	static int[][] map = {
			{0, 2, 4, 6, 8, 10}, 
			{10, 13, 16, 19, 25}, 
			{10, 12, 14, 16, 18, 20}, 
			{20, 22, 24, 25}, 
			{20, 22, 24, 26, 28, 30}, 
			{30, 28, 27, 26, 25}, 
			{30, 32, 34, 36, 38, 40}, 
			{25, 30, 35, 40}, 
			{40, 0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		max = 0;
		boolean[][] visit = {
				{false, false, false, false, false, false}, 
				{false, false, false, false, false}, 
				{false, false, false, false, false, false}, 
				{false, false, false, false}, 
				{false, false, false, false, false, false}, 
				{false, false, false, false, false}, 
				{false, false, false, false, false, false}, 
				{false, false, false, false}, 
				{false, false}}; 
		
		Loc[] user = new Loc[4];
		for (int i = 0; i < 4; i++) {
			user[i] = new Loc(0,0);
		}
		dfs(0, 0, visit, user);
		System.out.println(max);
	}

	private static void dfs(int cnt, int sum, boolean[][] visit, Loc[] user) {
		if(cnt == 10) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			
			int br = user[i].r;
			int bc = user[i].c;
			int nr = user[i].r;
			int nc = user[i].c;
			if(br == 8 && bc == 1) continue;
			
			for (int j = 0; j < nums[cnt]; j++) {
				if(nc >= map[nr].length-1) {
					if(nr % 2 == 0 && nr < 8) {
						nr += 2;
						nc = 0;
					}else if(nr % 2 == 1 && nr < 7) {
						nr = 7;
						nc = 0;
					}else if(nr == 7) {
						nr++;
						nc = 0;
					}else if(nr == 8) {
						nc = 1;
						break;
					}
				}
				nc++;
			}
			
			if(nc == map[nr].length-1) { // 교차점에 도착
				nc = 0;
				if(nr == 6 || nr == 7) {
					nr = 8;
				}else if(nr == 8) {
					nc = 1;
				}
				else if(nr % 2 == 0) {
					nr++;
				}else if(nr % 2 == 1) {
					nr = 7;
				}
			}
			if(!(nr == 8 && nc == 1) && visit[nr][nc]) continue;
			visit[br][bc] = false;
			visit[nr][nc] = true;
			user[i].r = nr;
			user[i].c = nc;
			dfs(cnt+1, sum+map[nr][nc], visit, user);
			user[i].r = br;
			user[i].c = bc;
			visit[br][bc] = true;
			visit[nr][nc] = false;
		}
	}
}
