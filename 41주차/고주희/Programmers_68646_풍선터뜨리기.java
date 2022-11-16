import java.util.*;

class Solution {
    public int solution(int[] a) {

        PriorityQueue<int[]> left = new PriorityQueue<>(new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> right = new PriorityQueue<>(new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int N = a.length;
        for(int i = 0; i < N; i++) {
            right.offer(new int[]{a[i], i});
        }
        
        boolean[] v = new boolean[N];
        v[right.peek()[1]] = true;
        
        for(int i = 0; i < N; i++) {
            left.offer(new int[]{a[i], i});
            while(!right.isEmpty() && right.peek()[1] < i) {
                right.poll();
            }
            v[left.peek()[1]] = true;
            v[right.peek()[1]] = true;
            
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if(v[i]) {
                answer++;
            }
        }
        return answer;
    }
}

/*
+7
정확성  테스트
테스트 1 〉	통과 (0.77ms, 73.5MB)
테스트 2 〉	통과 (0.93ms, 76.4MB)
테스트 3 〉	통과 (2.74ms, 76.1MB)
테스트 4 〉	통과 (73.27ms, 91.4MB)
테스트 5 〉	통과 (283.80ms, 161MB)
테스트 6 〉	통과 (369.21ms, 177MB)
테스트 7 〉	통과 (347.23ms, 203MB)
테스트 8 〉	통과 (484.52ms, 195MB)
테스트 9 〉	통과 (508.71ms, 203MB)
테스트 10 〉	통과 (525.16ms, 193MB)
테스트 11 〉	통과 (619.06ms, 205MB)
테스트 12 〉	통과 (525.02ms, 195MB)
테스트 13 〉	통과 (490.83ms, 190MB)
테스트 14 〉	통과 (572.69ms, 181MB)
테스트 15 〉	통과 (615.76ms, 185MB)
*/
