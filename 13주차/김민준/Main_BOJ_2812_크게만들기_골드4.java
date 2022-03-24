package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 424ms
//메모리 39004kb
public class Main_BOJ_2812_크게만들기_골드4 {
	static int N, K;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stack = new Stack<>();
		char[] num = br.readLine().toCharArray();
		int[] list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = num[i]-'0';
		}
		int cnt = 0;
		stack.push(list[0]);
		for (int i = 1; i < N; i++) {
			if(cnt < K && stack.peek() < list[i]) {
				while(cnt < K && !stack.isEmpty()) {
					if(stack.peek() < list[i]) {
						stack.pop();
						cnt++;
					}else{
						break;
					}
				}
			}
			stack.push(list[i]);
		}
		
		if(cnt < K) {
			for (int i = cnt; i < K; i++) {
				stack.pop();
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}
		System.out.println(sb.toString());
	}
}
