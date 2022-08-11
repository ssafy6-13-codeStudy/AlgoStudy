import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 172ms
// 메모리 : 51468KB
public class Main {

	static class Thing implements Comparable<Thing> {
		int weight;
		int value;
		
		public Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		public int compareTo(Thing o) {
			int n = Integer.compare(this.weight, o.weight);
			if(n!=0) return n;
			else return Integer.compare(this.value, o.value);
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Thing[] things = new Thing[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			things[i] = new Thing(weight, value);
		}
		
		Arrays.sort(things);
		
		int[][] map = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(j-things[i-1].weight < 0) {
					map[i][j] = map[i-1][j];
					continue;
				}
					if(map[i-1][j-things[i-1].weight] + things[i-1].value > map[i-1][j]) {
						map[i][j] = map[i-1][j-things[i-1].weight] + things[i-1].value;
					} else {
						map[i][j] = map[i-1][j];
					}
			}
		}
		
		System.out.println(map[N][K]);
		
	}

}
