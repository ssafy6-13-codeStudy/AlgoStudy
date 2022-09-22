import java.util.*;

class Solution {
    
    public String solution(String id) {
        
        // 1단계. 대문자 -> 소문자
        for(int i=0; i<28; i++) {
            char c = (char)(i+65);
            id = id.replace(c,(char)(i+97));
        }
        
        // 2단계
        id = id.replaceAll("[^a-z0-9-_.]","");
        // 3단계
        id = id.replaceAll("[.]{2,}", ".");
        // 4단계
        id = id.replaceAll("^[.]{1}", "");
        // 5단계
        if(id.length()==0)
            id = "aaa";
        
        // 6단계
        if(id.length()>=16) id = id.substring(0,15);
        
        if(id.charAt(id.length()-1)=='.')
            id = id.substring(0,id.length()-1);
        
        // 7단계
        while (id.length()<3)
            id += id.charAt(id.length()-1);
        
        return id;
    }
}

/*
+4
테스트 1 〉	통과 (0.21ms, 73.6MB)
테스트 2 〉	통과 (0.23ms, 71.8MB)
테스트 3 〉	통과 (12.80ms, 79.6MB)
테스트 4 〉	통과 (0.29ms, 75.8MB)
테스트 5 〉	통과 (0.31ms, 74.3MB)
테스트 6 〉	통과 (0.25ms, 72.4MB)
테스트 7 〉	통과 (0.24ms, 71.5MB)
테스트 8 〉	통과 (0.33ms, 78.3MB)
테스트 9 〉	통과 (11.23ms, 72.9MB)
테스트 10 〉	통과 (0.22ms, 71MB)
테스트 11 〉	통과 (0.28ms, 80.5MB)
테스트 12 〉	통과 (0.30ms, 74.8MB)
테스트 13 〉	통과 (10.72ms, 74.1MB)
테스트 14 〉	통과 (0.33ms, 79.1MB)
테스트 15 〉	통과 (0.41ms, 81.6MB)
테스트 16 〉	통과 (0.43ms, 76.3MB)
테스트 17 〉	통과 (1.53ms, 80.5MB)
테스트 18 〉	통과 (1.77ms, 84.6MB)
테스트 19 〉	통과 (1.77ms, 70.3MB)
테스트 20 〉	통과 (2.96ms, 79.2MB)
테스트 21 〉	통과 (2.98ms, 74.1MB)
테스트 22 〉	통과 (5.70ms, 81.5MB)
테스트 23 〉	통과 (1.19ms, 77.2MB)
테스트 24 〉	통과 (1.78ms, 82.3MB)
테스트 25 〉	통과 (7.60ms, 77.5MB)
테스트 26 〉	통과 (1.46ms, 76.3MB)
 */