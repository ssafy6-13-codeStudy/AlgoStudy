package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간 892ms
//메모리 70908kb
public class Main_BOJ_12764_싸지방에간준하_골드3 {
	public static class Computer{
		int starttime;
		int endtime;
		int idx;
		
		public Computer(int starttime, int endtime, int idx) {
			this.starttime = starttime;
			this.endtime = endtime;
			this.idx = idx;
		}
	}
	static int N;
	static int[][] people;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		people = new int[N][2];
		int[] cnt = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			people[i][0] = Integer.parseInt(st.nextToken()); 
			people[i][1] = Integer.parseInt(st.nextToken()); 					
		}
		
		// 빨리 시작하는 순으로 정렬
		Arrays.sort(people, (int[] o1, int[] o2) -> o1[0] - o2[0]);
		
		PriorityQueue<Computer> comq = new PriorityQueue<>((Computer o1, Computer o2) -> o1.endtime - o2.endtime); // 사용중
		PriorityQueue<Integer> idxq = new PriorityQueue<>(); // 사용가능 자리
		int idx = 0;
		for (int i = 0; i < N; i++) {
			while(!comq.isEmpty()) {
				Computer temp = comq.peek();
				if(temp.endtime < people[i][0]) {
					idxq.add(temp.idx);
					comq.poll();
				}else {
					break;
				}
			}
			
			if(!idxq.isEmpty()) {
				int temp = idxq.poll();
				comq.add(new Computer(people[i][0], people[i][1], temp));
				cnt[temp]++;
			}else {
				comq.add(new Computer(people[i][0], people[i][1], idx));
				cnt[idx]++;
				idx++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx+"\n");
		for (int i = 0; i < idx; i++) {
			sb.append(cnt[i]+" ");
		}
		System.out.println(sb.toString());
	}
}
