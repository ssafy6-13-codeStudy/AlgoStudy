import java.util.*;
import java.io.*;

/*
    주사위
    골드 5
    시간 : 72ms
    메모리 : 11548kb
*/

/*
    꼭짓점 : 4개는 무조건 3면이 나온다.
    (n-2) * 8 + 4 개는 무조건 2면이 나온다.
    (n-2)^2 * 5  + 4* (n -2) 개는 무조건 1면이 나온다.
 */

public class Main {

    static int[] dice; // 주사위
    static long n;
    static long sum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dice = new int[6];

        n = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            sum += dice[i];
            max = Math.max(max,dice[i]);
        }

        if (n == 1L) {
            System.out.println(sum-max);
        }
        else {
            int a = Math.min(dice[0],dice[5]);
            int b = Math.min(dice[1],dice[4]);
            int c = Math.min(dice[2],dice[3]);
            int a1 = Math.min(a,Math.min(b,c));
            int a2 = Math.min(a+b,Math.min(b+c,a+c));
            int a3 = a+b+c;

            sum = 4L*a3 + a2 * (8 * (n - 2) + 4) + a1*(5*(n-2)*(n-2) + 4*(n-2));

            System.out.println(sum);
        }

    }

}