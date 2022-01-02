package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 시간 : 1620ms
// 메모리 : 12476KB

public class BOJ_1339_2 {


	static int max;
    static int count;
    public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
    // 입력 받을 변수
		int N = Integer.parseInt(br.readLine());
		char[][] number = new char[N][8];
    // 어떤 알파벳이 들어왔는지 확인할 배열
		int[] alphabet = new int[26];
		Arrays.fill(alphabet, -1);
		for (int i = 0; i < N; i++) {
			number[i] = br.readLine().toCharArray();
			int len = number[i].length;
			for (int j = 0; j < len; j++) {
        // 들어온 알파벳 체크
				alphabet[number[i][j]-'A'] = 0;
			}
		}
		
		count = 0; // 몇 종류 알파벳?
		max = Integer.MIN_VALUE;
		for (int i = 0; i < 26; i++) {
			if(alphabet[i]==0) count++;
		}
		
		perm(0, number, new boolean[26], alphabet);
		System.out.println(max);
	}

  // permutation으로 9부터 count개까지 순열 만듦
	private static void perm(int cnt, char[][] number, boolean[] v, int[] alphabet) {

		if(cnt==count) {
			int total = 0;
			int len = number.length;
			for (char[] i : number) {
				int len2 = i.length;
				for (int j = 0; j<len2; j++) {
          // alphabet에 저장된 숫자(올림차순으로 저장했으므로 9에서 빼야함) 저장
					int temp = 9-alphabet[i[j]-'A'];
					for (int k = 0; k < len2-j-1; k++) {
            // 자릿수 옮겨줌
            // Math.pow로 하는 경우 시간 초과 나옴
						temp*=10;
					}
					total+=temp;
				}
			}
			max = Math.max(max, total);
			
			return;
		}
		
		for (int i = 0; i < 26; i++) {
      // 나타나지 않은 알파벳이면 넘어감
			if(alphabet[i]==-1) continue;
      // 방문했던 경우 넘어감
			if(v[i]) continue;
			v[i] = true;
      // alphabet에 cnt 값 저장(순열 세우는 것)
			alphabet[i] = cnt;
			perm(cnt+1, number, v, alphabet);
			v[i] = false;
		}
	}

}
