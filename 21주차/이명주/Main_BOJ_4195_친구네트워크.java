import java.io.*;
import java.util.*;

public class Main {

    /*
    친구 네트워크
    골드 2
    시간 : 1008ms
    메모리: 78844kb
     */

    static int[] parents, ranks;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0) {

            int F = Integer.parseInt(br.readLine());

            parents = new int[F*2];
            ranks = new int[F*2];

            for (int i = 0; i < F*2; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }

            HashMap<String,Integer> map = new HashMap<>();

            int idx = 0;
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if(!map.containsKey(a))
                    map.put(a,idx++);
                if(!map.containsKey(b))
                    map.put(b,idx++);

                sb.append(union(map.get(a),map.get(b))+"\n");
            }

        }
        System.out.println(sb);

    }

    public static int union(int a, int b) {

        a = find(a);
        b = find(b);

        if (a != b) {
            parents[b] = a;
            ranks[a] += ranks[b];

        }

        return ranks[a];
    }

    public static int find(int a) {

        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

}
