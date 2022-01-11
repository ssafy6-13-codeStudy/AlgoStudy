package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//시간  124ms
//메모리  14320kb
//코드길이  933b

public class Main_BOJ_1339_단어수학_골드4 {
	static int N;
	static int[] alpha;
	// 런타임 에러 -> alpha 배열크기 +1 해줘야 한다.
	// 수학적 방식으로 해결
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		alpha = new int['Z'-'A'+1];
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < temp.length; j++) {
				alpha[temp[j]-'A'] += Math.pow(10, temp.length -j -1);
			}
		}
		
		Arrays.sort(alpha);
		
		int sum = 0;
		for (int i = alpha.length-1, num = 9; num >= 0; i--, num--) {
			sum += alpha[i]*num;
		}
		System.out.println(sum);
	}
}