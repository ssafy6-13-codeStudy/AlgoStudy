import java.io.*;
import java.util.*;

/*
    마법사 상어와 파이어볼
    골드 4
    시간 : ms
    메모리 : kb
*/

public class Main {

    /*

    i번 파이어볼의 위치 : r,c
    질량 : m
    방향 : d
    속력 : s

    1. 이동
    2. 2개 이상의 파이어볼이 있으면
        1. 하나로 합쳐진다.
        2. 4개로 나누어진다.
            1. 질량 : 합 / 5
            2. 속력 : 합 / 합쳐진 개수
            3. 방향이 모두 홀수나 짝수면 0, 2, 4, 6
               아니면 1, 3, 5, 7
        3. 질량이 0이라면 없어진다.

     */

    static int N, M, K;
    static Queue<FireBall> queue;
    static ArrayList<FireBall> [][]map;

    static int dr[] = {-1,-1,0,1,1,1,0,-1};
    static int dc[] = {0,1,1,1,0,-1,-1,-1};
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            queue.add(new FireBall(r,c,m,s,d));
        }

        for (int i = 0; i < K; i++) {
            move();
            split();
        }

        System.out.println(getSum());

    }

    private static void move() {
        while(!queue.isEmpty()){
            FireBall fireBall = queue.poll();

            int nr = (fireBall.r + fireBall.s*dr[fireBall.d]%N + N)%N;
            int nc = (fireBall.c + fireBall.s*dc[fireBall.d]%N + N)%N;
            fireBall.r = nr;
            fireBall.c = nc;

            map[nr][nc].add(fireBall);
        }
    }

    private static void split() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size()>1){

                    for (FireBall fireBall : map[i][j]) {

                    }
                }

            }
        }

    }

    private static int getSum() {

        return 0;
    }

    static class FireBall {
        int r, c, m, d, s;

        public FireBall(int r, int c,int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }

    }
}
