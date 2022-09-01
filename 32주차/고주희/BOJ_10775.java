import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// 시간 : 208ms
// 메모리 : 21728KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int[] prev = new int[G+1];
		for (int i = 0; i <= G; i++) {
			prev[i] = i;
		}
		int cnt = 0;
		for (int i = 0; i < P; i++) {
			int gate = Integer.parseInt(br.readLine());
			int p = find(prev, gate);
			if(p == 0) {
				System.out.println(cnt);
				return;
			}
			union(p - 1, p, prev);
			cnt++;
		}
		System.out.println(cnt);
		
	}

	private static void union(int k, int p, int[] prev) {
		int kp = find(prev, k);
		int pp = find(prev, p);
		if(kp != pp) {
			prev[pp] = kp;
		}
	}
	private static int find(int[] prev, int gate) {

		if(gate == prev[gate]) return gate;
		return prev[gate] = find(prev, prev[gate]);
	}

}
