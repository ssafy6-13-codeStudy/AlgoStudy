import java.util.*;
class Solution {
    public int solution(String s) {
        int len = s.length()/2;
        int min = Integer.MAX_VALUE;
        
        if(s.length() == 1){
            return 1;
        }
        
        for(int i = 1; i <= len; i++){
            Stack<String> st = new Stack<>();
            st.add(s.substring(0,i));
            
            int cnt = 1;
            int j = i;
            for(; j+i <= s.length(); j += i){
                String sub = s.substring(j,j+i);
                
                if(st.peek().equals(sub)){
                    cnt++;
                }else{
                    if(cnt > 1){
                        st.add(String.valueOf(cnt));
                        cnt = 1;
                    }
                    st.add(sub);
                }
            }
            if(cnt > 1){
                st.add(String.valueOf(cnt));
            }
            if(j - i < s.length()){
                st.add(s.substring(j,s.length()));
            }
            
            
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < st.size(); k++){
                sb.append(st.get(k));
            }
            
            min = Math.min(min, sb.length());
        }
        
        return min;
    }
}