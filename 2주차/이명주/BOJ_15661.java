import java.io.*;
import java.util.*;

/*
    링크와 스타트
    실버 1
    시간 : 1060ms
    메모리 : 12484kb
 */

public class Main {

    static int N, result = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        subset(0, new boolean[N]);

        System.out.println(result);
    }

    private static void subset(int cnt, boolean[] v) {

        if (cnt == N) {
            int start = 0, link = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (v[i] == v[j]) {
                        if (v[i] == true)
                            start += map[i][j] + map[j][i];
                        else
                            link += map[i][j] + map[j][i];
                    }

                }
            }
            result = Math.min(result, Math.abs(start - link));
            return;
        }

        v[cnt] = true;
        subset(cnt + 1, v);
        v[cnt] = false;
        subset(cnt + 1, v);

    }
}
