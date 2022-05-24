// 메모리 18152 kb
// 시간 368 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.validation.Validator;

public class Main_BOJ_2580_스도쿠 {
	static final int N = 9;
	static int blankCnt ;
	static int[][] sdoku = new int[9][9];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				sdoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
	}
	private static void dfs(int r, int c) {
		if(c == 9) {
			dfs(r+1,0);
			return;
		}
		
		if(r==9) {
			for(int i=0; i<N; i++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<N; j++) {
					sb.append(sdoku[i][j]+" ");
				}
				System.out.println(sb.toString());
			}
			System.exit(0);
		}
		
		if(sdoku[r][c] == 0) {
			for(int i=1; i<=9; i++ ) {
				if(check(r,c,i)) {
					sdoku[r][c] = i;
					dfs(r,c+1);
				}
			}
			sdoku[r][c] = 0;
			return;
		}
		
		dfs(r,c+1);
		
	}
	private static boolean check(int r, int c, int v) {
		
		for(int i=0; i<9; i++) {
			if(sdoku[r][i] == v) return false;
			if(sdoku[i][c] == v) return false;
		}
		
		
		int div_r = r/3*3;
		int div_c = c/3*3;
		
		for(int i=div_r; i<div_r+3; i++) {
			for(int j=div_c; j<div_c+3; j++) {
				if(sdoku[i][j] == v) return false;
			}
		}
		
		
		return true;
	}
}
