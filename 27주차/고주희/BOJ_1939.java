import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 : 2044ms
// 메모리 : 168108K
public class Main {

	public static class Land implements Comparable<Land>{
		int node;
		int weight;
		
		public Land(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Land o) {
			return Integer.compare(o.weight, this.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer>[] map = new HashMap[N]; 
		for(int i = 0; i < N; i++) {
			map[i] = new HashMap<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			if(map[start].containsKey(end)) {
				int w = map[start].get(end);
				if(weight > w) {
					map[start].put(end, weight);
					map[end].put(start, weight);
				}
			} else {
				map[start].put(end, weight);
				map[end].put(start, weight);
			}
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		int weight = 0;
		
		PriorityQueue<Land> que = new PriorityQueue<>();
		que.offer(new Land(start, Integer.MAX_VALUE));
		boolean[][] v = new boolean[N][N];
		v[start][start] = true;
		
		while(!que.isEmpty()) {
			Land cur = que.poll();
			for(Integer i : map[cur.node].keySet()) {
				if(v[cur.node][i]) continue;
				int w = Math.min(map[cur.node].get(i), cur.weight);
				if(w <= weight) continue;
                if(i==end) {
					weight = w;
				} else {
					que.offer(new Land(i, w));
					v[cur.node][i] = true;
				}
			}
		}
		
		System.out.println(weight);
	}

}
