import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 184ms
// 메모리 : 17996KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<int[]> oil = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			oil.offer(new int[] {loc, w});
		}
		st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		
		// 기름 => 양을 기준으로 정렬되어 있음
		int loc = L;
		int cnt = 0;
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		while(true) {
			
			if(loc >= P) {
				break;
			}
			while(!oil.isEmpty() && oil.peek()[0] <= loc) {
				que.offer(oil.poll());
			}
			
			if(que.isEmpty()) {
				cnt = -1;
				break;
			}
			
			cnt++;
			loc += que.poll()[1];
		}
		System.out.println(cnt);
	}

}
