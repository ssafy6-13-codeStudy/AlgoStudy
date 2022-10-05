import java.util.*;

/*
1트)
취약 위치 간 거리를 구한 후 긴 거리부터 끊어주었다.
반례 : 
n 37, 취약 위치 [0,5,12,17], 걸을 수 있는 거리 [3,12]
0과 5 사이 거리를 끊어주면 0에 한 명, 5~17에 한 명이 갈 수 있는데 긴 것부터 자르다보니 0~5, 12~17이 되어버렸다. 그래서 보수할 수 없다고 나온다.
*/
class Solution {
    static boolean flag = false;
    static int min = -1;
    static int len;
    public int solution(int n, int[] weak, int[] dist) {
        len = n;
        int M = weak.length;
        int[] diff = new int[M];
        Arrays.sort(dist);
        for (int i = 0; i < M - 1; i++) {
            diff[i] = weak[i+1] - weak[i];
        }
        diff[M-1] = n - weak[M-1] + weak[0];
        for (int i = 1; i <= M; i++) {
            comb(i, 0, new int[i], weak, dist);
            if(flag) break;
        }
        return min;
    }
    
    static public void comb(int cnt, int start, int[] num, int[] weak, int[] dist) {
        
        int N = weak.length;
        int M = num.length;
        if(cnt == 0){
            int[] contous = new int[M];
            // int o = 0;
            for (int i = 0; i < M - 1; i++) {
                // {0, 2}로 되어 있다고 해보면
                int next = num[i+1] - 1;
                int diff = weak[next] - weak[num[i]];
                contous[i] = diff;
            }
            int next = num[0] - 1;
            if(next == -1) next = N - 1;
            if(weak[num[M-1]] > weak[next]) {
                contous[M-1] = len - weak[num[M-1]] + weak[num[0]-1];
            } else {
                contous[M-1] = weak[next] - weak[num[M-1]];
            }
            Arrays.sort(contous);
            if(check(contous, dist)) {
                flag = true;
                min = M;
            }
            return;
        }
        
        for(int i = start; i < N; i++) {
            num[M-cnt] = i;
            comb(cnt - 1, i + 1, num, weak, dist);
        }
    }
    
    static public boolean check(int[] contous, int[] dist) {
        int j = 0;
        // 친구들이 움직일 수 있는 거리
        int N = dist.length;
        int size = contous.length;
        
        // 친구들도 정렬되어 있기 때문에 작은 애부터 가져감
        for(int i = 0; i < N; i++) {
            if(dist[i] >= contous[j]) {
                j++;
                if(j == size) return true;
            }
        }
        return false;
    }
}
/*
+8
테스트 1 〉	통과 (0.41ms, 77.3MB)
테스트 2 〉	통과 (0.42ms, 72.8MB)
테스트 3 〉	통과 (3.85ms, 82.8MB)
테스트 4 〉	통과 (1.77ms, 74.4MB)
테스트 5 〉	통과 (2.03ms, 68.4MB)
테스트 6 〉	통과 (3.91ms, 71.5MB)
테스트 7 〉	통과 (0.34ms, 66.2MB)
테스트 8 〉	통과 (0.53ms, 79.6MB)
테스트 9 〉	통과 (0.74ms, 82.1MB)
테스트 10 〉	통과 (3.96ms, 83.2MB)
테스트 11 〉	통과 (5.60ms, 78.4MB)
테스트 12 〉	통과 (5.34ms, 66.7MB)
테스트 13 〉	통과 (13.64ms, 76.4MB)
테스트 14 〉	통과 (5.98ms, 71.7MB)
테스트 15 〉	통과 (18.97ms, 69.9MB)
테스트 16 〉	통과 (1.69ms, 73.4MB)
테스트 17 〉	통과 (3.44ms, 78.7MB)
테스트 18 〉	통과 (1.32ms, 78.5MB)
테스트 19 〉	통과 (0.58ms, 75.3MB)
테스트 20 〉	통과 (1.08ms, 80.1MB)
테스트 21 〉	통과 (0.60ms, 83.6MB)
테스트 22 〉	통과 (0.93ms, 74.5MB)
테스트 23 〉	통과 (1.15ms, 73.2MB)
테스트 24 〉	통과 (1.41ms, 75.6MB)
테스트 25 〉	통과 (0.61ms, 77.9MB)
*/
