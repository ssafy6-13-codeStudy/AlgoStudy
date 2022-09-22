import java.util.*;

class Solution {
    
    List<String> answerList = new ArrayList<>();
    Map<String, Integer> hashMap = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        
         // 주문 알파벳순 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // 코스 갯수별로 조합
        for (int n : course) {
            for (String order : orders)
                comb("", order, n);

            // 가장 많은 조합
            if (!hashMap.isEmpty()) {
                List<Integer> countList = new ArrayList<>(hashMap.values());
                int max = Collections.max(countList);

                if (max > 1)
                    for (String key : hashMap.keySet())
                        if (hashMap.get(key) == max)
                            answerList.add(key);
                hashMap.clear();
            }
        }

        // 오름차순정렬
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++)
            answer[i] = answerList.get(i);

        return answer;
    }
    
    public void comb(String order, String orders, int count) {
        
        if (count == 0) {
            hashMap.put(order, hashMap.getOrDefault(order, 0) + 1);
            return;
        }

        for (int i = 0; i < orders.length(); i++)
            comb(order + orders.charAt(i), orders.substring(i + 1), count - 1);
    }
}

/*
테스트 1 〉	통과 (16.35ms, 81.1MB)
테스트 2 〉	통과 (18.88ms, 84.7MB)
테스트 3 〉	통과 (10.96ms, 83.6MB)
테스트 4 〉	통과 (11.10ms, 75.7MB)
테스트 5 〉	통과 (14.35ms, 77.6MB)
테스트 6 〉	통과 (12.17ms, 77.5MB)
테스트 7 〉	통과 (18.04ms, 80MB)
테스트 8 〉	통과 (23.11ms, 100MB)
테스트 9 〉	통과 (26.90ms, 88.1MB)
테스트 10 〉	통과 (21.03ms, 91.8MB)
테스트 11 〉	통과 (20.68ms, 80.3MB)
테스트 12 〉	통과 (18.76ms, 78.8MB)
테스트 13 〉	통과 (20.04ms, 94.9MB)
테스트 14 〉	통과 (22.57ms, 92.8MB)
테스트 15 〉	통과 (19.31ms, 81.1MB)
테스트 16 〉	통과 (16.74ms, 80.4MB)
테스트 17 〉	통과 (20.20ms, 84.4MB)
테스트 18 〉	통과 (23.46ms, 80.3MB)
테스트 19 〉	통과 (17.23ms, 80.6MB)
테스트 20 〉	통과 (30.88ms, 88.9MB)
 */