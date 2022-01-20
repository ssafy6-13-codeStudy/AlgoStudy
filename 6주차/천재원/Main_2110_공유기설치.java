// 시간초과 

import java.util.Arrays;
import java.util.Scanner;

public class Main_2110_공유기설치2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		int[] house = new int[N];
		for (int i = 0; i < N; i++) {
			house[i] = sc.nextInt();
		}
		Arrays.sort(house);
		int left = 1;
		int right = house[N-1]-house[0];
		int distance = 0;
		int min = 0;
		while(left <= right) {
			int mid= (left + right ) /2;
			int start = house[0];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				distance = house[i] - start;
				if(distance >= mid) {
					cnt +=1; 
					start = house[i];
					
				}
			}
			if(cnt >= C) {
				min = mid;
				left = min +1;
			}else {
				right = mid-1;
			}
		}
		System.out.println(min);
		
		
	}
}
