import java.io.*;
import java.util.*;

/*
    소문난 칠공주
    골드 3
    시간 : 588ms
    메모리 : 122252kb
 */

public class Main {

    /*
    1. 7자리중 Y가 4 이상 되면 stop
    2. 서로 인접해야한다.
     */

    static int result;
    static char[][] map;
    static boolean[][] v; // 칠공주인지 체크하는 변수
    static int dr[] = {1, 0, 0, -1};
    static int dc[] = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        v = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        combi(0, 0, 0, new int[7]);

        System.out.println(result);
    }

    private static void combi(int start, int cnt, int cntY, int[] selected) {

        if (cntY >= 4) return;

        if (cnt == 7) {
            if (adjcheck(selected)) result++;
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[cnt] = i;
            v[i / 5][i % 5] = true;
            if (map[i / 5][i % 5] == 'Y') combi(i + 1, cnt + 1, cntY + 1, selected);
            else combi(i + 1, cnt + 1, cntY, selected);
            v[i / 5][i % 5] = false;
        }

    }

    private static boolean adjcheck(int[] selected) {
        int cnt = 1;

        boolean ischecked[][] = new boolean[5][5];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(selected[0]);

        while (!queue.isEmpty()) {

            int cur = queue.poll();

            int r = cur / 5;
            int c = cur % 5;

            ischecked[r][c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(!check(nr,nc)) continue;

                if(ischecked[nr][nc]) continue;

                if (v[nr][nc] == true) {
                    queue.add(nr * 5 + nc);
                    cnt++;
                    ischecked[nr][nc] = true;
                }
            }

        }

        if (cnt == 7) return true;
        else return false;
    }


    private static boolean check(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

}
