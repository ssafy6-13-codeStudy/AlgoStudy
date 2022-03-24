import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 288ms
// 38928KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new LinkedList<>();
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			int num = str.charAt(i)-'0';
			while(K>0 &&!dq.isEmpty() && dq.peekLast()< num) {
				K--;
				dq.removeLast();
			}
			dq.offerLast(num);
		}
		for (int i = 0; i < K; i++) {
			dq.removeLast();
		}
		StringBuilder sb = new StringBuilder();
		for (Integer integer : dq) {
			sb.append(integer);
		}
		System.out.println(sb.toString());
	}

}
