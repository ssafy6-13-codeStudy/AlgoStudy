import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 =0, sum2 =0;
        for(int i=0;i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        int cnt = 0;
        // 양쪽 합이 같아질 때 까지
        while(sum1!=sum2) {
            
            // 큰 쪽 -> 작은 쪽으로 옮기기
            if(sum1 > sum2) {
                int n = q1.poll();
                sum1 -= n;
                sum2 += n;
                q2.add(n);
            }
            else {
                int n = q2.poll();
                sum2 -= n;
                sum1 += n;
                q1.add(n);
            }
            
            cnt++;
            
            if(cnt>= queue1.length*3)
                return -1;
        }
        
        return cnt;
    }
}

/*
+16
테스트 1 〉	통과 (0.12ms, 75.3MB)
테스트 2 〉	통과 (0.10ms, 73.2MB)
테스트 3 〉	통과 (0.12ms, 73MB)
테스트 4 〉	통과 (0.14ms, 74.1MB)
테스트 5 〉	통과 (0.22ms, 79.5MB)
테스트 6 〉	통과 (0.23ms, 72.9MB)
테스트 7 〉	통과 (0.36ms, 72.6MB)
테스트 8 〉	통과 (0.48ms, 74.4MB)
테스트 9 〉	통과 (0.68ms, 77.1MB)
테스트 10 〉	통과 (0.85ms, 76.2MB)
테스트 11 〉	통과 (23.04ms, 95.3MB)
테스트 12 〉	통과 (12.79ms, 91.7MB)
테스트 13 〉	통과 (15.43ms, 86.2MB)
테스트 14 〉	통과 (14.80ms, 82.1MB)
테스트 15 〉	통과 (16.78ms, 101MB)
테스트 16 〉	통과 (15.19ms, 106MB)
테스트 17 〉	통과 (15.73ms, 93.3MB)
테스트 18 〉	통과 (82.20ms, 151MB)
테스트 19 〉	통과 (69.25ms, 141MB)
테스트 20 〉	통과 (74.94ms, 146MB)
테스트 21 〉	통과 (74.83ms, 136MB)
테스트 22 〉	통과 (79.63ms, 155MB)
테스트 23 〉	통과 (81.71ms, 151MB)
테스트 24 〉	통과 (83.28ms, 135MB)
테스트 25 〉	통과 (0.27ms, 77.1MB)
테스트 26 〉	통과 (0.24ms, 75.4MB)
테스트 27 〉	통과 (0.22ms, 75.2MB)
테스트 28 〉	통과 (72.88ms, 107MB)
테스트 29 〉	통과 (2.18ms, 76.6MB)
테스트 30 〉	통과 (51.87ms, 119MB)
 */