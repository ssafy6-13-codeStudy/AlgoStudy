import java.io.*;
import java.util.*;

public class Main {

    /*
    불
    골드 4
    시간 : 584ms
    메모리 : 115688kb
    */

    static int W, H;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static boolean[][] v;
    static char[][] map;
    static Queue<Point> fire;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            fire = new LinkedList<>();

            v = new boolean[H][W];
            map = new char[H][W];
            int r = 0;
            int c = 0;

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '@') {
                        r = i;
                        c = j;
                        v[i][j] = true;
                        map[i][j] = '.';
                    }

                    else if (map[i][j] == '*')
                        fire.add(new Point(i, j));

                }
            }

            bfs(r, c);
        }
        System.out.println(sb);
    }

    public static void bfs(int r, int c) {

        Queue<Point> q = new LinkedList<>();
        int level = 0;

        q.add(new Point(r, c));

        while (!q.isEmpty()) {

            int size = q.size();
            level++;

            move();

            for (int t = 0; t < size; t++) {

                Point now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        sb.append(level + "\n");
                        return;
                    }

                    if (!v[nr][nc] && map[nr][nc]=='.') {
                        v[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }

        sb.append("IMPOSSIBLE\n");
    }

    private static void move() {

        int size = fire.size();

        for (int i = 0; i < size; i++) {

            Point now = fire.poll();

            for (int d = 0; d < 4; d++) {

                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
                    map[nr][nc] = '*';
                    fire.add(new Point(nr, nc));
                }
            }
        }
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
