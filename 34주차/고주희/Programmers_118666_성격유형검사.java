import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        int N = survey.length;
        for (int i = 0; i < N; i++) {
            char first = survey[i].charAt(0);
            char sec = survey[i].charAt(1);
            int choice = choices[i];
            if(choice > 4) {
                int score = choice - 4;
                map.put(sec, map.getOrDefault(sec, 0) + score);
            } else if(choice < 4) {
                int score = 4 - choice;
                map.put(first, map.getOrDefault(first, 0) + score);
            }
            
        }
        StringBuilder sb = new StringBuilder();
            
        if(map.getOrDefault('R', 0) >= map.getOrDefault('T', 0)) {
            sb.append('R');
        } else {
            sb.append('T');
        }
        if(map.getOrDefault('C', 0) >= map.getOrDefault('F', 0)) {
            sb.append('C');
        } else {
            sb.append('F');
        }
        if(map.getOrDefault('J', 0) >= map.getOrDefault('M', 0)) {
            sb.append('J');
        } else {
            sb.append('M');
        }
        if(map.getOrDefault('A', 0) >= map.getOrDefault('N', 0)) {
            sb.append('A');
        } else {
            sb.append('N');
        }
        return sb.toString();
    }
}

/*
+2점
테스트 1 〉	통과 (0.13ms, 72.9MB)
테스트 2 〉	통과 (0.12ms, 71.6MB)
테스트 3 〉	통과 (0.15ms, 78MB)
테스트 4 〉	통과 (0.16ms, 75MB)
테스트 5 〉	통과 (0.12ms, 70.1MB)
테스트 6 〉	통과 (0.14ms, 74.4MB)
테스트 7 〉	통과 (0.22ms, 76.8MB)
테스트 8 〉	통과 (0.16ms, 71.2MB)
테스트 9 〉	통과 (0.17ms, 74MB)
테스트 10 〉	통과 (0.27ms, 79.5MB)
테스트 11 〉	통과 (0.26ms, 79.5MB)
테스트 12 〉	통과 (0.45ms, 76.9MB)
테스트 13 〉	통과 (0.66ms, 82.9MB)
테스트 14 〉	통과 (0.63ms, 81.4MB)
테스트 15 〉	통과 (0.78ms, 75.4MB)
테스트 16 〉	통과 (0.56ms, 75.5MB)
테스트 17 〉	통과 (0.75ms, 74.5MB)
테스트 18 〉	통과 (0.94ms, 76MB)
테스트 19 〉	통과 (0.57ms, 82.6MB)
테스트 20 〉	통과 (0.86ms, 78.9MB)
*/
