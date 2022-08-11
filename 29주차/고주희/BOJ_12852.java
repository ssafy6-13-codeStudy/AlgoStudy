import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// 시간 : 100ms
// 메모리 : 19276KB
public class Main {

	static String answer;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		Arrays.fill(dp, 987654321);
		
		answer = "";
		dfs(dp, 0, N, new int[1000001]);
		System.out.println(dp[1]);
		System.out.println(answer);
	}

	private static void dfs(int[] dp, int cnt, int prev, int[] sb) {

		if(cnt >= dp[1]) return;
		if(prev==1) {
			if(dp[1] > cnt) {
				dp[1] = cnt;
				sb[cnt] = prev;
				StringBuilder s = new StringBuilder();
				for(int i = 0; i <= cnt; i++) {
					s.append(sb[i]).append(" ");
				}
				answer = s.toString();
			}
			return;
		}
		
		if(dp[prev] <= cnt) return;
		else dp[prev] = cnt;
		
		sb[cnt] = prev;
		if(prev%2==0) {
			dfs(dp, cnt+1, prev/2, sb);
		}
		if(prev%3==0) {
			dfs(dp, cnt+1, prev/3, sb);
		}
		dfs(dp, cnt+1, prev-1, sb);
	}

}
