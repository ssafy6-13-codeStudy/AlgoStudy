import java.util.*;
import java.io.*;

/*
    촌수계산
    실버 2
    시간 : 76ms
    메모리 : 17000kb
*/

public class Main {

    static ArrayList<Integer>[] list;
    static boolean[] v;
    static int p1, p2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine())+1;

        list = new ArrayList[N];
        v = new boolean[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(p1);
        v[p1] = true;

        int depth = 0;

        while(!queue.isEmpty()){

            int n = queue.size();
            depth++;

            for (int i = 0; i < n; i++) {

              int cur = queue.poll();

              for (Integer integer : list[cur]) {
                if (!v[integer]) {
                  v[integer] = true;
                  queue.add(integer);
                }
                if (integer == p2)
                  return depth;
              }
            }
        }
        return -1;
    }
}