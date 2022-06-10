package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 132ms
//메모리 14884kb
public class Main_BOJ_1654_랜선자르기_실버2{
	static int K, N;
	static int[] lenline;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lenline = new int[K];
		for (int i = 0; i < K; i++) {
			lenline[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lenline[i]);
		}
		System.out.println(search());
	}

	private static int search() {
		int left = 0;
		int right = max+1;
		
		int mid; // 최대 랜선 길이
		while(left < right) {
			mid = (left+right)/2;
			
			int cnt = 0; // 잘리는 랜선 갯수
			for (int i = 0; i < K; i++) {
				cnt += lenline[i]/mid;
			}
			
			if(cnt >= N) { // 더 크게 잘라도 됨
				left = mid + 1;
			}else { // 더 잘게 잘라야 함
				right = mid;
			}
		}
		
		return right-1; // 임의로 더한 1을 빼줌
	}
}
