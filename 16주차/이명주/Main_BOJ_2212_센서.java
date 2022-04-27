import java.util.*;
import java.io.*;

/*
    센서
    골드 5
    시간 : 164ms
    메모리 : 15272kb
*/

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] sensors = new int[N];

        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);

        ArrayList<Integer> gap = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            gap.add(sensors[i+1]-sensors[i]);
        }

        Collections.sort(gap);

        int answer = 0;

        for (int i = 0; i < N-K; i++) {
          answer+=gap.get(i);
        }

        System.out.println(answer);
    }


}