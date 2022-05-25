import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 124ms
// 메모리 : 11924KB
public class Main {

	static int c;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int alphabet = 0;
		int v = 0;
		String ANTIC = "antic";
		for (int i = 0; i < 5; i++) {
			alphabet |= (1 << ANTIC.charAt(i)-'a');
		}
		
		int[] words = new int[N];
        int letter = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int b = alphabet;
			for (int j = 0; j < str.length(); j++) {
				b |= 1 << (str.charAt(j)-'a');
                letter++;
			}
			words[i] = b;
		}
		c = 0;
		if(letter<=K) c = N;
		else if(K<5) c = 0;
        else learnAlpha(words, alphabet, K-5, 0);
		System.out.println(c);
	}

	private static void learnAlpha(int[] words, int alphabet, int cnt, int start) {

		if(cnt==0) {
			int total = 0;
			for (int b : words) {
				if((alphabet & b) == b) total++;
			}
			c = Math.max(c, total);
		}
		for (int i = start; i < 26; i++) {
			if((alphabet & (1 << i)) != 0) continue;
			learnAlpha(words, (alphabet | 1 <<i), cnt-1, i+1);
		}
		
	}

}
