import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 시간 : 120ms
// 메모리 : 13880KB
public class Main {

	static String ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine())-4;
		
		int[] map = {6,2,5,5,4,5,6,3,7,6};
		ans = "impossible";
		perm(0, new int[6], map, N);
		System.out.println(ans);
	}

	private static void perm(int cnt, int[] num, int[] map, int N) {
		
		if(cnt==6) {
			int total = 0;
			for (int i = 0; i < 6; i++) {
				total += map[num[i]];
			}
			if(total!=N) return;
			int a = num[0]*10 + num[1];
			int b = num[2]*10 + num[3];
			int c = num[4]*10 + num[5];
			StringBuilder answer = new StringBuilder();
			if(a+b==c) {
				answer.append(num[0]);
				answer.append(num[1]);
				answer.append("+");
				answer.append(num[2]);
				answer.append(num[3]);
				answer.append("=");
				answer.append(num[4]);
				answer.append(num[5]);
				ans = answer.toString();
                System.out.println(ans);
				System.exit(0);
			}
 			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			num[cnt] = i;
			perm(cnt+1, num, map, N);
		}
	}

}
