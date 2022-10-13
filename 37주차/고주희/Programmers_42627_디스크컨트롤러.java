import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int start;
        int taken;
        
        public Node(int start, int taken) {
            this.start = start;
            this.taken = taken;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.taken - o.taken;
        }
    }
    public int solution(int[][] jobs) {
        int N = jobs.length;
        PriorityQueue<Node> que = new PriorityQueue<>();
        Arrays.sort(jobs, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int time = jobs[0][0];
        int idx = 0;
        int waiting = 0;
        while(idx < N || !que.isEmpty()) {
            while(idx < N && jobs[idx][0] <= time) {
                que.offer(new Node(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            if(idx < N && que.isEmpty()) {
                if(time < jobs[idx][0]) {
                    time = jobs[idx][0];
                }
                continue;
            }
            Node cur = que.poll();
            // System.out.println(cur.start + " " + cur.taken);
            time += cur.taken;
            waiting += (time - cur.start);
        }
        return waiting/N;
    }
}
/*
+7
테스트 1 〉	통과 (3.49ms, 81.1MB)
테스트 2 〉	통과 (3.10ms, 72.3MB)
테스트 3 〉	통과 (9.14ms, 65.3MB)
테스트 4 〉	통과 (2.95ms, 66.1MB)
테스트 5 〉	통과 (2.89ms, 79.9MB)
테스트 6 〉	통과 (1.93ms, 85MB)
테스트 7 〉	통과 (2.84ms, 70.8MB)
테스트 8 〉	통과 (2.04ms, 75.3MB)
테스트 9 〉	통과 (1.33ms, 74.7MB)
테스트 10 〉	통과 (2.43ms, 76.4MB)
테스트 11 〉	통과 (0.93ms, 72.3MB)
테스트 12 〉	통과 (1.27ms, 76.2MB)
테스트 13 〉	통과 (0.88ms, 70.8MB)
테스트 14 〉	통과 (1.20ms, 75.6MB)
테스트 15 〉	통과 (1.00ms, 74.9MB)
테스트 16 〉	통과 (0.86ms, 64.6MB)
테스트 17 〉	통과 (1.85ms, 74.8MB)
테스트 18 〉	통과 (0.83ms, 73.8MB)
테스트 19 〉	통과 (0.79ms, 75MB)
테스트 20 〉	통과 (0.82ms, 73.8MB)
*/
