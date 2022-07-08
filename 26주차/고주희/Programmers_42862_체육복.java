class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] cloth = new int[n];
        for(int i : reserve) {
            cloth[i-1] += 1;
        }
        
        for(int i : lost) {
            cloth[i-1] -= 1;
        }
        for(int i=0; i < n; i++) {
            if(cloth[i]==-1){
                if(i-1>=0 && cloth[i-1]==1){
                    cloth[i] = 0;
                    cloth[i-1] = 0;
                } else if(i+1 < n && cloth[i+1]==1) {
                    cloth[i] = 0;
                    cloth[i+1] = 0;
                }
            }
            if(cloth[i] >= 0) answer++;
        }
        return answer;
    }
}
