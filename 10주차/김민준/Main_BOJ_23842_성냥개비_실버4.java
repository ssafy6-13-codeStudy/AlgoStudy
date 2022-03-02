package week10;

import java.util.Scanner;

//시간 372ms
//메모리 24332kb
//코드길이 978b
public class Main_BOJ_23842_성냥개비_실버4 {
	static int N;
	static int[] num = {6,2,5,5,4,5,6,3,7,6};
	static String[] list = {"0","1","2","3","4","5","6","7","8","9"};
	static String result;
	static boolean check;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		N -= 4;
		result = "impossible";
		check = false;
		if(N > 0) {
			perm(0, new int[6], N);
		}
		System.out.println(result);
	}
	
	public static void perm(int cnt, int[] nums, int remain) {
		if(cnt == 6) {
			if(remain != 0 || check) return;
			String a = list[nums[0]] + list[nums[1]];
			String b = list[nums[2]] + list[nums[3]];
			String sum = list[nums[4]] + list[nums[5]];
			if(Integer.parseInt(a) + Integer.parseInt(b) == Integer.parseInt(sum)) {
				result = a+"+"+b+"="+sum;
				check = true;
			}
			return;
		}
		for(int i = 0; i < 10; i++) {
			if(remain - num[i] >= 0) {
				nums[cnt] = i;
				perm(cnt+1, nums, remain - num[i]);
			}
		}
	}
}
