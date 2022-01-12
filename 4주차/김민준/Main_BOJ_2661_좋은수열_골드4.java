package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//시간 168ms
//메모리 16364kb
//코드길이 1115b

public class Main_BOJ_2661_좋은수열_골드4 {
	static int N;
	static boolean end;
	static String result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		end = false;
		backtracking(0,"");
		System.out.println(result);
	}

	private static void backtracking(int cnt, String str) {
		if(end) {
			return;
		}
		if(cnt == N) {
			end = true;
			result = str;
			return;
		}
		for (int i = 1; i <= 3; i++) {
			if(!check(str+i)) {
				backtracking(cnt+1, str+i);
			}
		}
	}
	
	// 숫자를 추가하면 그 수열이 좋은 수열인지 검사 -> 좋은 수열일 때만 계속 돌아감
	private static boolean check(String str) {
		for (int i = 1; i <= str.length()/2; i++) {
			String a = str.substring(str.length()-2*i, str.length()-i);
			String b = str.substring(str.length()-i, str.length());
			if(a.equals(b)) {
				return true;
			}
		}
		return false;
	}
}
