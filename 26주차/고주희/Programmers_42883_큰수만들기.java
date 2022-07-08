import java.util.*;

class Solution {
    public String solution(String number, int k) {
        char[] c = number.toCharArray();
        int len = c.length;
        int[] map = new int[len];
        for(int i = 0; i < len; i++) {
            map[i] = c[i]-'0';
        }
        Stack<Integer> stack = new Stack();
        stack.push(map[0]);
        for(int i = 1; i < len; i++) {
            if(k > 0 && !stack.isEmpty()&& stack.peek() < map[i]) {
                while(!stack.isEmpty() && k > 0) {
                    if(stack.peek() < map[i]) {
                        stack.pop();
                        k--;
                    } else break;
                }
            } 
            stack.push(map[i]);
        }
        while(stack.size() > len -k) {
            stack.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : stack) {
            sb.aappend(i);
        }
        String answer = sb.toString();
        return answer;
    }
}
