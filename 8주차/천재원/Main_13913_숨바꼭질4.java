import java.util.*;

public class Main_13913_¼û¹Ù²ÀÁú4 {
	
	static int N, K;
	static int time[] = new int[100001];
	static int parent[] = new int[100001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		Queue<Integer> que = new LinkedList<>();
		que.add(N);
		time[N] = 1;
		while(!que.isEmpty()) {
			int cur = que.poll();
			if(cur == K) break;
			for(int i=0; i<3; i++) {
				int next =0;
				
				switch(i) {
					case 0: next = cur +1; break;
					case 1: next = cur -1; break;
					case 2: next = cur*2; break;
				}
				
				if(next <0 || next>100000) continue;
				
				if(time[next] == 0) {
					que.add(next);
					time[next] = time[cur] +1;
					parent[next] = cur;
				}
			}
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int idx = K;
		while(idx != N) {
			stack.push(parent[idx]);
			idx = parent[idx];
		}
		System.out.println(time[K]-1);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		
	}

}
