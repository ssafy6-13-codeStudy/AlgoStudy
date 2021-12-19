package week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 704ms
//메모리 18732kb
//코드길이 1385

public class Main_BOJ_15661_링크와스타트_실버1 {
	static int N;
	static int[][] map;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		subset(0, 0, new boolean[N]);
		
		System.out.println(min);
	}

	private static void subset(int cnt, int checker,boolean[] v) {
		if(cnt == N) {
			//한 팀으로만 인원이 모였다면 return
			if(checker == -N || checker == N) return;
			
			int sumA = 0;
			int sumB = 0;
			
			for (int i = 0; i < N; i++) {
				// a팀
				if(v[i]) {
					for (int j = 0; j < N; j++) {
						if(v[j]) {
							sumA += map[i][j];
						}
					}
				}else { // b팀
					for (int j = 0; j < N; j++) {
						if(!v[j]) {
							sumB += map[i][j];
						}
					}
				}
			}
			
			min = Math.min(min, Math.abs(sumA - sumB));
			
			return;
		}
		
		v[cnt] = true;
		subset(cnt+1,checker+1, v);
		v[cnt] = false;
		subset(cnt+1,checker-1, v);
	}

}
