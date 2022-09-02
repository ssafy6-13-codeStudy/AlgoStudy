
class Solution {
    static int max;
    static int[] max_arr;
    public int[] solution(int n, int[] info) {
        max = -1;
        max_arr = new int[11];
        comb(n, 0, new int[11], info);
        if(max <= 0) {
            max_arr = new int[1];
            max_arr[0] = -1;
        }
        return max_arr;
    }
    
    static public void comb(int cnt, int start, int[] lion_shooting, int[] info) {
        if(cnt==0) {
            int lion = 0;
            int apeach = 0;
            for(int i = 0; i < 11; i++) {
                if(info[i] >= lion_shooting[i] && info[i] != 0) {
                    apeach += 10 - i;
                } else if(lion_shooting[i] != 0) {
                    lion += 10 - i;
                }
            }
            // System.out.println(lion +" "+apeach);
            if (lion - apeach > max) {
                max = lion - apeach;
                for(int i = 0; i < 11; i++) {
                    max_arr[i] = lion_shooting[i];
                }
            } 
            else if (lion - apeach == max) {
                if(check_arr(lion_shooting)) {
                    for(int i = 0; i < 11; i++) {
                        max_arr[i] = lion_shooting[i];
                    }
                }
            }
            return;
        }
        
        for(int i = 10; i >= start; i--) {
            if(lion_shooting[i] > info[i]) continue;
            lion_shooting[i]++;
            comb(cnt - 1, i, lion_shooting, info);
            lion_shooting[i]--;
        }
    }
    
    static public boolean check_arr(int[] shooting) {
        int n = shooting.length;
        for(int i = n - 1; i >= 0; i--) {
            if(shooting[i] + max_arr[i] == 0 || shooting[i] == max_arr[i]) {
                continue;
            } else if(max_arr[i] == 0) {
                return true;
            } else if(shooting[i] == 0) {
                return false;
            } else if(max_arr[i] < shooting[i]) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}

/*
테스트 1 〉	통과 (0.04ms, 74.1MB)
테스트 2 〉	통과 (2.10ms, 81.5MB)
테스트 3 〉	통과 (2.01ms, 76.3MB)
테스트 4 〉	통과 (0.53ms, 77.4MB)
테스트 5 〉	통과 (2.80ms, 72.4MB)
테스트 6 〉	통과 (2.90ms, 88MB)
테스트 7 〉	통과 (0.61ms, 74.5MB)
테스트 8 〉	통과 (0.16ms, 74.3MB)
테스트 9 〉	통과 (0.55ms, 79.3MB)
테스트 10 〉	통과 (0.20ms, 74.9MB)
테스트 11 〉	통과 (0.49ms, 77.1MB)
테스트 12 〉	통과 (0.31ms, 76.7MB)
테스트 13 〉	통과 (0.94ms, 78.9MB)
테스트 14 〉	통과 (2.32ms, 78.7MB)
테스트 15 〉	통과 (2.29ms, 80.7MB)
테스트 16 〉	통과 (1.28ms, 83.3MB)
테스트 17 〉	통과 (0.78ms, 71.7MB)
테스트 18 〉	통과 (0.08ms, 66.8MB)
테스트 19 〉	통과 (0.04ms, 72.9MB)
테스트 20 〉	통과 (3.44ms, 77.1MB)
테스트 21 〉	통과 (1.95ms, 78.6MB)
테스트 22 〉	통과 (2.77ms, 89.2MB)
테스트 23 〉	통과 (0.19ms, 74.8MB)
테스트 24 〉	통과 (1.72ms, 78.2MB)
테스트 25 〉	통과 (3.16ms, 71.3MB)
*/
