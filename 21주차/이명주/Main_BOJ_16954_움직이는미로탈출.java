import java.io.*;
import java.util.*;

public class Main {

    /*
    움직이는 미로 탈출
    골드 4
    시간 : 192ms
    메모리 : 70324kb
    */

    static int[] dr = {0, -1, 0, 1, -1, -1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 0, -1, 1, -1, 1, 0};
    static char[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];

        for (int i = 0; i < 8; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(7, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Point now = q.poll();

                if (map[now.r][now.c] == '#') continue;

                for (int d = 0; d < 9; d++) {

                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (!check(nr, nc)) continue;

                    if (nr == 0 && nc == 7) return 1;

                    q.add(new Point(nr, nc));

                }
            }
            move();
        }

        return 0;
    }

    private static void move() {

        for (int i = 7; i > 0; i--) {
            for (int j = 7; j >= 0; j--) {
                map[i][j] = map[i - 1][j];
            }
        }

        for (int i = 0; i < 8; i++) {
            map[0][i] = '.';
        }

    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8 && map[r][c] == '.';
    }

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
