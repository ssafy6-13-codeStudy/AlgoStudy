import java.util.*;

class Solution {
    static boolean[] v;
    static String[] answer;
    static ArrayList<String> list;
    
    public String[] solution(String[][] tickets) {
        
        v = new boolean[tickets.length];
        list = new ArrayList<>();
        
        dfs("ICN","ICN",0, tickets);
        
        Collections.sort(list);
        
        answer = list.get(0).split(" ");
        return answer;
    }
    
    public static void dfs(String now, String route, int cnt, String[][] tickets) {
        if(cnt == tickets.length) {
            list.add(route);
            return;
        }
        
        for(int i=0; i<tickets.length; i++) {
            if(now.equals(tickets[i][0]) && !v[i]) {
                v[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1], cnt+1, tickets);
                v[i] = false;
            }
        }
        
    }
}

/*
+9점
정확성  테스트
테스트 1 〉	통과 (89.59ms, 108MB)
테스트 2 〉	통과 (9.41ms, 87MB)
테스트 3 〉	통과 (9.96ms, 83.3MB)
테스트 4 〉	통과 (8.53ms, 68.2MB)
 */