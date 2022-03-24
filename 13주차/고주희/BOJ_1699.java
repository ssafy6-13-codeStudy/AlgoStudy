import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1636ms
// 11568KB
public class Main {

	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		count = Integer.MAX_VALUE;
		dfs(0, N);
		System.out.println(count);
	}
	private static void dfs(int cnt, int n) {

		if(count<=cnt) return;
		if(n==0) {
            count = cnt;
			return;
		}
		int sqrtN = (int)Math.sqrt(n);
		for (int j = sqrtN; j >= 1; j--) {
			dfs(cnt+1, n-j*j);
		}
	}

}
