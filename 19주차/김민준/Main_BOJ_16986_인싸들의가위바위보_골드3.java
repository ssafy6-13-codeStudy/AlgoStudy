package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 276ms
//메모리 38148kb
public class Main_BOJ_16986_인싸들의가위바위보_골드3 {
	static int N, K;
	static int[][] battle;
	static int[][] order;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
			
		battle = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				battle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[3][20];
		for (int i = 1; i <= 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 20; j++) {
				order[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
		order[0][N] = -1;
		dfs(0, new boolean[N]);
		System.out.println(0);
	}
	
	public static void dfs(int cnt, boolean[] visit) {
		if(cnt == N) {
			if(check()) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			order[0][cnt] = i;
			dfs(cnt+1, visit);
			visit[i] = false;
		}
	}

	private static boolean check() {
		int[] winCnt = new int[3];
		int[] idx = new int[3];
		int p1 = 0;
		int p2 = 1;
		int np = 2;
		int winner = -1;
		
		while(winCnt[0] < K && winCnt[1] < K && winCnt[2] < K) {
			if(order[0][idx[0]] == -1) {
				return false;
			}
			np = 3 - (p1 + p2);
			int A = order[p1][idx[p1]];
			int B = order[p2][idx[p2]];
			
			if(battle[A][B] == 2) { // 이김
				winCnt[p1]++;
				winner = p1;
			}else if(battle[A][B] == 1) { // 비김
				if(p1 < p2) {
					winCnt[p2]++;
					winner = p2;
				}else {
					winCnt[p1]++;
					winner = p1;
				}
			}else { // 짐
				winCnt[p2]++;
				winner = p2;
			}
			
			idx[p1]++;
			idx[p2]++;
			p1 = winner;
			p2 = np;
		}
				
		if(winCnt[0] == K) {
			return true;
		}else {
			return false;
		}
	}
}
