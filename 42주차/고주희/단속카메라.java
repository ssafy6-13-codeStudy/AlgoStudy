import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int[] route : routes) {
            que.offer(route);
        }
        int start = -30001;
        int end = -30001;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            if(cur[0] > end) {
                answer++;
                start = cur[0];
                end = cur[1];
            } else {
                start = Math.max(start, cur[0]);
                end = Math.min(end, cur[1]);
            }
        }
        return answer;
    }
}

/*
+5
정확성  테스트
테스트 1 〉	통과 (0.77ms, 83.3MB)
테스트 2 〉	통과 (0.86ms, 78.1MB)
테스트 3 〉	통과 (0.93ms, 72.2MB)
테스트 4 〉	통과 (0.77ms, 73.1MB)
테스트 5 〉	통과 (0.73ms, 77.9MB)
효율성  테스트
테스트 1 〉	통과 (8.44ms, 52.7MB)
테스트 2 〉	통과 (3.86ms, 52.9MB)
테스트 3 〉	통과 (8.51ms, 57.5MB)
테스트 4 〉	통과 (1.27ms, 52MB)
테스트 5 〉	통과 (4.96ms, 53.9MB)
*/
