package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 80ms
//메모리 11584kb
public class Main_BOJ_1043_거짓말_골드4 {
	
	static int N, M;
	static int[] parent;
	static int[] party;
	static boolean[] isKnow;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		
		isKnow = new boolean[N+1];
		for (int i = 0; i < num; i++) {
			int temp = Integer.parseInt(st.nextToken());
			isKnow[temp] = true;
		}
		
		parent = new int[N+1];
		party = new int[M];
		init();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			party[i] = first;
			for (int j = 1; j < temp; j++) {
				union(first, Integer.parseInt(st.nextToken()));
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(!isKnow[find(party[i])]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	public static void init() {
		for (int i = 0; i < N+1; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb) return;
		if(isKnow[pa] || isKnow[pb]) {
			isKnow[pa] = true;
			isKnow[pb] = true;
		}
		parent[pb] = pa;
	}
}
