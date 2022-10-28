import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 76ms
// 메모리 : 11664
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double[] build = new double[N];
		for (int i = 0; i < N; i++) {
			build[i] = Double.parseDouble(st.nextToken());
		}
		int[] see = new int[N];
		double prev = 0;
		for (int i = 0; i < N; i++) {
			double limit = Integer.MIN_VALUE; // 기울기
			for (int j = i+1; j < N; j++) {
				double tmp = 1.0*(build[j]-build[i])/(j-i);
				if(tmp > limit) {
					see[i]++;
					limit = tmp;
				}
			}
		}
		prev = 0;
		for (int i = 0; i < N; i++) {
			double limit = Integer.MIN_VALUE;
			for (int j = i-1; j >= 0; j--) {
				double tmp = 1.0*(build[j]-build[i])/(i-j);
				if(tmp > limit) {
					see[i]++;
					limit = tmp;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, see[i]);
		}
		System.out.println(max);
	}

}
