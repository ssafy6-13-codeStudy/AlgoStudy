import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 : 728ms
// 메모리 : 53352KB
public class Main {

	static class Soldier implements Comparable<Soldier>{
		int start;
		int end;
		
		public Soldier(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Soldier o) {
			int n = Integer.compare(this.start, o.start);
			if(n!=0) return n;
			else return Integer.compare(this.end, o.end);
		}
		
	}
	
	static class Computer{
		int index;
		int endtime;
		int count;
		
		public Computer(int index, int endtime, int count) {
			super();
			this.index = index;
			this.endtime = endtime;
			this.count = count;
		}	
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Soldier[] soldier = new Soldier[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			soldier[i] = new Soldier(s, e);
		}
		
		Arrays.sort(soldier);
		
		
		PriorityQueue<Computer> que = new PriorityQueue<>(new Comparator<Computer>() {

			@Override
			public int compare(Computer o1, Computer o2) {
				return Integer.compare(o1.endtime, o2.endtime);
			}
			
		});
		PriorityQueue<Computer> tmp = new PriorityQueue<>(new Comparator<Computer>() {

			@Override
			public int compare(Computer o1, Computer o2) {
				return Integer.compare(o1.index, o2.index);
			}
			
		});
		int cnt = 0;
		for (Soldier s : soldier) {
			while(!que.isEmpty()) {
				if(que.peek().endtime<s.start) {
					tmp.offer(que.poll()); // 현재 앉을 수 있는 곳
				}else break;
			}
			if(tmp.isEmpty()) { // 앉을 수 있는 곳이 없다
				que.offer(new Computer(cnt++, s.end, 1)); // 새 컴 삼
			}else { // 앉을 수 있는 곳이 있다.
				Computer t = tmp.poll();
				t.endtime = s.end;
				t.count++;
				que.offer(t); // 끝나는 시간 변경해서 que에 다시 넣어줌
			}
		}
		while(!que.isEmpty()) {
			tmp.offer(que.poll());
		}
		StringBuilder sb = new StringBuilder();
		sb.append(tmp.size()).append("\n");
		while(!tmp.isEmpty()) {
			Computer computer = tmp.poll();
			sb.append(computer.count).append(" ");
		}
		System.out.println(sb);
	}

}
