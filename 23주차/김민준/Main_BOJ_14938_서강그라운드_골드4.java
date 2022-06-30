package week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간 92ms
//메모리 12148kb
public class Main_BOJ_14938_서강그라운드_골드4 {
	
	static class Node implements Comparable<Node>{
		int end;
		int len;
		
		public Node(int end, int len) {
			this.end = end;
			this.len = len;
		}

		@Override
		public int compareTo(Node o) {
			return this.len - o.len;
		}
	}
	
	static int n, m, r;
	static int[] map;
	static List<Node>[] list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n];
		list = new ArrayList[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int len = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, len));
			list[end].add(new Node(start, len));
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, cal(i));
		}
		
		System.out.println(max);
	}
	
	public static int cal(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		int[] dist = new int[n];
		boolean[] visit = new boolean[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(visit[temp.end]) continue;
			visit[temp.end] = true;
			
			for (Node node : list[temp.end]) {
				if(dist[node.end] > dist[temp.end] + node.len) {
					dist[node.end] = dist[temp.end] + node.len;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < n; i++) {
			if(dist[i] <= m) {
				result += map[i];
			}
		}
		
		return result;
	}
}
