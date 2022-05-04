package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 76ms
//메모리 11592kb
public class Main_BOJ_1041_주사위_골드5 {
	static int N;
	static int[] dice; // 위 우 전 후 좌 아래
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dice = new int[6];
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N == 1) {
			int sum = 0;
			int max = 0;
			for (int i = 0; i < 6; i++) {
				sum += dice[i];
				max = Math.max(max, dice[i]);
			}
			System.out.println(sum-max);
		}else {
			long sum = 0;
			long one = findLittle1();
			long two = findLittle2();
			long three = findLittle3();
			
			sum += three*4 + two*(N-2)*4 + one*(N-2)*(N-2);
			sum += two*(N-1)*4 + one*(N-1)*(N-2)*4;
			System.out.println(sum);
		}
	}
	
	public static int findLittle1() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			min = Math.min(min, dice[i]);
		}
		return min;
	}
	
	public static int findLittle2() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			boolean[] visit = new boolean[6];
			visit[i] = true;
			visit[findReverse(i)] = true;
			for (int j = 0; j < 6; j++) {
				if(visit[j]) continue;
				min = Math.min(min, dice[i]+dice[j]);
			}
		}
		return min;
	}
	
	public static int findLittle3() {
		int min = 150;
		for (int i = 0; i < 6; i++) {
			boolean[] visit = new boolean[6];
			visit[i] = true;
			visit[findReverse(i)] = true;
			for (int j = 0; j < 6; j++) {
				if(visit[j]) continue;
				boolean[] visit2 = new boolean[6];
				visit2[j] = true;
				visit2[findReverse(j)] = true;
				for (int k = 0; k < 6; k++) {
					if(visit[k] || visit2[k]) continue;
					min = Math.min(min, dice[i]+dice[j]+dice[k]);
				}
			}
		}
		return min;
	}
	
	public static int findReverse(int d) {
		int reverse = 0;
		if(d == 0) {
			reverse = 5;
		}else if(d == 1) {
			reverse = 4;
		}else if(d == 2) {
			reverse = 3;
		}else if(d == 3) {
			reverse = 2;
		}else if(d == 4) {
			reverse = 1;
		}else {
			reverse = 0;
		}
		return reverse;
	}
}
