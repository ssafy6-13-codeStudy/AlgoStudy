import java.util.*;
import java.io.*;

/*
    주사위 굴리기
    골드 4
    시간 : 88ms
    메모리 : 11864kb
*/

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] dice;
    static int M, N, K, x, y;
    static int [][] map;
    static int[] dr = {0, 0, -1, 1}; //동 서 북 남
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dice = new int[7];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int order = Integer.parseInt(st.nextToken());

            move(order);
        }

        System.out.println(sb);
    }

    private static void move(int order) {

        int nx = x + dr[order-1];
        int ny = y + dc[order-1];

        if(!check(nx,ny)) return;

        int tmp = dice[1];

        if(order==1) {
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;
        }
        else if(order == 2) {
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        }
        else if(order == 3) {
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        }
        else {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }

        if(map[nx][ny] == 0) {
            map[nx][ny] = dice[6];
        } else {
            dice[6] = map[nx][ny];
            map[nx][ny] =0;
        }

        sb.append(dice[1]).append("\n");

        x = nx;
        y = ny;
    }

    private static boolean check(int x, int y) {
        return x>=0 && x<N && y>=0&& y<M;
    }
}