import java.io.*;
import java.util.*;

/*
    인구이동
    골드 5
    시간 : 540ms
    메모리 : 295208kb
*/

public class Main {

    static int N, L, R, result = 0;
    static int[][] A;
    static boolean[][] v;
    static int dr[] = {0, 0, -1, 1};
    static int dc[] = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N]; //각 나라 인구
        v = new boolean[N][N]; // 방문여부

        // 인구 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean hasUnion = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j] && findUnion(i, j)) {  //방문한 적 없으면 연합 찾고 연합이 있으면
                        hasUnion = true; // true 변경
                    }
                }
            }

            if (!hasUnion) break; //연합이 없다면 break

            result++;

            // 연합여부 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(v[i], false);
            }

        }

        System.out.println(result);
    }

    private static boolean findUnion(int r, int c) {
        Queue<Point> q = new LinkedList<>(); // 방문할 좌표 큐
        List<Point> list = new ArrayList<>(); // 같은 연합 리스트
        int sum = A[r][c]; // 연합 인구수 합계

        v[r][c] = true;
        q.add(new Point(r, c));
        list.add(new Point(r, c));

        // bfs 돌면서 같은 연합인지 확인
        while (!q.isEmpty()) {

            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (!check(nr, nc) || v[nr][nc]) continue;

                int diff = Math.abs(A[cur.r][cur.c] - A[nr][nc]);

                if (diff >= L && diff <= R) {
                    v[nr][nc] = true;
                    q.add(new Point(nr, nc));
                    list.add(new Point(nr, nc));
                    sum += A[nr][nc];
                }
            }
        }

        //연합이 없을 시 f 리턴
        if (list.size() == 1) return false;

        //연합인 나라 인구수 평균으로 초기화
        for (Point p : list) {
            A[p.r][p.c] = sum / list.size();
        }

        return true;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}