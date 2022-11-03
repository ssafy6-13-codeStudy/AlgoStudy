
import java.util.*;
import java.io.*;

/*
플러그를 빼는 횟수를 최소화
앞으로 쓰지 않을 전자기기 혹은 가장 순서가 먼 전자기기를 뽑아야함
11608kb
76ms
 */
class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> tab = new ArrayList<>();
        ArrayList<Integer> order = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        int answer = 0;

        for (int i = 0; i < K; i++) {
            int now = order.get(i);
            // 멀티탭에 꽃혀있으면 패스
            order.set(i,-1);
            if(tab.contains(now)) continue;

            if (tab.size() == N) {
                int next = 0;
                int idx = 0;
                // 멀티탭에 꽂힌 전자기기 체크
                for (int n : tab) {
                    // 앞으로 안쓰인다면 바로 그 기기 뽑기
                    if (!order.contains(n)) {
                        idx = n;
                        break;
                    } // 가장 마지막에 쓰이는 기기 뽑기
                    else if (order.indexOf(n) > next) {
                        idx = n;
                        next = order.indexOf(n);
                    }
                }
                tab.remove((Integer) idx);
                answer++;
            }
            tab.add(now);
        }

        System.out.println(answer);

    }
}