import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][] arr = new long[N + 1][2];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[n][0] = Long.parseLong(st.nextToken());
            arr[n][1] = Long.parseLong(st.nextToken());
        }
        arr[N] = arr[0].clone();

        long sum1 = 0L;
        long sum2 = 0L;

        for (int n = 0; n < N; n++) {
            sum1 += arr[n][0] * arr[n + 1][1];
            sum2 += arr[n][1] * arr[n + 1][0];
        }

        System.out.printf("%.1f%n", Math.abs(sum1 - sum2) / 2D);

    }
}