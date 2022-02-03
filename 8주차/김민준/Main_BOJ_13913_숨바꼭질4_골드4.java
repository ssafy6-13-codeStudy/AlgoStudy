package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 1088ms
//메모리 38464kb
//코드길이 1567b
public class Main_BOJ_13913_숨바꼭질4_골드4{
	static int N, K;
	static boolean[] visit;
	static int[] parent;
	static int num = 100000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[num+1];
		parent = new int[num+1];
		
		bfs();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visit[N] = true;
		parent[N] = N;
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(temp == K) {
				int i = temp;
				ArrayList<Integer> list = new ArrayList<>();
				while(i != N) {
					list.add(i);
					i = parent[i];
				}
				list.add(N);
				System.out.println(list.size()-1);
				for (int j = list.size()-1; j >= 0; j--) {
					System.out.print(list.get(j)+" ");
				}
				System.out.println();
				return;
			}
			
			if(temp+1 <= num && !visit[temp+1]) {
				q.add(temp+1);
				visit[temp+1] = true;
				parent[temp+1] = temp;
			}
			if(temp-1 >= 0 && !visit[temp-1]) {
				q.add(temp-1);
				visit[temp-1] = true;
				parent[temp-1] = temp;
			}
			if(temp*2 <= num && !visit[temp*2]) {
				q.add(temp*2);
				visit[temp*2] = true;
				parent[temp*2] = temp;
			}
		}
	}
}
