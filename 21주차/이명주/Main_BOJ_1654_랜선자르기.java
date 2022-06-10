import java.io.*;
import java.util.*;

public class Main {

    /*
    랜선 자르기
    실버 2
    시간 : 132ms
    메모리 : 15476kb
    */

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int []lines = new int[K];

        long max = 0;

        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            max += lines[i];
        }

        max /= N;
        long min = 1;
        long middle = 0;

        while (max >= min) {

            middle = (max + min) / 2;

            int cnt = 0;

            for (int i = 0; i < K; i++) {
                cnt += lines[i] / middle;
            }

            if (cnt >= N)
                min = middle + 1;
            else
                max = middle - 1;

        }
        System.out.println(max);
    }
}
