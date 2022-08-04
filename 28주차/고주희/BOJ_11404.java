import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 404ms
// 메모리 : 41800KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] bus  = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(bus[i], 987654321);
			bus[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			if(bus[a][b] > w) bus[a][b] = w;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(bus[i][j] > bus[i][k] + bus[k][j]) {
						bus[i][j] = bus[i][k] + bus[k][j];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
                if(bus[i][j] >= 987654321) sb.append(0);
				else sb.append(bus[i][j]);
                sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
