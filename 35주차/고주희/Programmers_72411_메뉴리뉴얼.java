import java.util.*;

class Solution {
    static int count;
    static int[] arr;
    static Map<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        
        count = 0;
        arr = new int[11];
        for(String order : orders) {
            int len = order.length();
            char[] tmp = order.toCharArray();
            Arrays.sort(tmp);
            subset(0, tmp, new boolean[len], course);
        }
        
        
        List<String> list = new ArrayList<>();
        int t = 0;
        for(String str : map.keySet()) {
            if(arr[str.length()] >= 2 && map.get(str) == arr[str.length()]) {
                list.add(str);
            }
        }
        String[] answer = new String[list.size()];
        int o = 0;
        for(String str : list) {
            answer[o++] = str;
        }
        Arrays.sort(answer);
        return answer;
    }
    
    static public void subset(int cnt, char[] order, boolean[] v, int[] course) {
        int len = order.length;
        if(cnt == len) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++) {
                if(v[i]) sb.append(order[i]);
            }
            if(sb.length() < 2) return;
            int clen = course.length;
            for (int i = 0; i < clen; i++) {
                if(sb.length() == course[i]) {
                    String str = sb.toString();
                    int tmp = 1;
                    if(map.containsKey(str)) tmp = map.get(str) + 1;
                    map.put(str, tmp);
                    if(arr[course[i]] < tmp) arr[course[i]] = tmp;
                    return;
                }
            }
            return;
        }
        
        v[cnt] = true;
        subset(cnt+1, order, v, course);
        v[cnt] = false;
        subset(cnt+1, order, v, course);
    }
}

/*
+3
테스트 1 〉	통과 (1.23ms, 77.2MB)
테스트 2 〉	통과 (0.70ms, 68MB)
테스트 3 〉	통과 (1.38ms, 74MB)
테스트 4 〉	통과 (1.20ms, 72.9MB)
테스트 5 〉	통과 (1.33ms, 74.3MB)
테스트 6 〉	통과 (1.58ms, 74.6MB)
테스트 7 〉	통과 (1.86ms, 77.7MB)
테스트 8 〉	통과 (7.59ms, 85.1MB)
테스트 9 〉	통과 (6.09ms, 83.5MB)
테스트 10 〉	통과 (6.76ms, 82.8MB)
테스트 11 〉	통과 (4.71ms, 83.6MB)
테스트 12 〉	통과 (4.52ms, 85.3MB)
테스트 13 〉	통과 (6.01ms, 83.2MB)
테스트 14 〉	통과 (3.72ms, 79.3MB)
테스트 15 〉	통과 (4.44ms, 85.5MB)
테스트 16 〉	통과 (3.60ms, 80.3MB)
테스트 17 〉	통과 (5.49ms, 91.2MB)
테스트 18 〉	통과 (4.57ms, 78.2MB)
테스트 19 〉	통과 (7.58ms, 80.2MB)
테스트 20 〉	통과 (4.75ms, 83.6MB)
*/
