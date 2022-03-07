import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 80ms
// 메모리 : 11772KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] parent = new int[N];
		int[] rank = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, parent, rank);
		}
		
		System.out.println(rank[0]-1);
	}

	private static void union(int x, int y, int[] parent, int[] rank) {

		int px = find(x, parent);
		int py = find(y, parent);
		
		if(px==py) return;
		if(px<py) {
			parent[py] = px;
			rank[px] += rank[py];
		}else {
			parent[px] = py;
			rank[py] += rank[px];
		}
	}

	private static int find(int x, int[] parent) {
        int px = parent[x];
		if(x==px) return x;
		else return parent[x] = find(px, parent);
	}

}
