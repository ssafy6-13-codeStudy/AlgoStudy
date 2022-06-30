import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 92ms
// 메모리 : 12156KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[] item = new int[N];
		List<int[]>[] adj = new ArrayList[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}
		
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 987654321);
			map[i][i] = 0;
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			map[start][end] = weight;
			map[end][start] = weight;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(map[j][k] > map[j][i] + map[i][k]) {
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}
		
		int max = -1;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= M) count+=item[j];
			}
			if(count > max) max = count;
		}
		System.out.println(max);
	}

}
