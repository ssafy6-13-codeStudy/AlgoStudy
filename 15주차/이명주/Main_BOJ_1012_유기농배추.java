import java.util.*;
import java.io.*;

/*
    유기농 배추
    실버 2
    시간 : 100ms
    메모리 : 13288kb
*/

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int M,N,K;
    static boolean[][] map;
    static int[] dr = {0,1,-1,0};
    static int[] dc = {1,0,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            int answer = 0;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]){
                        answer++;
                        dfs(i,j);
                    }
                }
            }

            sb.append(answer+"\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int r, int c) {

        map[r][c] = false;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(check(nr,nc)&&map[nr][nc])
                dfs(nr,nc);
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }


}