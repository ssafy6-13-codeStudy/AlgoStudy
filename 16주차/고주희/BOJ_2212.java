import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 : 164ms
// 메모리 : 15112KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] highway = new int[N];
		
		for (int i = 0; i < N; i++) {
			highway[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(highway);
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for (int i = 1; i < N; i++) {
			que.offer(highway[i]-highway[i-1]);
		}
		
		int total = 0;
		if(que.size()>K-1) {
			while(que.size()!=K-1) {
				total += que.poll();
			}
		}
		
		System.out.println(total);
		
	}

}
