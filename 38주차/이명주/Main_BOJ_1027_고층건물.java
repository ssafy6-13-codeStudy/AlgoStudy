import java.util.*;
import java.io.*;

/*
11608kb	
76ms
*/
class Main {

    static int N;
    static int[] arr;
    static int[] visible;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visible = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N-1; i++) {
            visible[i]++;
            visible[i+1]++;
            // 바로 옆 건물과의 기울기
            double slope = arr[i+1]-arr[i];
            // 그 다음 건물 기울기와 비교
            for(int j=i+2; j<N; j++) {
                double next = (double)(arr[j] - arr[i]) / (j-i);
                // 다음 건물의 기울기가 작거나 같으면 보이지 않는다.
                if(next <= slope) continue;
                // 기울기가 크다면 갱신
                slope = next;
                visible[i]++;
                visible[j]++;
            }
        }
        Arrays.sort(visible);
        System.out.println(visible[visible.length-1]);
    }
}