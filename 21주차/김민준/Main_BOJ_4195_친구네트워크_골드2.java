package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//시간 608ms
//메모리 65292kb
public class Main_BOJ_4195_친구네트워크_골드2 {
	static int[] parant;
	static int[] rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());
			
			// init
			HashMap<String, Integer> map = new HashMap<>();
			parant = new int[F*2];
			rank = new int[F*2];
			int idx = 0;
			for (int i = 0; i < F*2; i++) {
				parant[i] = i;
				rank[i] = 1;
			}
			
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) {
					map.put(a, idx);
					idx++;
				}
				if(!map.containsKey(b)) {
					map.put(b, idx);
					idx++;
				}
				
				// union
				int idxa = map.get(a);
				int idxb = map.get(b);
				union(idxa, idxb);
				sb.append(rank[parant[idxa]]).append("\n");
			}			
		}
		System.out.println(sb.toString());
	}
	
	public static int find(int a) {
		if(parant[a] == a) return a;
		return parant[a] = find(parant[a]);
	}
	
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb) return;
		parant[pb] = pa;
		rank[pa] += rank[pb];
	}
}
