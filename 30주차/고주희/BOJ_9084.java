import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 92ms
// 메모리 : 12752KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] coins = new int[N+1];
			for (int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[][] cnt = new int[N+1][M+1];
			sb.append(knapsack(coins, cnt)).append("\n");
		}
		System.out.println(sb);
	}

	private static int knapsack(int[] coins, int[][] cnt) {

		int N = cnt.length;
		int M = cnt[0].length;
		
		for (int i = 0; i < N; i++) {
			cnt[i][0] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if(j - coins[i] >= 0) {	
					cnt[i][j] += cnt[i][j - coins[i]] + cnt[i-1][j];
				} else cnt[i][j] += cnt[i-1][j];
			}
		}
		return cnt[N-1][M-1];
	}

}
