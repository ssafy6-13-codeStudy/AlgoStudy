import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 96ms
// 메모리 : 15976KB
public class Main {

	static int L;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		char[] c = new char[C];
		for (int i = 0; i < C; i++) {
			c[i] = str.charAt(i*2);
		}
		sb = new StringBuilder();
		Arrays.sort(c);
		comb(0, 0, c, new char[L], 0);
		System.out.println(sb);
		
	}
	private static void comb(int cnt, int start, char[] c, char[] letter, int count) {

		int C = c.length;
		
		if(cnt==L) {
			if(count==0) return;
			if(L-count<2) return;
			for (char d : letter) {
				sb.append(d);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < C; i++) {
			letter[cnt] = c[i];
			if("aeiou".contains(c[i]+"")) comb(cnt+1, i+1, c, letter, count+1);
			else comb(cnt+1, i+1, c, letter, count);
		}
	}

}
