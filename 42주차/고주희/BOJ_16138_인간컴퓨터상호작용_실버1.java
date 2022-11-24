import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 628ms
// 메모리 : 104292KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] cs = br.readLine().toCharArray();
		int len = cs.length;
		int[][] alpha = new int[26][len];
		alpha[cs[0]-'a'][0] = 1;
		for (int i = 1; i < len; i++) {
			int c = cs[i] - 'a';
			for (int j = 0; j < 26; j++) {
				if(c == j) {
					alpha[j][i] = alpha[j][i-1] + 1;
				} else {
					alpha[j][i] = alpha[j][i-1];
				}
			}
		}
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(--N >= 0) {
			st = new StringTokenizer(br.readLine());
			int al = st.nextToken().charAt(0) - 'a';
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start == 0) {
				sb.append(alpha[al][end]).append("\n");
			} else {				
				sb.append(alpha[al][end] - alpha[al][start-1]).append("\n");
			}
		}
		System.out.println(sb);
	}

}
