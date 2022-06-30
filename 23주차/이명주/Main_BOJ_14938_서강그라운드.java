import java.io.*;
import java.util.*;

public class Main {

    /*
    서강그라운드
    골드 4
    시간: 112ms
    메모리: 12228kb
    */

    static final int INF = 1000000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());
        int r = stoi(st.nextToken());

        int []item = new int[n];
        int [][]dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i],INF);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            item[i] = stoi(st.nextToken());

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken())-1;
            int b = stoi(st.nextToken())-1;
            dist[a][b] = dist[b][a] = stoi(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || j == k || k == i) continue;
                    dist[j][k] = Math.min(dist[j][i] + dist[i][k], dist[j][k]);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int sum = item[i];
            for (int j = 0; j < n; j++) {
                if(dist[i][j]<=m) {
                    sum += item[j];
                }
            }
            ans = Math.max(ans,sum);
        }


        System.out.println(ans);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
