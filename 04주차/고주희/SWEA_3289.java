package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 486ms
// 메모리 : 113508KB

public class SWEA_3289 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] parent = new int[N];
			int[] rank = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				
				if(op==0) {
					union(a,b,parent, rank);
				}else {
					int xa = find(a, parent);
					int xb = find(b, parent);
					sb.append(xa==xb? 1:0);
				}
			}
			System.out.println("#"+t+" "+sb.toString());
		}
	}

	private static void union(int a, int b, int[] parent, int[] rank) {

		int xa = find(a, parent);
		int xb = find(b, parent);
		if(xa==xb) return;
		if(rank[xa]>=rank[xb]) {
			parent[xb] = xa;
			rank[xa]++;
		}else {
			parent[xa] = xb;
			rank[xb]++;
		}
	}

	private static int find(int a, int[] parent) {
		if(parent[a]==a) return a;
		else return parent[a] = find(parent[a], parent);
	}

}
