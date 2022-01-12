package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//시간 640ms
//메모리 33100kb
//코드길이 947

public class Main_BOJ_5052_전화번호목록_골드4 {
	static int T;
	static int N;
	static String[] list;
	static String result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 일관성 기준
		// 1.A 문자열이 B 문자열 안에 모두 포함되어야 한다.
		// 2.접두어가 아닌 중복은 허용한다. -> matches 를 사용(정규표현식) -> startwith(더빠름)
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new String[N];
			
			for (int i = 0; i < N; i++) {
				list[i] = br.readLine();
			}
			
			// 이중 loop 돌면 시간초과 -> 정렬하면 i+1번은 i번이 접두어가 되는 번호가 옴. 혹은 그런 숫자가 없음 
			Arrays.sort(list);
			result = "YES";
			
			for (int i = 0; i < N-1; i++) {
				if(list[i+1].startsWith(list[i])) {
					result = "NO";
					break;
				}
			}
			
			System.out.println(result);
		}
	}
}
