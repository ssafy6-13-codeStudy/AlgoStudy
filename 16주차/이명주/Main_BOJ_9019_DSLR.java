import java.util.*;
import java.io.*;

/*
    DSLR
    골드 5
    시간 : 2484ms
    메모리 : 295672kb
*/

public class Main {

    /*
    D : 2n mod 10000 & n을 두배로. 결과가 10000이상인 경우 10000으로 나눈 나머지.
    S : n-1 & 0 -> 9999
    L : d2 d3 d4 d1
    R : d4 d1 d2 d3
    */
    static int a, b;
    static StringBuilder sb = new StringBuilder();
    static boolean[] v = new boolean[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            v = new boolean[10001];

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static String bfs(int a, int b) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(a, ""));
        v[a] = true;

        while (!queue.isEmpty()) {

            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tmp = 0;
                char d = '0';

                if (i == 0) {
                    tmp = (cur.value * 2) % 10000;
                    d = 'D';
                } else if (i == 1) {
                    tmp = (cur.value == 0) ? 9999 : cur.value - 1;
                    d = 'S';
                } else if (i == 2) {
                    // 1234 2341
                    tmp = (cur.value%1000) * 10 + cur.value/1000;
                    d = 'L';
                } else if (i == 3) {
                    // 1234 4123
                    tmp = (cur.value%10) * 1000 + (cur.value/10);
                    d = 'R';
                }

                if(v[tmp]) continue;

                if(tmp == b) {
                    return cur.str+d;
                }
                else{
                    queue.add(new Node(tmp,cur.str+d));
                    v[tmp] = true;
                }

            }
        }

        return "";
    }

    public static class Node {
        int value;
        String str;

        public Node(int value, String str) {
            this.value = value;
            this.str = str;
        }
    }

}