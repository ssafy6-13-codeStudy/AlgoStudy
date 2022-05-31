import java.io.*;
import java.util.*;

public class Main {

    static int N,K,L,sec=0; // sec = 게임 진행 시간
    static int[][] map;
    static Queue<point> queue = new LinkedList<>(); // 뱀의 방향 전환 정보
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        K = Integer.parseInt(br.readLine());

        // 사과가 있는 좌표를 1로 설정, 뱀이 있는 좌표는 -1로 설정
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;

            map[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());

        // 뱀이 이동할 방향 전환 정보를 입력받는다.
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int dir = st.nextToken().charAt(0);
            queue.add(new point(time,dir));
        }

        go();

        System.out.println(sec);
    }

    private static void go() {

        Deque<point> snake = new ArrayDeque<>();
        int d = 0; // 현재 뱀의 머리 방향

        //0,0에서 게임 시작
        snake.add(new point(0,0));
        map[0][0] = -1;

        while(true){
            sec++;
            //뱀의 머리 좌표를 불러온다.
            point head = snake.peekFirst();

            //현재 방향으로 이동
            int nr = head.r + dr[d];
            int nc = head.c + dc[d];

            //벽에 부딪히면 게임 종료
            if(!check(nr,nc)) return;

            //몸에 부딪히면 게임 종료
            if(map[nr][nc]==-1) return;

            //사과가 없다면 몸이 늘어나지 않으므로 꼬리 제거
            else if(map[nr][nc]!=1){
                point tail = snake.pollLast();
                map[tail.r][tail.c] = 0;
            }

            //앞으로 전진
            snake.addFirst(new point(nr,nc));
            map[nr][nc] = -1;

            //전환정보에 따라 방향 전환
            if(!queue.isEmpty() && queue.peek().r==sec){
                char dir = (char) queue.poll().c;
                if(dir == 'L') d= (d+3)%4;
                else d= (d+1)%4;
            }

        }
    }

    private static boolean check(int r, int c) {
        return r>=0&&r<N&&c>=0&&c<N;
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
