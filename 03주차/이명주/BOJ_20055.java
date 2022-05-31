import java.io.*;
import java.util.*;

/*
    컨베이어 벨트 위의 로봇
    실버 1
    시간 : 268ms
    메모리 : 16972kb
 */

public class Main {

    static int N, K, result;
    static int[] arr;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2*N];
        robot = new boolean[2*N]; // 열 : 로봇이 들어간 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;

        while (check()) {
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            int tmp = arr[2*N-1];
            for (int i = 2*N-2; i >=0 ; i--) {
                arr[i+1] = arr[i];
            }
            arr[0] = tmp;

            for (int i = N-2; i >=0 ; i--) {
                robot[i + 1] = robot[i];
            }
            robot[0] = false;

            if(robot[N-1]) robot[N-1] = false;

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 회전하는 방향으로 이동 (로봇이 없고 내구도가 1이상 시 이동 가능)
            for (int i = N-2; i >=0 ; i--) {
                if(robot[i]&&!robot[i+1]&&arr[i+1]>=1) {
                    arr[i+1]--;
                    robot[i]= false;
                    robot[i+1] = true;
                }
            }

            if(robot[N-1]) robot[N-1] = false;

            // 3. 올리는 위치에 내구도가 0이 아니면 로봇 올리기.
            if(arr[0]>0) {
                arr[0]--;
                robot[0] = true;
            }

            result++;
        }

        System.out.println(result);
    }

    private static boolean check() {
        int t=0;
        for (int i = 0; i < 2*N; i++) {
            if(arr[i]==0) t++;
            if(t==K) return false;
        }

        return true;
    }

}
