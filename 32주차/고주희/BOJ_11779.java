import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 시간 : 352ms
// 메모리 : 46256KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		for(int i = 0; i <= N; i++) {
			Arrays.fill(map[i], 987654321);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(map[start][end] > weight) {
				map[start][end] = weight;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		int answer = dijkstra(start, end, map, list);
		StringBuilder sb = new StringBuilder();
		sb.append(answer).append("\n");
		sb.append(list.size()).append("\n");
		for (int i = list.size() - 1; i >= 0; i--) {
			sb.append(list.get(i)).append(" ");
		}
		System.out.println(sb);
	}

	private static int dijkstra(int start, int end, int[][] map, List<Integer> list) {

		int N = map.length - 1;
		int[] dist = new int[N + 1];
		boolean[] v = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			dist[i] = map[start][i];
		}
		int[] prev = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			prev[i] = start;
		}
		v[start] = true;
		for (int i = 1; i <= N - 2; i++) {
			int n = findSmallest(dist, v);
			v[n] = true;
			for (int j = 1; j <= N; j++) {
				if(dist[j] > dist[n] + map[n][j]) {
					dist[j] = dist[n] + map[n][j];
					prev[j] = n;
				}
			}
		}
		int p = end;
		while(p != start) {
			list.add(p);
			p = prev[p];
		}
		list.add(p);
		return dist[end];
	}

	private static int findSmallest(int[] dist, boolean[] v) {

		int N = dist.length;
		int min = Integer.MAX_VALUE;
		int mini = -1;
		
		for (int i = 1; i < N; i++) {
			if(v[i]) continue;
			if(min > dist[i]) {
				min = dist[i];
				mini = i;
			}
		}
		return mini;
	}

}
