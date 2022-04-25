import java.util.*;
import java.io.*;

/*
    주사위 굴리기 2
    골드 3
    시간 : 180ms
    메모리 : 32632kb
*/

public class Main {

    static int N,M,K;
    static int[] dice = {0,1,2,3,4,5,6};
    static int[][] map;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int d = 0;
    static int x=0,y=0;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {

            int nx = x + dr[d];
            int ny = y + dc[d];

            if(!check(nx,ny)){
                d = (d+2)%4;
                nx = x + dr[d];
                ny = y + dc[d];
            }

            roll(d);

            int a = dice[6];

            int b = map[nx][ny];

            if ( a > b)
                d = (d +1)%4;
            else if (a < b)
                d = (d==0)?3:d-1;

            answer += b * bfs(nx,ny,b);
            x = nx;
            y = ny;
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y, int b) {
        boolean[][] v = new boolean[N][M];
        int max = 1;

        Queue<int[]> queue = new LinkedList<>();

        v[x][y] = true;
        queue.add(new int[]{x,y});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dr[d];
                int ny = cur[1] + dc[d];

                if(!check(nx,ny)||v[nx][ny]) continue;

                if(map[nx][ny] == b) {
                    queue.add(new int[]{nx,ny});
                    v[nx][ny] = true;
                    max +=1;
                }
            }
        }

        return max;
    }

    private static boolean check(int x, int y) {
        return x>=0 && x<N && y>=0 && y<M;
    }

    private static void roll(int d) {

        int tmp = dice[1];

        if (d==0){
            // 동
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;

        } else if(d==1) {
            // 남
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;

        } else if(d==2) {
            // 서
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else {
            // 북
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        }
    }

}