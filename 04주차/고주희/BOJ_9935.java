package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 시간 : 384ms
// 메모리 : 87572KB

public class BOJ_9935 {

	public static void main(String[] args) throws IOException {

		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		char[] pattern = br.readLine().toCharArray();
		
		// 정답 넣을 곳
		StringBuilder sb = new StringBuilder();
		
		// 길이 정의
		int len = str.length();
		int plen = pattern.length;
		
		// 스택 선언
		Stack<Character> stack = new Stack<>();
		
		
		// str 처음부터 순서대로
		for (int i = 0; i < len; i++) {
			
			// 현재 character 일단 스택에 넣기
			char c = str.charAt(i);
			stack.push(c);
			
			// 현재 character가 패턴 마지막 글자와 같다면
			if(c==pattern[plen-1]) {
				int size = stack.size();
				// 이 때 스택 사이즈가 패턴보다 작다면 이후 작업 하지 않음
                if(size<plen) continue;
                // 동일한지 판별하는 boolean
				boolean cnt = true;
				// 패턴 글자와 비교
				for (int j = 0; j < plen; j++) {
					if(size-plen+j<0) {
                        cnt = false;
                        break;
                    }
					// 하나라도 다를 경우 false로 바꾸고 break
					if(stack.get(size-plen+j)!=pattern[j]) {
						cnt = false;
                        break;
					}
				}
				// cnt가 true면 패턴과 같으므로 패턴 길이만큼 pop
				if(cnt) {
					for (int j = 0; j < plen; j++) {
						stack.pop();
					}
				}
			}
			
		}
		// stack 내용 stringbuilder에 넣기
		for (Character character : stack) {
		    sb.append(character);
		}
		// sb 길이가 0이면 FRULA, 아니면 sb 내용 출력
		// 같은 코드가 stringbuilder를 쓰지 않고 stack을 for문으로 돌려서 출력하는 경우 시간초과가 남!
        System.out.println(sb.length()==0?"FRULA": sb.toString());
	}

}
