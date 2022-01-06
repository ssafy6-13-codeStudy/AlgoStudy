import java.io.*;
import java.util.*;

/*
    뿌요뿌요
    골드 4
    시간 : 80ms
    메모리 : 11584kb
 */

public class Main {

  /*

  */

    static int result = 0;
    static char[][] map;
    static boolean[][] v;
    static int[] dr = {0, -1, 1, 0};
    static int[] dc = {1, 0, 0, -1};

    public static void main(String[] args) throws Exception {

        map = new char[12][6];
        v = new boolean[12][6];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {

            // 방문 초기화
            for (int i = 0; i < 12; i++) {
                Arrays.fill(v[i], false);
            }

            int cnt = 0;
            // 좌표 탐색
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    // 방문하지 않고 뿌요가 있는 좌표라면 bfs
                    if (!v[i][j] && map[i][j] != '.') {
                        if (bfs(i, j)) cnt++;
                    }

                }
            }

            if (cnt == 0) break;
            result++;
            // 아래로 이동 (중력)
            go();
        }


        System.out.println(result);
    }

    private static void go() {

        for (int j = 0; j < 6; j++) {
            //바닥부터 탐색해서 빈 칸이라면 한칸 아래로 내려준다.
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] != '.') {
                    int nr = i;
                    while (true) {
                        nr++;

                        if (!check(nr, j)) break;

                        if (map[nr][j] == '.') {
                            map[nr][j] = map[nr - 1][j];
                            map[nr - 1][j] = '.';
                        } else break;

                    }
                }
            }
        }


    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < 12 && c >= 0 && c < 6;
    }

    private static boolean bfs(int r, int c) {

        // 큐 : 탐색해볼 좌표를 저장하는 공간
        Queue<point> q = new LinkedList<>();
        // 리스트 : map[r][c]와 같인 색의 뿌요를 저장하는 공간 (4 이상 시 제거할 좌표들)
        ArrayList<point> arr = new ArrayList<>();

        char color = map[r][c];

        q.add(new point(r,c));
        arr.add(new point(r, c));
        v[r][c] = true;

        while(!q.isEmpty()){

            point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!check(nr,nc)) continue;

                if(map[nr][nc] == color && !v[nr][nc]){
                    arr.add(new point(nr,nc));
                    q.add(new point(nr,nc));
                    v[nr][nc] = true;
                }
            }
        }
        // 뿌요가 4개 이상 모였을 시 터뜨린다.
        if(arr.size()>=4){
            for(point p : arr){
                map[p.r][p.c] = '.';
            }

            return true;
        }

        return false;
    }


    private static class point {
        int r;
        int c;

        public point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
