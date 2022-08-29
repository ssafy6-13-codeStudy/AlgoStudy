import java.io.*;
import java.util.*;

public class Main {

    /*
    공항
    골드2
    204ms
    22028kb
     */

    static int G, P;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        for (int i = 0; i < P; i++) {
            int p = Integer.parseInt(br.readLine());

            if (find(p) == 0) break;
            cnt++;

            union(find(p), find(p) - 1);
        }

        System.out.println(cnt);
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parent[x] = y;

    }


}