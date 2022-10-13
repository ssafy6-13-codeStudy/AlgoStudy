import java.util.*;

// 소요시간이 짧은 작업순으로 처리하기

// 1. 큐를 두개를 만들어서 현재 시간에 요청 처리된 큐를 따로 저장하기
// 2. 일일이 비교하면서 최소 작업시간인 작업 찾기
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> queue = new PriorityQueue<>();
        PriorityQueue<Job> list = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.work - o2.work;
            }
        });

        for(int[] arr : jobs)
            queue.add(new Job(arr[0],arr[1]));
        
        int time = 0;
        while(true) {
            
            if(list.isEmpty()) {
                if(queue.isEmpty()) break;
                else list.add(queue.poll());
            }
            
            Job now = list.poll();
            
            // 현재 작업이 시작하기 까지 대기한 시간을 더해준다.
            if(time > now.start)
                answer += time - now.start;
            else time = now.start;
            
            // 작업 시간을 더해준다.
            time += now.work;
            answer += now.work;
            
            while(!queue.isEmpty() && queue.peek().start <= time)
                list.add(queue.poll());
        }
        
        return answer/jobs.length;
    }
    
    public class Job implements Comparable<Job> {
        int start;
        int work;
        
        public Job(int start, int work) {
            this.start = start;
            this.work = work;
        }
        
        // 요청 시간 오름차순 -> 작업 시간 오름차순
        @Override
        public int compareTo(Job o) {
            if(this.start==o.start) 
                return this.work-o.work;
            else return this.start-o.start;
        }
    }
}

/*
+10
정확성  테스트
테스트 1 〉	통과 (4.26ms, 72.5MB)
테스트 2 〉	통과 (2.67ms, 72.5MB)
테스트 3 〉	통과 (3.03ms, 71.2MB)
테스트 4 〉	통과 (3.03ms, 77.3MB)
테스트 5 〉	통과 (3.38ms, 68.1MB)
테스트 6 〉	통과 (0.88ms, 79MB)
테스트 7 〉	통과 (3.11ms, 77.8MB)
테스트 8 〉	통과 (2.13ms, 72MB)
테스트 9 〉	통과 (1.23ms, 74.2MB)
테스트 10 〉	통과 (2.74ms, 77.9MB)
테스트 11 〉	통과 (3.63ms, 74.7MB)
테스트 12 〉	통과 (0.71ms, 74.8MB)
테스트 13 〉	통과 (0.75ms, 77.7MB)
테스트 14 〉	통과 (0.65ms, 75MB)
테스트 15 〉	통과 (0.71ms, 72.9MB)
테스트 16 〉	통과 (0.72ms, 77.8MB)
테스트 17 〉	통과 (1.03ms, 72.9MB)
테스트 18 〉	통과 (0.75ms, 72.1MB)
테스트 19 〉	통과 (0.65ms, 76.1MB)
테스트 20 〉	통과 (0.86ms, 71.9MB)
 */