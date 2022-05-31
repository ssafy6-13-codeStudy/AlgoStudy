import java.io.*;
import java.util.*;

/*
    단어 수학
    골드 4
    시간 : ms
    메모리 : kb
 */

public class Main {

  /*
  ABC = 100A + 10B + C
  CB = 10C + B
  구해야 할 값 : 100A + 11B + 11C
  */

    static int N;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Integer[] alphabet = new Integer[26];

        // 배열 초기화
        Arrays.fill(alphabet, 0);

        for (int i = 0; i < N; i++) {

            char[] input = br.readLine().toCharArray();

            int pos = 1;

            // 자릿수 오름차순으로 alphabet 배열에 추가해준다.
            for (int j = input.length-1; j >= 0; j--) {
                alphabet[input[j] - 'A'] += pos;
                pos *= 10;
            }

        }

        // 내림차순 정렬
        Arrays.sort(alphabet, Collections.reverseOrder());

        int result = 0;
        int n = 9;
        
        // 가장 많은 알파벳부터 숫자 대입 ( 9, 8 ,7 ...)
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == 0) break;
            result += alphabet[i] * n--;
        }

        System.out.println(result);

    }

}
