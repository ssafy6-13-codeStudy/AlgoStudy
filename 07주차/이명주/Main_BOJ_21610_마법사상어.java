import java.io.*;
import java.util.*;

/*
    마법사 상어와 비바라기
    골드 5
    시간 : 192ms
    메모리 : 18852kb
*/

public class Main {
    
    /*
    모든 구름 : d방향으로 s칸 이동
    각 구름 위치 물의 양 +1
    대각선 방향의 물이 있는 바구니 수만큼 물의 양 증가
    물의 양 2 이상인 칸에 구름 생김, 2 감소 (기존 구름이 있는 곳은 안됨)
    구름 사라짐 
    */

    static int N,M, result;
    static Queue<cloud> q = new LinkedList<>();
    static int[][] order;
    static int[][] map;
    static boolean[][] v;
    static int []dr = {0,-1,-1,-1,0,1,1,1};
    static int []dc = {-1,-1,0,1,1,1,0,-1};
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        order = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                order[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        q.add(new cloud(N-2,0));
        q.add(new cloud(N-1,0));
        q.add(new cloud(N-2,1));
        q.add(new cloud(N-1,1));

        for (int i = 0; i < M; i++) {
            
            v = new boolean[N][N]; // 구름이면 체크
            int d = order[i][0];
            int s = order[i][1];
            
            // 구름을 d방향으로 s 이동 && 물 +1
            move(d-1,s);
            // 물복사 버그 (대각선에 물 있는 바구니 수만큼 물 증가)
            add();
            //구름 생성
            makeCloud();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        
        // 합 출력
        System.out.println(result);
    }

    private static void move(int d, int s) {

        for (cloud cloud : q) {
            cloud.r = (N + cloud.r + dr[d]*( s % N)) % N;
            cloud.c = (N + cloud.c + dc[d]*( s % N)) % N;

            map[cloud.r][cloud.c]++;
        }
    }


    private static void add() {

        while(!q.isEmpty()){

            cloud c = q.poll();
            v[c.r][c.c] = true; // 구름이었다는 걸 체크

            int n = 0; // 대각선에서 물 있는 칸 수
            for (int d = 0; d < 8; d++) {
                int nr = c.r+dr[d];
                int nc = c.c+dc[d];

                if(!check(nr,nc)) continue;

                if(map[nr][nc]>0) n++;
            }

            map[c.r][c.c] += n;

        }

    }

    private static boolean check(int r, int c) {
        return r>=0&&r<N&&c>=0&&c<N;
    }

    private static void makeCloud() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c]>=2 && !v[r][c]) {
                    q.add(new cloud(r, c));
                    map[r][c] -= 2;
                }
            }
        }


    }

    private static class cloud {
        int r,c;

        public cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
