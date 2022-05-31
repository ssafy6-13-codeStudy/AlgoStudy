package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


// 시간 : 584ms
// 메모리 : 32128KB
public class BOJ_5052 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] phone = new String[N];
			for (int i = 0; i < N; i++) {
				phone[i] = br.readLine();
			}
			
			// 배열 정렬
			Arrays.sort(phone);
/*			String sort 하면 다음과 같이 정렬됨 => 길이 상관 x 각 자리 단어 순서로
			111
			11234
			1567
			226
			345678
			3456789
			[111, 11234, 1567, 226, 345678, 3456789]
*/

			// 일관성 있는지 지켜볼 boolean
			boolean answer = true;
			for (int i = 1; i < N; i++) {
				int plen = phone[i-1].length();
				int nlen = phone[i].length();
				// 앞선 번호가 지금 번호보다 길면 어차피 포함 안될 것이므로 이후 코드 무시
				if(plen>nlen) continue;
				int cnt = 0;
				// 현재 번호가 앞선 번호를 포함하고 있는지 확인함
				// string에 startWith라는 함수가 있음 -> 메모리를 적게 먹는듯
				for (int j = 0; j < plen; j++) {
					if(phone[i-1].charAt(j)==phone[i].charAt(j)) {
						cnt++;
					}else { // 하나라도 다르면 break
						break;
					}
				}
				// 동일한 숫자 개수가 앞선 번호 길이와 같으면 포함한 것 -> 일관성 없음
				if(cnt==plen) {
					answer = false;
					break;
				}
			}
			System.out.println(answer? "YES":"NO");
		}
	}

}
