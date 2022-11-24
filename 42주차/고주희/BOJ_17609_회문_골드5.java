import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간 : 228ms
// 메모리 : 48196KB
public class Main {

	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(--N >= 0) {
			char[] cs = br.readLine().toCharArray();
			cnt = 2;
			dfs(cs, 0, cs.length-1, 0);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(char[] cs, int start, int end, int k) {

		if(k >= cnt) return;
		if(start > end) {
			if(cnt > k) {
				cnt = k;
			}
			return;
		}
		
		if(cs[start] == cs[end]) {
			dfs(cs, start+1, end-1, k);
		} else {
			if(cs[start+1] == cs[end]) {
				dfs(cs, start+1, end, k+1);
			} 
			if(cs[start] == cs[end-1]) {
				dfs(cs, start, end-1, k+1);
			}
		}	
		return;
	}

}
