import java.io.*;
import java.util.*;

public class Main {

    /*
    인싸들의 가위바위보
    골드 3
    시간 : 316ms
    메모리 : 39936kb
     */

    // 표
    static int[][] map;
    static int[][] orders;
    static int[] index;
    static int[] win;
    static boolean[] v;
    static boolean isWin;
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 낼 수 있는 손동작 수 보다 승수가 더 클 때
        if(N < K) {
            System.out.println(0);
            return;
        }

        map = new int[N+1][N+1];
        orders = new int[4][20];
        v = new boolean[N+1];

        for(int i = 1 ; i <= N ; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 경희
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 20 ; i++) {
            orders[2][i] = Integer.parseInt(st.nextToken());
        }
        // 민호
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 20 ; i++) {
            orders[3][i] = Integer.parseInt(st.nextToken());
        }

        perm(0);

        if(isWin) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void perm(int cnt) {
        if(isWin) return;

        if(cnt == N) {
            index = new int[4];
            win = new int[4];

            // 시뮬레이션 시작
            if(simulation(1, 2)) {
                isWin = true;
            };

            return;
        }

        for(int i = 1 ; i <= N ; ++i) {
            if(!v[i]) {
                v[i] = true;
                orders[1][cnt] = i;

                perm(cnt + 1);
                if(isWin) return;

                orders[1][cnt] = 0;
                v[i] = false;
            }
        }
    }

    private static boolean simulation(int a, int b) {

        if(win[1] >= K) {
            return true;
        }

        if(index[1] >= N || win[2] >= K || win[3] >= K) {
            return false;
        }

        int nextPlayer;

        if((a == 1 && b == 2) || (a == 2 && b == 1)) nextPlayer = 3;
        else if((a == 1 && b == 3) || (a == 3 && b == 1)) nextPlayer = 2;
        else nextPlayer = 1;

        int result = map[orders[a][index[a]]][orders[b][index[b]]];

        index[a]++;
        index[b]++;

        // a > b
        if(result == 2) {
            win[a]++;
            if(simulation(a, nextPlayer)) return true;

        }
        // a < b
        else if(result == 0) {
            win[b]++;
            if(simulation(b, nextPlayer)) return true;
        }
        // a = b 뒤가 이김
        else {
            if(a > b) {
                win[a]++;
                if(simulation(a, nextPlayer)) return true;
            } else {
                win[b]++;
                if(simulation(b, nextPlayer)) return true;
            }
        }

        return false;
    }

}
