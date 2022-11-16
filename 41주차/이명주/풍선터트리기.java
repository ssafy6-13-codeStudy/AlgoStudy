import java.util.*;

// 인접한 두 풍선 고르고 하나 터트리기
// 밀착
// 번호가 더 작은 풍선을 터트리기 1회가능
// 최후까지 남기는 것이 가능한 풍선의 개수
class Solution {
    public int solution(int[] a) {
        int answer = 2;
        
        int l = a[0];
        int[] r = new int[a.length];
        
        r[a.length-1] = a[a.length-1];
        
        for(int i=a.length-2; i>0; i--)
            r[i] = Math.min(r[i+1],a[i]);
    
        for(int i=1; i<a.length-1; i++) {
            if(!(l < a[i] && r[i] < a[i])) answer++;
            l = Math.min(l,a[i]);
        }
        
        return answer;
    }
}

/*
+8
정확성  테스트
테스트 1 〉	통과 (0.03ms, 77.7MB)
테스트 2 〉	통과 (0.04ms, 79.4MB)
테스트 3 〉	통과 (0.17ms, 70.8MB)
테스트 4 〉	통과 (3.82ms, 94.3MB)
테스트 5 〉	통과 (12.08ms, 130MB)
테스트 6 〉	통과 (33.21ms, 136MB)
테스트 7 〉	통과 (20.88ms, 141MB)
테스트 8 〉	통과 (24.85ms, 142MB)
테스트 9 〉	통과 (23.88ms, 130MB)
테스트 10 〉	통과 (20.61ms, 126MB)
테스트 11 〉	통과 (23.28ms, 124MB)
테스트 12 〉	통과 (22.50ms, 145MB)
테스트 13 〉	통과 (27.54ms, 136MB)
테스트 14 〉	통과 (19.55ms, 144MB)
테스트 15 〉	통과 (14.56ms, 137MB)

*/