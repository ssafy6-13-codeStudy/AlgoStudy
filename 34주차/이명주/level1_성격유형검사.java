import java.util.*;

class Solution {
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        // 타입별 점수를 기록할 해시맵
        HashMap<Character, Integer> map = new HashMap<>();
        
        // 0점으로 초기화
        String l = "RTCFJMAN";
        for(char c : l.toCharArray()) {
            map.put(c,0);
        }
        
        // survey에 맞게 점수 추가
        for(int i=0; i<choices.length; i++) {
            int score = choices[i];
            int rate = 0;
            char c;
            switch (score) {
                case 1: rate = 3;
                    break;
                case 2: rate = 2;
                    break;
                case 3: rate = 1;
                    break;
                case 5: rate = 1;
                    break;
                case 6: rate = 2 ;
                    break;
                case 7: rate = 3;
                    break;
            }
            
            if(score > 4)
                c = survey[i].charAt(1);
            else c = survey[i].charAt(0);
            
            map.put(c,map.get(c) + rate);
        }
        
        // 한쌍씩 점수 비교하며 더 많은 점수에 해당하는 알파벳 선택
        for(int i=0; i<8; i+=2) {
            int a = map.get(l.charAt(i));
            int b = map.get(l.charAt(i+1));
            
            if(a>=b)
                answer += l.charAt(i);
            else 
                answer += l.charAt(i+1);
        }
        
        return answer;
    }
}

/*
테스트 1 〉	통과 (11.08ms, 78.5MB)
테스트 2 〉	통과 (13.93ms, 83.1MB)
테스트 3 〉	통과 (14.19ms, 74.5MB)
테스트 4 〉	통과 (12.01ms, 91.7MB)
테스트 5 〉	통과 (10.41ms, 74.3MB)
테스트 6 〉	통과 (13.15ms, 73.8MB)
테스트 7 〉	통과 (22.23ms, 79.8MB)
테스트 8 〉	통과 (11.94ms, 74MB)
테스트 9 〉	통과 (13.72ms, 80.4MB)
테스트 10 〉	통과 (10.30ms, 76.1MB)
테스트 11 〉	통과 (9.56ms, 66.7MB)
테스트 12 〉	통과 (10.86ms, 84.4MB)
테스트 13 〉	통과 (13.87ms, 86.2MB)
테스트 14 〉	통과 (11.49ms, 74.9MB)
테스트 15 〉	통과 (11.45ms, 86.8MB)
테스트 16 〉	통과 (13.97ms, 69.5MB)
테스트 17 〉	통과 (14.06ms, 74.8MB)
테스트 18 〉	통과 (13.09ms, 74.6MB)
테스트 19 〉	통과 (12.64ms, 74.4MB)
테스트 20 〉	통과 (15.51ms, 82.4MB)
 */