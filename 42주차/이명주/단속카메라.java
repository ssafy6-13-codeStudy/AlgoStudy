import java.util.*;

/*
모든 차량이 한 번은 단속 카메라를 만나도록
최소 설치해야 하는 카메라 수 리턴

차 <= 10000
-30000 <= 고속도로 <= 30000

1. 진출 빠른 순 - 진입 빠른 순으로 정렬하고
앞에서부터 가장 뒤에다가 설치하면 되지 않나?
*/
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes,(o1,o2)->o1[1]-o2[1]);
        
        int now = -30001;
        
        for(int i=0; i<routes.length; i++) {
            // 시작 지점이 이전에 세운 카메라보다 크다면 제일 끝에 또 설치하기
            if(now < routes[i][0]) {
                answer++;
                now = routes[i][1];
            }
        }
        
        return answer;
    }
}
/*
+5
정확성  테스트
테스트 1 〉	통과 (0.50ms, 76.7MB)
테스트 2 〉	통과 (0.70ms, 75.8MB)
테스트 3 〉	통과 (0.58ms, 76.4MB)
테스트 4 〉	통과 (0.56ms, 76.4MB)
테스트 5 〉	통과 (0.48ms, 75.2MB)
효율성  테스트
테스트 1 〉	통과 (4.69ms, 52.7MB)
테스트 2 〉	통과 (3.58ms, 53.2MB)
테스트 3 〉	통과 (8.85ms, 56MB)
테스트 4 〉	통과 (0.91ms, 52MB)
테스트 5 〉	통과 (9.77ms, 58.7MB)
 */