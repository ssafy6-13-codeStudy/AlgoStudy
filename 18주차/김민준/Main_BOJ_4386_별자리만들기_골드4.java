package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//시간 100ms
//메모리 12608kb
public class Main_BOJ_4386_별자리만들기_골드4 {
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		double weight;
		
		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static int N;
	static double[][] map;
	static List<Edge> list;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		map = new double[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Double.parseDouble(st.nextToken());
			map[i][1] = Double.parseDouble(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				double distance = Math.sqrt(Math.pow(Math.abs(map[i][0] - map[j][0]), 2) + Math.pow(Math.abs(map[i][1] - map[j][1]), 2)); 
				list.add(new Edge(i,j,distance));
			}
		}
		
		Collections.sort(list);
		
		init();
		double sum = 0;
		int cnt = 0;
		for (Edge e : list) {
			if(union(e.start, e.end)) {
				sum += e.weight;
				cnt++;
				if(cnt >= N-1) {
					break;
				}
			}
		}
		System.out.printf("%.2f",sum);
	}
	
	public static void init() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb) return false;		
		parent[pb] = pa;

		return true;
	}
}
