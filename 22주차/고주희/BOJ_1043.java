import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 80ms
// 메모리 : 11904KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		boolean[] tp = new boolean[N];
		
		for (int t = 0; t < T; t++) {
			int p = Integer.parseInt(st.nextToken())-1;
			tp[p] = true;
		}
		
		List<Integer>[] list = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				list[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(tp[i]) {
				Queue<Integer> que = new LinkedList<>();
				que.offer(i);
				boolean[] v = new boolean[N];
				
				while(!que.isEmpty()) {
					int cur = que.poll();
					for (int j = 0; j < M; j++) {
						if(list[j].contains(cur)) {
							for (Integer b : list[j]) {
								if(tp[b] || v[b]) continue;
								tp[b] = true;
								v[b] = true;
								que.offer(b);
							}
						}
					}
				}
			}
		}
		int count = 0;
		for (int i = 0; i < M; i++) {
			boolean flag = false;
			for (Integer l : list[i]) {
				if(tp[l]) flag = true;
			}
			if(!flag) count++;
		}
		
		System.out.println(count);
		
	}

}
