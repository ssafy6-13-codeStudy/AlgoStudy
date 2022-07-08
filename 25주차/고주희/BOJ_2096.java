import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 376ms
// 메모리 : 53136KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] min = new int[N][3];
		int[][] max = new int[N][3];
		for (int i = 0; i < 3; i++) {
			min[0][i] = max[0][i] = map[0][i];
		}
		
		for (int i = 1; i < N; i++) {
			max[i][0] = map[i][0] + Math.max(max[i-1][0], max[i-1][1]);
			max[i][1] = map[i][1] + Math.max(max[i-1][0], Math.max(max[i-1][1], max[i-1][2]));
			max[i][2] = map[i][2] + Math.max(max[i-1][1], max[i-1][2]);
			min[i][0] = map[i][0] + Math.min(min[i-1][0], min[i-1][1]);
			min[i][1] = map[i][1] + Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2]));
			min[i][2] = map[i][2] + Math.min(min[i-1][1], min[i-1][2]);
		}
		
		int maxanswer = -1;
		int minanswer = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			if(maxanswer < max[N-1][i]) maxanswer = max[N-1][i];
			if(minanswer > min[N-1][i]) minanswer = min[N-1][i];
		}

		System.out.println(maxanswer+" "+minanswer);

	}

}
