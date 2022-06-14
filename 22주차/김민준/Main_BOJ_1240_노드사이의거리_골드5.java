package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 284ms
//메모리 49544kb
public class Main_BOJ_1240_노드사이의거리_골드5{
	static class Node{
		int node;
		int weight;
		
		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	
	static int N, M;
	static List<Node>[] list;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, weight));
			list[end].add(new Node(start, weight));
		}
		
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			find(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void find(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visit = new boolean[N];
		q.add(new Node(start, 0));
		visit[start] = true;
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			for (Node n : list[temp.node]) {
				if(visit[n.node]) continue;
				if(n.node == end) {
					sb.append(temp.weight + n.weight).append("\n");
					return;
				}
				visit[n.node] = true;
				q.add(new Node(n.node, temp.weight + n.weight));
			}
		}
	}
}
