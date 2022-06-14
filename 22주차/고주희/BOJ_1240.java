import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 444ms
// 메모리 : 64088KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] list = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken())-1;
			int child = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			list[parent].add(new int[] {child, weight});
			list[child].add(new int[] {parent, weight});
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			
			Queue<int[]> que = new LinkedList<>();
			que.offer(new int[] {start, 0});
			
			boolean[] v = new boolean[N];
			v[start] = true;
			
			int answer = 0;
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				if(cur[0]==end) {
					answer = cur[1];
					break;
				}
				for (int[] j : list[cur[0]]) {
                    if(v[j[0]]) continue;
                    v[j[0]] = true;
					que.offer(new int[] {j[0], j[1]+cur[1]});
				}
			}
			System.out.println(answer);
		}
	}

}
