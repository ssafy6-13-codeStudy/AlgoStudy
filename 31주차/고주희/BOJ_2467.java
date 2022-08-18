import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 268ms
// 메모리 : 31296KB
public class Main {


	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] liquids = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		if(liquids[0] >= 0) {
            System.out.println(liquids[0] + " " + liquids[1]);
            return;
        }
        if(liquids[N - 1] <= 0) {
            System.out.println(liquids[N - 2] + " " + liquids[N - 1]);
            return;
        }
		int start = 0;
		int end = N - 1;
		int sum = 1087654321;
		int a = liquids[start];
		int b = liquids[end];
		while(start < end) {
			if(sum >= Math.abs(liquids[start] + liquids[end])) {
				a = liquids[start];
				b = liquids[end];
				sum = Math.abs(liquids[start] + liquids[end]);
			}
			if(liquids[start] + liquids[end] > 0) {
				end--;
			} else if(liquids[start] + liquids[end] < 0) {
				start++;
			} else {
				a = liquids[start];
				b = liquids[end];
				break;
			}
		}
		System.out.println(a + " " + b);
	}

}
