import java.io.*;
import java.util.*;

/*
    바이러스
    시간 : 11864ms
    메모리 : 92kb
*/

public class Main {

    static int N;
    static boolean[] v;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        v = new boolean[N+1];
        list = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        int line = Integer.parseInt(br.readLine());

        for (int i = 0; i < line; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        System.out.println(bfs());

    }

    private static int bfs() {

        int cnt=0;
        Queue<Integer> q = new LinkedList<>();
        v[1] = true;
        q.add(1);

        while(!q.isEmpty()){

            int start = q.poll();

            for (Integer n : list[start]) {
                if(!v[n]) {
                    q.add(n);
                    v[n]=true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
