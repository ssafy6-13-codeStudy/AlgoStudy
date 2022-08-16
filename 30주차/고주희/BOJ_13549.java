import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 : 2752ms
// 메모리 : 12248KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N > K) {
			System.out.println(N-K);
			return;
		}
		int[] v = new int[100002];
		Arrays.fill(v, 987654321);
		v[N] = 0;
		boolean[] visited = new boolean[100002];
		dijkstra(N, K, v, visited);
		System.out.println(v[K]);
	}
	
	
	
	private static void dijkstra(int N, int K, int[] v, boolean[] visited) {

		int size = v.length;
		for (int i = 0; i < size; i++) {
			int n = findSmallest(v, visited);
			visited[n] = true;
			if(n==-1) return;
			if(n==K) {
				return;
			}			
			if(n * 2 < size && v[n*2] > v[n]) v[n*2] = v[n];
			if(n + 1 < size && v[n+1] > v[n] + 1) v[n+1] = v[n] + 1;
			if(n - 1 >= 0 && v[n-1] > v[n] + 1) v[n-1] = v[n] + 1;
		}
		
	}



	private static int findSmallest(int[] v, boolean[] visited) {

		int K = v.length;
		int min = Integer.MAX_VALUE;
		int loc = -1;
		for (int i = 0; i < K; i++) {
			if(visited[i]) continue;
			if(min > v[i]) {
				min = v[i];
				loc = i;
			}
		}
		return loc;
	}

}
