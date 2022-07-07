package week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 76ms
//메모리 11560kb
public class Main_BOJ_1111_IQTest_골드3 {
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N == 1) {
			System.out.println("A");
		}else if(N == 2) {
			if(nums[0] == nums[1]) {
				System.out.println(nums[0]);
			}else {
				System.out.println("A");
			}
		}else {
			int a;
			int b;
			
			if(nums[0] == nums[1]) {
				a = 1;
				b = 0;
			}else {
				a = (nums[2] - nums[1]) / (nums[1] - nums[0]);
				b = nums[1] - (nums[0]*a);
			}
			
			boolean flag = true;
			for (int i = 1; i < N; i++) {
				if(nums[i] != (nums[i-1]*a + b)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				System.out.println(nums[N-1]*a + b);
			}else {
				System.out.println("B");
			}
		}
	}
}
