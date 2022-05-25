package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 120ms
//메모리 11912kb
public class Main_BOJ_1062_가르침_골드4 {
	static int N, K;
	static int[] list;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
			
		list = new int[N];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < temp.length(); j++) {
				list[i] |= 1 << (temp.charAt(j)-'a');
			}
		}
		
		if(K < 5) {
			System.out.println(0);
		}else if(K == 26){
			System.out.println(N);
		}else {
			max = 0;
			int words = 0;
			words |= 1 << ('a' - 'a');
			words |= 1 << ('n' - 'a');
			words |= 1 << ('t' - 'a');
			words |= 1 << ('i' - 'a');
			words |= 1 << ('c' - 'a');
			dfs(0, 0, words);
			System.out.println(max);
		}
	}
	
	public static void dfs(int cnt, int start, int words) {
		if(cnt == K-5) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if((list[i] & words) == list[i]) {
					count++;
				}
			}
			max = Math.max(max, count);
			return;
		}
		for (int i = start; i < 26; i++) {
			if((words & (1 << i)) != 0) continue;
			dfs(cnt+1, i+1, words | 1 << i);
		}
	}

}
