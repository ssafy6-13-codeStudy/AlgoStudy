// 시간 124ms

import java.util.Scanner;

public class Main_2661_좋은수열 {
	static int N ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		perm("");
	}
	
	public static void perm(String str) {
		if(str.length() == N) {
			System.out.println(str);
			System.exit(0);
			return;
		}
		for(int i=1; i<= 3; i++) {
			if(make_str(str+i)) perm(str+i);
		}
	}
	
	public static boolean make_str(String str) {
		int len = str.length();
		for (int i = 1; i <= len/2; i++) {
			if(str.substring(len-(i*2), len-i).equals(str.substring(len-i, len))) {
				return false;
			}
		}
		
		
		return true;
	}
	
}
