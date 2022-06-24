import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// 시간 : 156ms
// 메모리 : 36408KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] number = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			number[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(number);
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			cnt += check(number, i);
		}
		System.out.println(cnt);
	}
    
    public static int check(long[] number, int i) {
        int N = number.length;
        int start = 0;
        int end = N - 1;

        while(start<end) {
            Long add = number[start]+number[end];
            if(start==i) start++;
            else if(end==i) end--;
            else if(add==number[i]) {
                return 1;
            } else if(add > number[i]) end--;
            else start++;
        }
        return 0;
    }

}
