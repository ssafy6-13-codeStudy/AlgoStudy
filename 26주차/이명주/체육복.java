import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++) {
                if(lost[i]==reserve[j]){
                    answer++;
                    reserve[j] = -1;
                    lost[i] = -1;
                    break;
                }
            }
        }
        
        for(int num: lost) {
            
            for(int i =0; i<reserve.length; i++) {
                if(reserve[i]==num-1 || reserve[i]==num+1){
                    answer++;
                    reserve[i] = -1;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}