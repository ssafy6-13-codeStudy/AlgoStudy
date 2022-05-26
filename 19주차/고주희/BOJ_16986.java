import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// 시간 : 260ms
// 메모리 : 37600KB

public class Main {

	static int K;
	static int JIU = 0;
	static int KYEONG = 1;
	static int MINHO = 2;
	static boolean isWin;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] rsp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				rsp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] order = new int[3][20];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			order[1][i] = Integer.parseInt(st.nextToken()) - 1;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			order[2][i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		Arrays.fill(order[0], -1);
		isWin = false;
		perm(0, order, new boolean[N], rsp);
		if(isWin) System.out.println(1);
		else System.out.println(0);
	}
	private static void perm(int cnt, int[][] order, boolean[] v, int[][] rsp) {

		int N = rsp.length;
		if(isWin) return;
		if(cnt==N) {
			if(findWinner(rsp, order) == JIU) {
				isWin = true;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(v[i]) continue;
			v[i] = true;
			order[0][cnt] = i;
			perm(cnt+1, order, v, rsp);
			v[i] = false;
		}
		
	}
	private static int findWinner(int[][] rsp, int[][] order) {

		int first = JIU;
		int sec = KYEONG;
		int[] cnt = new int[3];
		int[] win = new int[3];
		int winner = -1;
		
		while(true) {
			int f = order[first][cnt[first]++];
			int s = order[sec][cnt[sec]++];
			int tmp = 3 - first - sec;
			if(f == -1) return -1;
			if(s == -1) return -1;
			if(rsp[f][s] == 1) {
				win[sec]++;
				if(win[sec]==K) {
					winner = sec;
					break;
				}
				if(tmp>sec) {
					first = sec;
					sec = tmp;
				} else {
					first = tmp;
				}
			} else if(rsp[f][s] == 2) {
				win[first]++;
				if(win[first]==K) {
					winner = first;
					break;
				}
				if(tmp>first) {
					sec = tmp;
				} else {
					sec = first;
					first = tmp;
				}
			} else {
				win[sec]++;
				if(win[sec]==K) {
					winner = sec;
					break;
				}
				if(tmp>sec) {
					first = sec;
					sec = tmp;
				} else {
					first = tmp;
				}
			}
		}
		return winner;
	}

}
