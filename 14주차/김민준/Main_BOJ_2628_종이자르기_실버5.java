package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 124ms
//메모리 14200kb
public class Main_BOJ_2628_종이자르기_실버5 {
	static int N, M;
	static int cut;
	static List<Integer> listN = new ArrayList<>();
	static List<Integer> listM = new ArrayList<>();
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		max = 0;
		
		listN.add(0);
		listM.add(0);
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		listN.add(N);
		listM.add(M);
		
		cut = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < cut; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int loc = Integer.parseInt(st.nextToken());
			if(d == 0) {
				listN.add(loc);
			}else {
				listM.add(loc);
			}
		}
		
		Collections.sort(listM);
		Collections.sort(listN);
		
		for(int i = 0; i < listM.size()-1; i++) {
			int m = listM.get(i+1) - listM.get(i);
			for (int j = 0; j < listN.size()-1; j++) {
				int n = listN.get(j+1) - listN.get(j);
				max = Math.max(max, m*n);
			}
		}
		
		System.out.println(max);
	}
}
