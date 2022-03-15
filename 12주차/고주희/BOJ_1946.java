import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 788ms
// 메모리 : 297288KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] rank = new int[N];
			boolean[] pass = new boolean[N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				rank[Integer.parseInt(st.nextToken())-1] = Integer.parseInt(st.nextToken())-1;
			}
			int count = N;
			int m = rank[0];
			for (int i = 1; i < N; i++) {
				if(rank[i]<m) {
					m = rank[i];
				}
				else count--;
			}
			System.out.println(count);
		}
	}

}
