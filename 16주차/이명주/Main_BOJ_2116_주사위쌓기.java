import java.util.*;
import java.io.*;

/*
    주사위 쌓기
    골드 4
    시간 : 196ms
    메모리 : 19864kb
*/

public class Main {

    static int[][] dice;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dice[i] = new int[6];
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;

        for (int i = 0; i < 6; i++) {
            max = Math.max(max, find(i));
        }

        System.out.println(max);

    }

    private static int find(int d) {

        int sum = 0;
        int down = dice[0][d];
        int up = findOpposite(0, d);

        sum += findMax(up, down);

        // 2번 ~ N번 주사위 돌기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                if (dice[i][j] == up) {
                    down = up;
                    up = findOpposite(i, j);
                    break;
                }
            }
            sum += findMax(up, down);
        }
        return sum;
    }

    private static int findMax(int up, int down) {
        if (up + down == 11) return 4;
        else if (up == 6 || down == 6) return 5;
        else return 6;
    }

    private static int findOpposite(int index, int d) {
        switch (d) {
            case 0:
                return dice[index][5];
            case 1:
                return dice[index][3];
            case 2:
                return dice[index][4];
            case 3:
                return dice[index][1];
            case 4:
                return dice[index][2];
            case 5:
                return dice[index][0];
        }
        return -1;
    }

}