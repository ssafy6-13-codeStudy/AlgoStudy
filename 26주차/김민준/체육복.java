class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] list = new int[n+2];
        int answer = 0;
        
        for(int i = 0; i < reserve.length; i++){
            list[reserve[i]]++;
        }
        
        for(int i = 0; i < lost.length; i++){
            list[lost[i]]--;
        }
        
        for(int i = 1; i <= n; i++){
            if(list[i] == -1){
                if(list[i-1] == 1){
                    list[i-1] = 0;
                    list[i] = 0;
                }else if(list[i+1] == 1){
                    list[i+1] = 0;
                    list[i] = 0;
                }
            }
            if(list[i] >= 0){
                answer++;
            }
        }
        
        return answer;
    }
}