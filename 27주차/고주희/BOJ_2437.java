import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 88ms
// 메모리 : 11828KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
        if(num[0]!=1){
            System.out.println(1);
            return;
        }
		int sum = 1;
		for(int i = 0; i < N; i++) {
			if(sum < num[i]) {
				System.out.println(sum);
				return;
			} else {
				sum += num[i];
			}
		}
		System.out.println(sum);
		
	}

}
