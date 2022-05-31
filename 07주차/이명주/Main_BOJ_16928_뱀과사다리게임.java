import java.io.*;
import java.util.*;

/*
    뱀과 사다리 게임
    실버 1
    시간 : 84ms
    메모리 : 11880kb
*/

public class Main {

    static int N,M;
    static int [] map;
    static boolean[] v;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[101]; // map[출발지점] = 도착지점 (없을 시 0)
        v = new boolean[101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<point> q = new LinkedList<>();

        q.add(new point(1,0)); // 1부터 출발
        v[1] = true;

        while(!q.isEmpty()){

            point cur = q.poll();

            if(cur.p==100) return cur.t; //100이면 리턴

            for (int i = 1; i <= 6; i++) {
                int np = cur.p+i; //주사위 
                if(np>100) break; //100을 넘어가면 break
                if(map[np]==0&&!v[np]) { // 뱀,사다리가 없고 방문한 적 없으면
                    q.add(new point(np,cur.t+1));
                    v[np]=true;
                }
                else if(map[np]!=0&&!v[map[np]]) { // 뱀, 사다리가 있고 방문한 적 없으면
                    q.add(new point(map[np],cur.t+1));
                    v[map[np]] = true;
                }
            }

        }

        return -1;
    }

    private static class point {
        int p;
        int t;

        public point(int p, int t) {
            this.p = p;
            this.t = t;
        }
    }
}
