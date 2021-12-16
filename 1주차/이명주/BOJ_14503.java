import java.io.*;
import java.util.*;

/*
0 : 북
1 : 동
2 : 남
3 : 서
 */

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int M, N, cnt = 0;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dc = {0, 1, 0, -1};


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());

        // 로봇 청소기 좌표, 방향 입력
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(r, c, d);

        System.out.println(cnt);

    }

    private static void clean(int r, int c, int d) {

        // 청소안한 공간이라면 청소하기
        if (map[r][c] == 0) {
            map[r][c] = -1;
            cnt++;
        }

        int nd = d;

        // 청소할 공간이 존재하는지 4방향을 왼쪽부터 탐색
        for (int i = 0; i < 4; i++) {
            nd = (nd + 3) % 4;
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if (map[nr][nc] == 0) {
                clean(nr, nc, nd);
                return;
            }
        }

        int nr = r - dr[d];
        int nc = c - dc[d];
        // 네 방향 모두 청소 불가능인 상태이므로, 후진 가능하다면 후진
        if (map[nr][nc] != 1)
            clean(nr, nc, d);

    }

}
