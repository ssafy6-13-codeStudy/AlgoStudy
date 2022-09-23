import java.util.*;

class Solution {
    public String solution(String p) {
        
        if(p == "") return p;
        // 1. 올바른 문자열인지 검사하기
        int right = 0;
        int left = 0;
        int len = p.length();
        if(isRight(p)) return p;
        // 2. 아니라면 균형잡은 거 두 개로 나누기
        String[] str = divide(p);
        System.out.println(Arrays.toString(str));
        if(isRight(str[0])) {
            return str[0] + solution(str[1]);
        } else {
            return "(" + solution(str[1]) + ")" + reverse(str[0]);
        }
        // if(str[1].equals("")) {
        //     // System.out.println(str[1] + " hi");
        //     return "(" + reverse(p) + ")";
        // }
        // // 3. 변환!한 거 붙이기...?
        // else return solution(str[0]) + solution(str[1]);
        // else return "";
    }
    
    static public String reverse(String p) {
        StringBuilder sb = new StringBuilder();
        char[] cs = p.toCharArray();
        int len = cs.length;
        for (int i = 1; i < len - 1; i++) {
            char c = p.charAt(i);
            if(c == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
    
    static public String[] divide(String p) {
        int len = p.length();
        int right = 0;
        int left = 0;
        for (int i = 0; i < len; i++) {
            if(p.charAt(i) == '(') {
                right++;
            } else {
                left++;
            }
            if(right == left) {
                return new String[] {p.substring(0, i+1), p.substring(i+1, len)};
            }
        }
        return new String[]{p, ""};
    }
    
    
    static public boolean isRight(String p) {
        int len = p.length();
        int right = 0;
        int left = 0;
        for (int i = 0; i < len; i++) {
            if(p.charAt(i) == '(') {
                right++;
            } else {
                left++;
                if(right < left) return false;
            }
        }
        if(right == left) return true;
        else return false;
    }
    
}
