import java.util.*;

class Solution {
    public String solution(String new_id) {
        char[] c = new_id.toLowerCase().toCharArray();
        int len = c.length;
        List<Character> list = new ArrayList<>();
        boolean comma = false;
        for(int i = 0; i < len; i++) {
            char tmp = c[i];
            if((tmp >= 'a' && tmp <= 'z') || (tmp >= '0' && tmp <= '9') || tmp == '_' || tmp == '-') {
                list.add(tmp);
                comma = false;
            }
            if(tmp == '.') {
                if(!comma) {
                    comma = true;
                    list.add(tmp);
                }
            }
        }
        if(list.size() == 0) list.add('a');
        if(list.get(0) == '.') list.remove(0);
        if(list.size() == 0) list.add('a');
        if(list.get(list.size() - 1) == '.') list.remove(list.size() - 1);
        if(list.size() == 0) list.add('a');

        if(list.size() >= 16) {
            for(int i = list.size() - 1; i >= 15; i--) {
                list.remove(i);
            }
            if(list.get(list.size() - 1)== '.') list.remove(list.size() - 1);
        } else if(list.size() <= 2) {
            char last = list.get(list.size() - 1);
            while(list.size() <= 2) {
                list.add(last);
            }
        } 
        StringBuilder sb = new StringBuilder();
        for(char l : list) {
            sb.append(l);
        }
        return sb.toString();
    }
}

/*
+1
테스트 1 〉	통과 (1.40ms, 90.8MB)
테스트 2 〉	통과 (0.11ms, 73.9MB)
테스트 3 〉	통과 (0.15ms, 72.5MB)
테스트 4 〉	통과 (0.21ms, 71.9MB)
테스트 5 〉	통과 (0.22ms, 78.9MB)
테스트 6 〉	통과 (0.16ms, 73.5MB)
테스트 7 〉	통과 (0.18ms, 76.8MB)
테스트 8 〉	통과 (0.15ms, 75.1MB)
테스트 9 〉	통과 (0.14ms, 78.7MB)
테스트 10 〉	통과 (0.17ms, 73.6MB)
테스트 11 〉	통과 (0.16ms, 67.7MB)
테스트 12 〉	통과 (0.15ms, 65.8MB)
테스트 13 〉	통과 (0.16ms, 73.7MB)
테스트 14 〉	통과 (0.19ms, 79.1MB)
테스트 15 〉	통과 (0.18ms, 73.4MB)
테스트 16 〉	통과 (0.24ms, 77.4MB)
테스트 17 〉	통과 (0.39ms, 71.8MB)
테스트 18 〉	통과 (1.48ms, 73MB)
테스트 19 〉	통과 (0.77ms, 77.8MB)
테스트 20 〉	통과 (0.32ms, 74.1MB)
테스트 21 〉	통과 (0.38ms, 71.6MB)
테스트 22 〉	통과 (0.24ms, 77.5MB)
테스트 23 〉	통과 (0.33ms, 73.3MB)
테스트 24 〉	통과 (0.97ms, 75.4MB)
테스트 25 〉	통과 (0.37ms, 80.6MB)
테스트 26 〉	통과 (0.42ms, 72.2MB)
*/
