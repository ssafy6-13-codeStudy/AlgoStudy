import java.io.*;
import java.util.*;

public class Main {

    /*
    로봇
    골드 3
    시간 : 100ms
    메모리 : 12796kb

    k =  1 2 3
    dir = 좌우 90도 회전
    0 = 로봇이 갈 수 있음
    1 = 갈 수 없음
    */

    static int M, N;
    static int[][] map;
    static Point s;
    static int R,C,D;
    static boolean[][][] v;
    static int[] dr = {0,0,1,-1}; // 동서남북
    static int[] dc = {1,-1,0,0};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        v = new boolean[M][N][4];

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        s = new Point(  Integer.parseInt(st.nextToken())-1,
                            Integer.parseInt(st.nextToken())-1,
                            Integer.parseInt(st.nextToken())-1
        ,0);

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken())-1;
        C = Integer.parseInt(st.nextToken())-1;
        D = Integer.parseInt(st.nextToken())-1;

        v[s.r][s.c][s.d] = true;
        System.out.println(bfs(s));
    }

    private static int bfs(Point point) {

        Queue<Point> q = new LinkedList<>();

        q.add(point);

        while(!q.isEmpty()) {

            Point now = q.poll();

            // 도착지인지 체크
            if(now.r==R && now.c == C && now.d == D)
                return now.cnt;

            // go
            for (int i = 1; i <= 3; i++) {

                int nr = now.r + dr[now.d]*i;
                int nc = now.c + dc[now.d]*i;

                if(!check(nr,nc)) continue;

                // 중간에 벽이 있으면 아예 끝내야 한다
                if(map[nr][nc]==1) break;

                if(!v[nr][nc][now.d]) {
                    v[nr][nc][now.d] = true;
                    q.add(new Point(nr,nc,now.d,now.cnt+1));
                }

            }

            // turn 오른쪽 90도 동0 -> 남2 -> 서1 -> 북3
            // 왼쪽 동0 -> 북3 -> 서1 -> 남2
            int left = 0, right = 0;

            switch(now.d) {
                case 0: left = 3; right = 2; break;
                case 1: left = 2; right = 3; break;
                case 2: left = 0; right = 1; break;
                case 3: left = 1; right = 0; break;
            }

            if(!v[now.r][now.c][left]) {
                v[now.r][now.c][left] = true;
                q.add(new Point(now.r,now.c,left,now.cnt+1));
            }

            if(!v[now.r][now.c][right]) {
                v[now.r][now.c][right] = true;
                q.add(new Point(now.r,now.c,right,now.cnt+1));
            }

        }

        return -1;
    }

    private static boolean check(int r, int c) {
        return r>=0 && r<M && c>=0 && c<N;
    }

    public static class Point {
        int r;
        int c;
        int d;
        int cnt;

        public Point(int r, int c, int d,int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }
    }

}