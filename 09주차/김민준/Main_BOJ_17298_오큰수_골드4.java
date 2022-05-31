package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


//시간 1140ms
//메모리 143864kb
//코드길이 1091b
public class Main_BOJ_17298_오큰수_골드4 {
	static int N;
	static int[] list;
	static Stack<Integer> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			while(!stack.isEmpty() && list[stack.peek()] < list[i]) {
				int temp = stack.pop();
				list[temp] = list[i];
			}
			stack.push(i);
		}
		
		while(!stack.empty()) {
			list[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(list[i]);
			sb.append(' ');
		}
		System.out.println(sb.toString());
	}
}
