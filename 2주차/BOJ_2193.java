package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 시간 : 76ms
// 메모리 : 11540KB
public class BOJ_2193 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
//		List<Integer>[] list = new ArrayList[N];
//		for (int i = 0; i < N; i++) {
//			list[i] = new ArrayList<>();
//		}
//		
//		list[0].add(1);
//		for (int i = 1; i < N; i++) {
//			for (Integer num : list[i-1]) {
//				list[i].add(num*10);
//				if((num&1)!=1) list[i].add(num*10+1); 
//			}
//		}
//		System.out.println(list[N-1].size());
		
		int[] arr = new int[N];
		arr[0] = 1;
		if(N>1)arr[1] = 1;
		for (int i = 2; i < N; i++) {
			arr[i] = arr[i-2]+arr[i-1];
		}
		
		System.out.println(arr[N-1]);
	}

}
