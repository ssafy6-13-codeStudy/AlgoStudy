import java.io.*;
import java.util.*;

/*
    가장 가까운 공통 조상
    시간 : 22080ms
    메모리 : 192kb
*/

public class Main {

    static int T, N;
    static int parent[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 0; t < T; t++) {

            N = Integer.parseInt(br.readLine());
            parent = new int[N+1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
            }

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] isParent = new boolean[N+1];

            // a의 조상들 체크
            while (a!=0){
                isParent[a] = true;
                a = parent[a];
            }

            while(true){
                // b의 조상 중 처음으로 a의 조상과 같은 조상 출력
                if(isParent[b]) {
                    sb.append(b).append("\n");
                    break;
                }

                b = parent[b];
            }

        }

        System.out.println(sb);
    }
}
