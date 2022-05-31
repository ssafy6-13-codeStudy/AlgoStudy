package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시간 : 1796ms
// 메모리 : 191188KB

public class BOJ_15661 {

	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] power = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		min = Integer.MAX_VALUE;
		subset(1, new boolean[N], power);
		System.out.println(min);
	}

	private static void subset(int cnt, boolean[] v, int[][] power) {

		int N = power.length;
		if(cnt==N) {
			List<Integer> start = new ArrayList<Integer>();
			List<Integer> link = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(v[i]) start.add(i);
				else link.add(i);
			}
			min = Math.min(min, Math.abs(exp(start, power)-exp(link, power)));
			return;
		}
		
		v[cnt] = true;
		subset(cnt+1, v, power);
		v[cnt] = false;
		subset(cnt+1, v, power);
	}

	private static int exp(List<Integer> team, int[][] power) {
		
		int total = 0;
		for (Integer i : team) {
			for (Integer j : team) {
				if(i==j) continue;
				total+=power[i][j];
			}
		}
		return total;
	}

}
