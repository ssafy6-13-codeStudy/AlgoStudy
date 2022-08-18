import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 740ms
// 메모리 : 139284KB
public class Main {

    static int[] parent;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		int answer = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(union(start, end)) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
		
	}

	private static boolean union(int start, int end) {

		int ps = find(start);
		int pe = find(end);
		if(ps==pe) return true;
		parent[pe] = ps;
		return false;
	}

	private static int find(int x) {

		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

}
