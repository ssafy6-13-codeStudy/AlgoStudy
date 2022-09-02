import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        int len = id_list.length;
        HashSet<String>[] set = new HashSet[len];
        Map<String, Integer> users = new HashMap<>();
        for(int i = 0; i < len; i++) {
            users.put(id_list[i], i);
            set[i] = new HashSet<>();
        }
        for(String rep : report) {
            st = new StringTokenizer(rep);
            String reporter = st.nextToken();
            String bad_user = st.nextToken();
            set[users.get(bad_user)].add(reporter);
        }
        
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            if(set[i].size() >= k) {
                for(String name : set[i]) {
                    answer[users.get(name)]++;
                }
            }
        }
        return answer;
    }
}
