import java.util.*;
import java.io.*;

/*
11672kb
80ms
*/
class Main {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[4][4];
        Fish[] fish = new Fish[17];
        fish[0] = new Fish();

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = n;
                fish[n] = new Fish(n, d, i, j, true);
            }
        }

        eatFish(0, 0, 0, map, fish);

        System.out.println(max);
    }

    public static void eatFish(int r, int c, int sum, int[][] map, Fish[] fish) {

        // 상어가 물고기를 먹는다.
        sum += map[r][c];
        int d = fish[map[r][c]].d;
        fish[map[r][c]].alive = false;
        map[r][c] = -1;

        max = Math.max(sum, max);

        // 물고기가 이동한다.
        moveFish(map, fish);

        // 상어가 갈 수 있는 위치의 물고기를 먹는다.
        for (int i = 1; i <= 3; i++) {
            int nr = r + dr[d] * i;
            int nc = c + dc[d] * i;

            if (!check(nr, nc)) break;
            if (map[nr][nc] == 0) continue;

            int[][] newMap = new int[4][4];
            for (int j = 0; j < 4; j++) {
                System.arraycopy(map[j],0,newMap[j],0,map.length);
            }

            Fish[] newFish = new Fish[17];
            for (int j = 0; j < 17; j++) {
                newFish[j] = fish[j].clone();
            }
            newMap[r][c] = 0;
            eatFish(nr, nc, sum, newMap, newFish);
        }
    }

    public static void moveFish(int[][] map, Fish[] fish) {

        for (int i = 1; i <= 16; i++) {
            if (!fish[i].alive) continue;

            Fish now = fish[i];

            for (int d = 0; d < 8; d++) {

                int nd = (now.d + d) % 8;
                int nr = now.r + dr[nd];
                int nc = now.c + dc[nd];

                // 벽이 아니고 상어가 있는 자리가 아니라면
                if (check(nr, nc) && map[nr][nc] != -1) {
                    int temp = map[nr][nc];
                    map[nr][nc] = i;

                    // 물고기가 있으면 위치 교환
                    if (temp > 0) {
                        fish[temp].r = now.r;
                        fish[temp].c = now.c;
                        map[now.r][now.c] = temp;
                    } else
                        map[now.r][now.c] = 0;

                    now.r = nr;
                    now.c = nc;
                    now.d = nd;

                    break;
                }
            }
        }
    }

    public static boolean check(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    public static class Fish {
        int n;
        int d;
        int r;
        int c;
        boolean alive;

        public Fish() {}

        public Fish(int n, int d, int r, int c, boolean alive) {
            this.n = n;
            this.d = d;
            this.r = r;
            this.c = c;
            this.alive = alive;
        }

        public Fish clone() {
            return new Fish(n,d,r,c, alive);
        }
    }
}