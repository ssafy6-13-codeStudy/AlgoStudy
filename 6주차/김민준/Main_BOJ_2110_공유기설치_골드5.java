package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 ms
// 메모리 kb
// 코드길이 b
// 어려움

public class Main_BOJ_2110_공유기설치_골드5 {
	static int N, C;
	static int[] house;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		System.out.println(bs());
	}

	private static int bs() {
		int left = 1;
		int right = house[N-1]-house[0]+1;
		int mid;
		
		while(left < right) {
			mid = (left + right) / 2; // 거리
			
			int cnt = 1; // 첫번째 집에는 항상 설치
			int last = house[0];
			for (int i = 1; i < N; i++) {
				if(house[i]-last > mid) {
					cnt++;
					last = house[i]; 
				}
			}
			
			if(C <= cnt) {
				left = mid+1; // 거리를 늘려서 더 최소 cnt ( >= C) 에서 최대 거리까지 되도록 계산
			}else {
				right = mid; // 거리를 줄여서 cnt가 더 찍히도록 함
			}
		}
		
		return left;
	}
}
