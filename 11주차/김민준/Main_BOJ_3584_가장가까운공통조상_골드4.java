package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간 136ms
//메모리 14256kb
//코드길이 1177b
public class Main_BOJ_3584_가장가까운공통조상_골드4 {
	static int T, N;
	static int[] parent;
	static ArrayList<Integer> resultA;
	static ArrayList<Integer> resultB;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 초기화
			parent = new int[N+1];
			Arrays.fill(parent, 0);
			
			for(int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int pa = Integer.parseInt(st.nextToken());
				int ch = Integer.parseInt(st.nextToken());
				parent[ch] = pa;
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			
			resultA = new ArrayList<>();
			resultB = new ArrayList<>();
			dfs(nodeA, new ArrayList<>(), true); 
			dfs(nodeB, new ArrayList<>(), false);
			
			if(resultA.size() >= resultB.size()) {
				LCA(resultA, resultB);
			}else {
				LCA(resultB, resultA);
			}
		}
	}
	
	private static void LCA(ArrayList<Integer> lo, ArrayList<Integer> sh) {
		int first = 0;
		int second = 0; 
		int result = 0;
		
		while (true) {
			if (lo.isEmpty() || sh.isEmpty()) {
				break;
            		}
			first = lo.get(lo.size()-1);
			lo.remove(lo.size()-1);
			second = sh.get(sh.size()-1);
			sh.remove(sh.size()-1);
			if (first != second) {
			    break;
			}
			result = first;
		}
		System.out.println(result);
	}

	private static void dfs(int node, ArrayList<Integer> list, boolean flag) {
		list.add(node);
		if(parent[node] == 0) {
			if(flag) {
				resultA = list;
			}else {
				resultB = list;
			}
			return;
		}
		dfs(parent[node], list, flag);
	}
}
