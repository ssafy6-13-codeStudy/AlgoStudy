//15448kb
// 136ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1654_랜선자르기 {
	static int N, K;
	static int LAN[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		LAN = new int[N];
		long max = 0;
		long min = 0;
		for(int i=0; i<N; i++) {
			LAN[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, LAN[i]);
		}
		
		max +=1;
	
		while(min < max ) {
			
			long mid = (min + max) /2;
			
			long cnt =0;
			
			for(int i=0;i<N;i++) {
				cnt += (LAN[i] / mid);
			}
			
			if(cnt < K) {
				max = mid;
			}else {
				min = mid +1;
			}
		}
		
		System.out.println(min-1);
	}
}
