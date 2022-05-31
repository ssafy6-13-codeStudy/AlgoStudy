package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간 : 96ms
// 메모리 : 14984KB

public class BOJ_1309 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[][] map = new long[N+1][2];
		
		map[1][0] = 1;
		map[1][1] = 1;
		
		for (int i = 2; i <= N; i++) {
			map[i][0] = (map[i-1][0]+map[i-1][1]*2)%9901;
			map[i][1] = (map[i-1][0]+map[i-1][1])%9901;
		}
		
		System.out.println((map[N][0]+map[N][1]*2)%9901);
	}

}
