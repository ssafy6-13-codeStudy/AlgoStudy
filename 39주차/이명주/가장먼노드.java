import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<Integer>[] list = new ArrayList[n+1];
        int[] v = new int[n+1];
        
        for(int i=0; i<=n; i++)
            list[i] = new ArrayList<>();
        
        for(int[] arr : edge) {
            list[arr[0]].add(arr[1]);
            list[arr[1]].add(arr[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        int depth = 1;
        queue.add(1);
        v[1] = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i=0; i<size; i++) {
                int now = queue.poll();
                
                for(int next : list[now]) {
                    if(v[next]!=0) continue;
                    v[next] = depth;
                    queue.add(next);
                }
            }
            
            depth++;
        }
        
        for(int i=1; i<=n; i++) {
            if(v[i]==depth-2)
                answer++;
        }
        
        return answer;
    }
}

/*
+5
정확성  테스트
테스트 1 〉	통과 (0.22ms, 64.8MB)
테스트 2 〉	통과 (0.23ms, 87.1MB)
테스트 3 〉	통과 (0.45ms, 72.4MB)
테스트 4 〉	통과 (1.03ms, 77.1MB)
테스트 5 〉	통과 (3.08ms, 94.8MB)
테스트 6 〉	통과 (4.93ms, 82.9MB)
테스트 7 〉	통과 (21.17ms, 99.6MB)
테스트 8 〉	통과 (40.74ms, 113MB)
테스트 9 〉	통과 (47.97ms, 99.2MB)
 */