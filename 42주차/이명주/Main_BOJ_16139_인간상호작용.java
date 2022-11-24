import java.util.*;
import java.io.*;

/*
740ms
108088kb
->
배열 크기를 페이지 크기에 1을 추가함
sum[to] - sum[from] 한 후 따로 해당 위치 알파벳 확인 할 필요 없이
sum[to] - sum[from-1]만 해주면 된다.
668ms
106672kb

목표
l~r 사이에서 a가 몇 번 나타나는지 구해라

고려사항
S <= 200,000
q <= 200,000

1. 누적합처럼 해당 인덱스에 알파벳이 몇개까지 있는지 저장한다.
2. 그냥 스트링 잘라서 알파벳 하나하나 센다.
*/
class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        int q = Integer.parseInt(br.readLine());
        int[][] arr = new int[26][s.length()+1]; // 알파벳, 인덱스

        // s * 26
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < 26; j++)
                arr[j][i] = arr[j][i - 1];

            arr[s.charAt(i-1)-97][i]++;
        }

        // q
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char c = st.nextToken().charAt(0);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int cnt = arr[c - 97][to+1] - arr[c - 97][from];

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}