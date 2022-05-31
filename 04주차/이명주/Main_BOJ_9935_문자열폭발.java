import java.io.*;
import java.util.*;

/*
    문자열 폭발
    골드 4
    시간 : 516ms
    메모리 : 88192kb
*/

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // s : 원본 문자열
        // bomb : 폭발 문자열
        String s = br.readLine();
        String bomb = br.readLine();

        // 원본 문자열을 문자별로 넣어줄 스택
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            stack.push(s.charAt(i));

            //스택 크기가 폭발 문자열 보다 커질 경우
            if(stack.size() >= bomb.length()){

                boolean hasBomb = true;
                //스택의 끝에서부터 폭발 문자열 크기만큼 비교
                for (int j = 0; j < bomb.length(); j++) {
                    if(bomb.charAt(j)!=stack.get(stack.size()-bomb.length()+j)){
                        hasBomb = false;
                        break;
                    }
                }

                //포함이라면 스택에서 제거해준다.
                if(hasBomb)
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
            }

        }

        // 스택 빈 여부 확인 후 출력
        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            for (Character character : stack) {
                sb.append(character);
            }

            System.out.println(sb.toString());
        }

    }
}
