package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 128ms
//메모리 14828kb
public class Main_BOJ_2212_센서_골드5 {
	static int N, K;
	static int[] sensor;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		sensor = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {	
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensor);
		
		int[] distance = new int[N-1];
		for (int i = 1; i < N; i++) {
			distance[i-1] = sensor[i] - sensor[i-1]; 
		}
		Arrays.sort(distance);
		int sum = 0;
		for (int i = 0; i < N-K; i++) {
			sum += distance[i];
		}
		
		System.out.println(sum);
	}
}
