// 84784kb
// 932ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_4195_친구네트워크 {
	static int N, K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] parent, level;
	static Map<String, Integer> friendMap;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int n=0; n<N; n++) {
			int idx = 0;
			K = Integer.parseInt(br.readLine());
			friendMap = new HashMap<>();
			parent = new int[K*2];
			level = new int[K*2];
			for(int i=0; i<K*2; i++) {
				parent[i] = i;
				level[i] = 1;
			}
			
			for(int k=0; k<K; k++) {

				st = new StringTokenizer(br.readLine());
				String src = st.nextToken();
				String dst = st.nextToken();
				if(!friendMap.containsKey(src)) {
					friendMap.put(src, idx++);
				}
				if(!friendMap.containsKey(dst)) {
					friendMap.put(dst, idx++);
				}
				sb.append((union(friendMap.get(src), friendMap.get(dst)))+ "\n");
			}
		}
		System.out.print(sb.toString());
	}
	private static int union(int u, int v) {
		int x = find(u);
		int y = find(v);
		
		if(x!=y) {
			parent[y] = x;
			level[x] += level[y];
			
			level[y] = 1;
		}
		
		return level[x];
		
	}
	private static int find(int u) {
		
		if(u == parent[u]) return u;
		
		return parent[u] = find(parent[u]);
	}
}
