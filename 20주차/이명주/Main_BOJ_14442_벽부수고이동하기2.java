import java.io.*;
import java.util.*;

public class Main {

    /*
    벽 부수고 이동하기 2
    골드 3
    시간 : 1580ms
    메모리 : 370700kb
    */

    static int N, M, K;
    static char[][] map;
    static boolean[][][] v;
    static int[] dr = {0, 1, -1, 0};
    static int[] dc = {1, 0, 0, -1};

    // (0,0) -> (N-1,M-1)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        v = new boolean[N][M][K+1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        v[0][0][0] = true;
        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0, 0, 0, 1));

        while (!q.isEmpty()) {

            Node n = q.poll();

            if(n.r==N-1&&n.c==M-1) return n.cnt;

            for (int d = 0; d < 4; d++) {
                int nr = n.r + dr[d];
                int nc = n.c + dc[d];

                if (check(nr, nc)) {

                    if (map[nr][nc] == '1' && n.k < K  && !v[nr][nc][n.k+1]) {
                        v[nr][nc][n.k+1] = true;
                        q.add(new Node(nr, nc, n.k +1, n.cnt + 1));
                    }
                    else if (map[nr][nc] == '0' && !v[nr][nc][n.k]) {

                        v[nr][nc][n.k] = true;
                        q.add(new Node(nr, nc, n.k, n.cnt + 1));
                    }

                }
            }

        }

        return -1;
    }

    public static class Node {
        int r;
        int c;
        int k;
        int cnt;

        public Node(int r, int c, int k, int cnt) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}