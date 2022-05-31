package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

//시간 660ms
//메모리 60932kb
//코드길이 1177b

public class Main_BOJ_9935_문자열폭발_골드4 {
	static String str;
	static String bomb;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		bomb = br.readLine();
		stack = new Stack<>();
		
		// 1. 메모리초과 발생 -> 스택으로 풀이 => 스택에 담아놓고 폭탄 글자만큼 비교
		//		그래도 해결안됨 -> stringbuffer로 풀이
		
		for (int i = str.length()-1; i >= 0; i--) {
			stack.push(str.charAt(i));
			if(stack.size() >= bomb.length() && stack.peek() == bomb.charAt(0)) {
				boolean check = true;
				for (int j = 0; j < bomb.length(); j++) {
					if(stack.get(stack.size() - 1 - j) != bomb.charAt(j)) {
						check = false;
						break;
					}
				}
				if(check) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}else {
			StringBuffer sb = new StringBuffer();
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			System.out.println(sb.toString());
		}
	}
}
