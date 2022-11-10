import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 76ms
// 메모리 : 11528KB
public class Main {

	static int K;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int total = N;
		String tmp = Integer.toBinaryString(total);
		while(check(tmp)) {
			for (int i = tmp.length()-1; i >= 0; i--) {
				if(tmp.charAt(i) == '1') {
					total += (1 << tmp.length()-1-i);
					tmp = Integer.toBinaryString(total);
					break;
				}
			}
		}
		System.out.println(total - N);
	}

	private static boolean check(String tmp) {

		int cnt = 0;
		
		for (int i = 0; i < tmp.length(); i++) {
			if(tmp.charAt(i) == '1') cnt++;
			if(cnt > K) return true;
		}
		return false;
	}

}
