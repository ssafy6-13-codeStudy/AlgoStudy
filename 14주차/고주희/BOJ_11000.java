import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 시간 : 656ms
// 메모리 : 75696KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Class> map = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			map.offer(new Class(s, t));
		}
		PriorityQueue<Integer> room = new PriorityQueue<>();
        room.offer(map.poll().end);
		while(!map.isEmpty()) {
            Class i = map.poll();
			if(room.peek()<=i.start) {
				room.poll();
			}
			room.offer(i.end);
		}
		System.out.println(room.size());
	}
	
	static class Class implements Comparable<Class>{
		int start;
		int end;
		public Class(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Class o) {
			int n = Integer.compare(this.start, o.start);
			return n;
		}
	}
}
