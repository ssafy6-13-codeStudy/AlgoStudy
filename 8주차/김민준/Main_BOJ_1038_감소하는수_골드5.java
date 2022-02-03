package week8;

import java.util.Scanner;

//시간 216ms
//메모리 17900kb
//코드길이 1057b
public class Main_BOJ_1038_감소하는수_골드5{
	static int N;
	static int num = 10;
	static int count;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		count = 0;
		for (int i = 1; i <= num; i++) {
			perm(0, new int[i], new boolean[10], i);
		}
		System.out.println(-1);
	}

	private static void perm(int cnt, int[] nums, boolean[] v, int end) {
		if(cnt == end) {
			count++;
			if(count-1 == N) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < nums.length; i++) {
					sb.append(nums[i]);
				}
				System.out.println(sb.toString());
				System.exit(0);
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if(v[i]) continue;
			v[i] = true;
			if(cnt > 0) {
				if(nums[cnt-1] > i) { // 앞자리보다 작다면 perm
					nums[cnt] = i;
					perm(cnt+1,nums,v,end);
				}
			}else {
				nums[cnt] = i;
				perm(cnt+1,nums,v,end);
			}
			v[i] = false;
		}
	}
}
