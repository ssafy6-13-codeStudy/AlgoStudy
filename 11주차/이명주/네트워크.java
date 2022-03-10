import java.util.*;
import java.io.*;

class Solution {
  public int solution(int n, int[][] computers) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean [] network = new boolean[n];
        
        for(int i = 0; i<n; i++) {
                if(network[i]) continue;
                q.add(i);
                network[i] = true;
                
                while(!q.isEmpty()){
                    int num = q.poll();                    
                    
                    for(int j = 0; j<n; j++){
                        
                        if(!network[j]&&computers[num][j]==1){
                            
                        network[num]=true;
                        q.add(num);
                            
                        }
                    }
                }
                
              answer++;
        }
        
        return answer;
    }
}