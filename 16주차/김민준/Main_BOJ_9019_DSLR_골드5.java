package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 2472ms
//메모리 299920kb
public class Main_BOJ_9019_DSLR_골드5 {
	static class DSLR{
		int num;
		String order;
		
		public DSLR(int num, String order){
			this.num = num;
			this.order = order;
		}
	}
	
	static int T;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			dfs(start, end);
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int start, int end) {
		Queue<DSLR> q = new LinkedList<>();
		q.add(new DSLR(start, ""));
		boolean[] visit = new boolean[10000];
		visit[start] = true;
		
		while(!q.isEmpty()) {
			DSLR temp = q.poll();
			
			// D
			int D = temp.num*2;
			if(D > 9999) {
				D = D%10000;
			}
			if(!visit[D]) {
				if(D == end) {
					sb.append(temp.order+"D"+"\n");
					return;
				}
				q.add(new DSLR(D, temp.order+"D"));
				visit[D] = true;
			}
			
			// S
			int S = temp.num-1;
			if(temp.num == 0) {
				S = 9999;
			}
			if(!visit[S]) {
				if(S == end) {
					sb.append(temp.order+"S"+"\n");
					return;
				}
				q.add(new DSLR(S, temp.order+"S"));
				visit[S] = true;
			}
			
			// L
			int L = (temp.num%1000)*10 + temp.num/1000;
			if(!visit[L]) {
				if(L == end) {
					sb.append(temp.order+"L"+"\n");
					return;
				}
				q.add(new DSLR(L, temp.order+"L"));
				visit[L] = true;
			}
			
			// R
			int R = (temp.num%10)*1000 + temp.num/10;
			if(!visit[R]) {
				if(R == end) {
					sb.append(temp.order+"R"+"\n");
					return;
				}
				q.add(new DSLR(R, temp.order+"R"));
				visit[R] = true;
			}
		}
	}
}
