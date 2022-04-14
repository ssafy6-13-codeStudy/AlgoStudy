import java.io.*;
import java.util.*;

/*
    나이트의 이동
    시간 : 272ms
    메모리 : 71504kb
*/

public class Main {

    static int T, N;
    static int[][] map;
    static boolean[][] v;
    static Point start, end;
    static StringBuilder sb = new StringBuilder();
    static int[] dr = {-2,-2,-1,-1,1,1,2,2};
    static int[] dc = {-1,1,-2,2,-2,2,-1,1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            v = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new Point(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), 0);
            st = new StringTokenizer(br.readLine());
            end = new Point(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), 0);

            bfs(start);

        }
        System.out.println(sb);
    }

    static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.add(p);
        v[p.r][p.c] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int cnt = cur.cnt;

            if(r == end.r && c == end.c) { // 목표점에 도착
                sb.append(cur.cnt).append("\n");
                return;
            }

            for(int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
                    q.add(new Point(nr, nc, cnt + 1));
                    v[nr][nc] = true;
                }
            }

        }

    }

    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

}