import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        int len = k;
        
        for(int i = 0; i < number.length(); i++){
            char temp = number.charAt(i);
            
            while(!stack.isEmpty() && stack.peek() < temp && len > 0){
                stack.pop();
                len--;
            }
            
            stack.push(temp);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < number.length()-k; i++){
            sb.append(stack.get(i));
        }
        
        return sb.toString();
    }
}