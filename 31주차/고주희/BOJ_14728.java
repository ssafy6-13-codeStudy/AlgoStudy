import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 108ms
// 메모리 : 15960KB
public class Main {

	static int T;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int[] time = new int[N+1];
		int[] score = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(knapsack(time, score));
		
				
	}

	private static int knapsack(int[] time, int[] score) {

		int N = time.length;
		int[][] kn = new int[N][T+1];
		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= T; j++) {
				if(j - time[i] >= 0) {
					if(kn[i-1][j-time[i]] + score[i] > kn[i-1][j]) {
						kn[i][j] = kn[i-1][j - time[i]] + score[i];
					} else {
						kn[i][j] = kn[i-1][j];
					}
				} else {
					kn[i][j] = kn[i-1][j];
				}
			}
		}
		return kn[N-1][T];
	}

}
