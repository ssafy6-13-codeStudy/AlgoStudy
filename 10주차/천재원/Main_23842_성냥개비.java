import java.util.*;
import java.io.*;

public class Main_23842_성냥개비 {
	
	static int[] number = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6}; 
	static int result[] = new int[3];
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dfs(0, N-4, new int[6]);
		System.out.println("impossible");
	}
	private static void dfs(int cnt, int last, int[] numbers) {
		if(last < 0) return;
		
		if(cnt == 6) {
			if(last ==0) {
				int num1 = numbers[0]*10 + numbers[1];
				int num2 = numbers[2]*10 + numbers[3];
				if(num1 + num2 == numbers[4]*10 + numbers[5]) {
					flag = true;
					System.out.println(numbers[0]+""+numbers[1]+"+"+numbers[2]+""+numbers[3]+"="+numbers[4]+""+numbers[5]);
					System.exit(0);
				}
			}
			return;
		}
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[cnt] = i;
			dfs(cnt+1, last-number[i], numbers);
		}
	}

}
