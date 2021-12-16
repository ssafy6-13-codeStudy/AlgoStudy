import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int result = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //상담 기간
            arr[i][1] = Integer.parseInt(st.nextToken()); //상담 금액
        }

        subset(0, 0);

        System.out.println(result);
    }

    // 부분집합
    private static void subset(int cnt, int sum) {

        // 퇴사일이라면 max값 비교
        if (cnt == N) {
            result = Math.max(result, sum);
            return;
        }

        // 현재 날짜의 상담을 선택했을 시 N을 넘지 않는다면 선택하고 다음으로
        if (arr[cnt][0] + cnt <= N) subset(cnt + arr[cnt][0], sum + arr[cnt][1]);

        // 현재 상담을 선택하지 않고 다음으로
        subset(cnt + 1, sum);

    }

}
