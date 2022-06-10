import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 120ms
// 메모리 : 15276KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		long N = Long.parseLong(st.nextToken());
		int[] labber = new int[K];
		
		long start = 1;
		long end = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			labber[i] = Integer.parseInt(br.readLine());
			if(end<labber[i]) end = labber[i];
		}
		long mid = -1;
		long answer = mid;
		while(start<=end) {
			mid =  1l*(start+end)/2;
			long tmp = check(mid, labber);
			if(tmp>=N) {
				start = mid+1;
				if(answer<mid) answer = mid;
			}else {
				end = mid-1;
			}
		}
		System.out.println(answer);
		
	}

	private static long check(long end, int[] labber) {
		long total = 0;
		for (int i : labber) {
			total += i/end;
		}
		return total;
	}

}
