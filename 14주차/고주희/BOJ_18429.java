import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 84ms
// 메모리 : 11628KB
public class Main {

	static int count;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] exercise = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			exercise[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
		perm(0, new boolean[N], 500, exercise, K);
		System.out.println(count);
	}

	private static void perm(int cnt, boolean[] v, int power, int[] exercise, int K) {

		int N = v.length;
		if(power<500) return;
		if(cnt==N) {
			if(power>=500) count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(v[i]) continue;
			v[i] = true;
			perm(cnt+1, v, power+exercise[i]-K, exercise, K);
			v[i] = false;
		}
	}

}
