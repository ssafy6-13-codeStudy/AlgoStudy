import java.util.*;
import java.io.*;

/*
21772kb
340ms

1km가는데 1l 연료 샌다
주유소 n개 최소한으로 멈추기
탱크 제한 없음

주유소에서 멈추는 횟수를 최소로

마을에 도착 할 수 없다면
현재 시점에서 갈 수 있는 주유소 중 기름이 제일 많은 곳으로 가기

-> 완전 잘못 짬..
한 구간에서 무조건 주유소 한개 가는게 아님
 */
class Main {

    static int N, L, P;
    static PriorityQueue<int[]> queue;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queue = new PriorityQueue<>((o1, o2) -> o1[0]-o2[0]);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        System.out.println(go());
    }

    public static int go() {
        int answer = 0;

        PriorityQueue<int[]> queue2 = new PriorityQueue<>((o1,o2)->o2[1]-o1[1]);

        while (P < L) {

            while(!queue.isEmpty() && queue.peek()[0] <= P) {
                queue2.add(queue.poll());
            }

            if(queue2.isEmpty()) return -1;

            answer++;
            P += queue2.poll()[1];
        }

        return answer;
    }
}