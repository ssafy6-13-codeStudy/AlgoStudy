import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        long sum = 0l;
        long q1 = 0l;
        long q2 = 0l;
        for (int i : queue1) {
            que1.offer(i);
            sum += i;
            q1 += i;
        }
        for (int i : queue2) {
            que2.offer(i);
            sum += i;
            q2 += i;
        }
        if(sum % 2 != 0) {
            return -1;
        }
        sum = sum/2;
        int count = 0;
        int len = queue1.length;
        boolean flag = false;
        while(count < len * 3) {
            if(q1 == q2) {
                flag = true;
                break;
            } else if(q1 > q2) {
                int tmp = que1.poll();
                q1 -= tmp;
                q2 += tmp;
                que2.offer(tmp);
            } else {
                int tmp = que2.poll();
                q1 += tmp;
                q2 -= tmp;
                que1.offer(tmp);
            }
            count++;
        }
        return flag ? count : -1;
    }
}

/*
+8
테스트 1 〉	통과 (0.16ms, 72.4MB)
테스트 2 〉	통과 (0.10ms, 75MB)
테스트 3 〉	통과 (0.18ms, 64.5MB)
테스트 4 〉	통과 (0.19ms, 74.2MB)
테스트 5 〉	통과 (0.29ms, 73.7MB)
테스트 6 〉	통과 (0.33ms, 65.1MB)
테스트 7 〉	통과 (0.34ms, 70.9MB)
테스트 8 〉	통과 (0.80ms, 81.6MB)
테스트 9 〉	통과 (1.11ms, 70.6MB)
테스트 10 〉	통과 (1.49ms, 78MB)
테스트 11 〉	통과 (24.77ms, 98.2MB)
테스트 12 〉	통과 (11.22ms, 81.4MB)
테스트 13 〉	통과 (29.17ms, 92.4MB)
테스트 14 〉	통과 (12.17ms, 77MB)
테스트 15 〉	통과 (16.06ms, 92.2MB)
테스트 16 〉	통과 (20.25ms, 95.1MB)
테스트 17 〉	통과 (17.26ms, 107MB)
테스트 18 〉	통과 (80.47ms, 134MB)
테스트 19 〉	통과 (85.69ms, 145MB)
테스트 20 〉	통과 (76.83ms, 145MB)
테스트 21 〉	통과 (78.64ms, 138MB)
테스트 22 〉	통과 (71.57ms, 145MB)
테스트 23 〉	통과 (95.81ms, 135MB)
테스트 24 〉	통과 (92.50ms, 137MB)
테스트 25 〉	통과 (0.41ms, 75.5MB)
테스트 26 〉	통과 (0.25ms, 90.9MB)
테스트 27 〉	통과 (0.38ms, 72.4MB)
테스트 28 〉	통과 (66.56ms, 110MB)
테스트 29 〉	통과 (3.46ms, 75.7MB)
테스트 30 〉	통과 (54.83ms, 108MB)
*/
