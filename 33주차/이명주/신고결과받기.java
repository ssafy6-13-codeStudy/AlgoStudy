import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        // 유저별 받을 메일의 수
        int[] answer = new int[id_list.length];
        // 유저별 신고받은 수
        int[] reportCnt = new int[id_list.length];
        // map[i][j] - i가 j에게 신고 되었는지 체크
        boolean[][] map = new boolean[id_list.length][id_list.length];
        // 아이디 순서 저장 (id_list 순으로 출력해야 하므로)
        HashMap<String, Integer> id = new HashMap<>();

        for(int i=0; i<id_list.length; i++) {
            id.put(id_list[i], i);
        }

        for(int i=0; i<report.length; i++) {
            int a = id.get(report[i].split(" ")[0]); // 신고한 유저
            int b = id.get(report[i].split(" ")[1]); // 신고된 유저

            // 중복 제거
            if(map[b][a]) continue;

            map[b][a] = true;
            reportCnt[b]++;
        }

        // 신고 수가 k 이상인 유저들만 정지시킨다.
        for(int i=0; i<id_list.length; i++) {
            if(reportCnt[i] >= k) {
                // i번 유저를 신고한 유저들의 메일cnt++
                for(int j =0; j<id_list.length; j++) {
                    if(map[i][j])
                        answer[j]++;
                }
            }
        }

        return answer;
    }
}
