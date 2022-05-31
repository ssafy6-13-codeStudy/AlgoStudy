
import java.util.Scanner;
//이해x subset가능
public class Main_BOJ_14501_퇴사_실버3 {
	static int N;
	static int[] T, P;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = new int[N+1];
		P = new int[N+1];
		dp = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		for (int i = N-1; i >= 0; i--) {
			int next = i + T[i];
			if(next > N) {
				dp[i] = dp[i+1];
			}else {
				dp[i] = Math.max(dp[i+1],dp[next]+P[i]);
			}
		}
		System.out.println(dp[0]);
	}
}
