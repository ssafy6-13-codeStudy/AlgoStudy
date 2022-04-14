package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 536ms
//메모리 294992kb
public class Main_BOJ_18429_근손실_실버3 {
	static int N, K;
	static int[] kit;
	static int count;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kit = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		perm(0, new boolean[N], 500);
		System.out.println(count);
		
	}

	private static void perm(int cnt, boolean[] visit, int weight) {
		if(cnt == N) {
			count++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visit[i]) continue;
			if(weight - K + kit[i] < 500) continue;
			visit[i] = true;
			perm(cnt+1, visit, weight - K + kit[i]);
			visit[i] = false;
		}
	}
}
