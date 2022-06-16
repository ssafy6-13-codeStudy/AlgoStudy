import java.io.*;
import java.util.*;

public class Main {
  
    /*
    거짓말
    골드 4
    시간: 88ms
    메모리: 11868kb
    */

    static int N, M;
    static boolean[] know;
    static int[] parents;
    static int[] party;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = atoi(st.nextToken());
        M = atoi(st.nextToken());
        know = new boolean[N + 1];
        parents = new int[N + 1];
        party = new int[M];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int n = atoi(st.nextToken());

        if (n == 0) {
            System.out.println(M);
            System.exit(0);
        }

        while (n-- > 0) {
            know[atoi(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n = atoi(st.nextToken()) - 1;
            int parent = atoi(st.nextToken());
            party[i] = parent;

            while (n-- > 0) {
                int tmp = atoi(st.nextToken());
                union(parent, tmp);
            }
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            if (!know[find(party[i])])
                answer++;
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;

            if (know[aRoot] || know[bRoot])
                know[aRoot] = know[bRoot] = true;
        }
    }

    private static int find(int a) {

        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    public static int atoi(String s) {
        return Integer.parseInt(s);
    }
}
