package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//시간 3064ms
//메모리 315716kb
public class Main_BOJ_1946_신입사원_실버1 {
	public static class Comp implements Comparator<int[]>{
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
		}
	}
	static int T, N;
	static int[][] scores;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			scores = new int[N][2];
			boolean[] check = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				scores[i][0] = a;
				scores[i][1] = b;
			}
			
			Arrays.sort(scores, new Comp());
			
			int cnt = 1;
			int rank = scores[0][1];
			for(int i = 1; i < N; i++) {
				if(rank > scores[i][1]) {
					rank = scores[i][1];
					cnt++;
				}
			}
			
			System.out.println(cnt);
		}
	}
}
