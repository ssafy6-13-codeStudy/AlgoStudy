import java.io.*;
import java.util.*;

/*
    문자들
    시간 : 375ms
    메모리 : 45668kb
*/

public class Main {

    static int R, C;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static char[][] map;
    static int max = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        boolean[][] v = new boolean[R][C];
        v[0][0]=true;
        dfs(0, 0, v, String.valueOf(map[0][0]));

        System.out.println(max);

    }

    private static void dfs(int r, int c, boolean[][] v, String alphabet) {
        max = Math.max(alphabet.length(), max);
        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!check(nr, nc) || v[nr][nc]) continue;
            if (alphabet.contains("" + map[nr][nc])) continue;

            v[nr][nc] = true;
            alphabet += map[nr][nc];
            dfs(nr, nc, v, alphabet);
            v[nr][nc] = false;
            alphabet = alphabet.substring(0, alphabet.length() - 1);
        }

    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }


}
