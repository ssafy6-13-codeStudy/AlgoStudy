import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 112ms
// 메모리 : 17408KB
public class Main {

	static int min;
	static int[][][] hp;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] scv = new int[3];
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(scv);
		min = Integer.MAX_VALUE;
		hp = new int[61][61][61];
		for (int i = 0; i < 61; i++) {
			for (int j = 0; j < 61; j++) {
				Arrays.fill(hp[i][j], 987654321);
			}
		}
		attack(scv, 0);
		System.out.println(hp[0][0][0]);
	}
	private static void attack(int[] scv, int cnt) {
		
		boolean allZero = true;
		for (int i = 0; i < 3; i++) {
			if(scv[i] > 0) allZero = false;
			else scv[i] = 0;
		}
		
		if(allZero) {
			hp[scv[0]][scv[1]][scv[2]] = Math.min(hp[scv[0]][scv[1]][scv[2]], cnt);
			return;
		}
		if(hp[scv[0]][scv[1]][scv[2]]!=987654321) return;
		
		hp[scv[0]][scv[1]][scv[2]] = Integer.min(hp[scv[0]][scv[1]][scv[2]], cnt);
		perm(scv, 0, new boolean[3], new int[3], cnt);
	}
	private static void perm(int[] scv, int cnt, boolean[] v, int[] num, int prev) {
		
		if(cnt==3) {
			int[] tmp = new int[3];
			for (int i = 0; i < 3; i++) {
				tmp[i] = scv[i] - num[i];
			}
			attack(tmp, prev+1);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if(v[i]) continue;
			v[i] = true;
			num[cnt] = (int) Math.pow(3, i);
			perm(scv, cnt+1, v, num, prev);
			v[i] = false;
		}
	}
}
