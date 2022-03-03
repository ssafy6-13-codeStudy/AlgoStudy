import java.io.*;
import java.util.*;

/*
    성냥개비
    실버 4
    시간 : 128ms
    메모리 : 14304kb
*/

public class Main {

    static int N;
    static boolean answer;
    static String result = "impossible";
    static int[] num = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()) - 4;

        perm(0, new int[6], N);

        System.out.println(result);
    }

    private static void perm(int cnt, int[] nums, int n) {

        if (cnt == 6) {
            if (n != 0 || answer) return;

            int a = nums[0] * 10 + nums[1];
            int b = nums[2] * 10 + nums[3];
            int c = nums[4] * 10 + nums[5];

            if (a + b == c) {
                result = "" + nums[0] + nums[1] + "+" + nums[2] + nums[3] + "=" + nums[4] + nums[5];
                answer = true;
            }

            return;
        }

        for (int i = 0; i < 10; i++) {
            // 성냥개비가 남아있을 때만
            if (n >= num[i]) {
                nums[cnt] = i;
                perm(cnt + 1, nums, n - num[i]);
            }
        }

    }

}
