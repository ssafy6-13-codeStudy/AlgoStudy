import java.io.*;
import java.util.*;

/*
    전화번호 목록
    골드 4
    시간 : 564ms
    메모리 : 31768kb
*/

public class Main {

    static int T, N;
    static StringBuilder sb = new StringBuilder();
    static String[] numbers;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        outer:
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            numbers = new String[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers);

            for (int i = 0; i < N-1; i++) {
                if (numbers[i + 1].startsWith(numbers[i])) {
                    sb.append("NO\n");
                    continue outer;
                }
            }

            sb.append("YES\n");

        }

        System.out.println(sb);
    }
}