import java.util.*;
import java.io.*;

/*
76ms
11596kb
 */
class Main {
    static int x, o;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();

            if (s.equals("end")) break;

            map = new char[3][3];

            x = 0;
            o = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = s.charAt(i * 3 + j);
                    if (map[i][j] == 'O') o++;
                    else if (map[i][j] == 'X') x++;
                }
            }

            // x는 o와 같거나 +1이어야 한다.
            if (!(x == o || x == o + 1)) {
                sb.append("invalid\n");
                continue;
            }

            if (check()) sb.append("valid\n");
            else sb.append("invalid\n");
        }

        System.out.println(sb);
    }

    public static boolean check() {
        int xBingo = 0;
        int oBingo = 0;

        // 가로 검사
        for (int i = 0; i < 3; i++) {
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                if(map[i][0]=='X') xBingo++;
                else if(map[i][0]=='O') oBingo++;
            }
        }

        // 세로 검사
        for (int i = 0; i < 3; i++) {
            if(map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                if(map[0][i]=='X') xBingo++;
                else if(map[0][i]=='O') oBingo++;
            }
        }

        // 대각선 검사
       if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
           if(map[0][0]=='X') xBingo++;
           else if(map[0][0]=='O') oBingo++;
       }

        if(map[2][0] == map[1][1] && map[1][1] == map[0][2]) {
            if(map[2][0]=='X') xBingo++;
            else if(map[2][0]=='O') oBingo++;
        }

        if (xBingo > 0) {
            if (oBingo > 0) return false;

            if (x == o + 1) return true;
            else return false;
        }

        if (oBingo > 0) {

            if (x == o) return true;
            else return false;
        }

        if (x==5 && o==4) return true;

        return false;
    }
}