package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 632ms
//메모리 84256kb
//코드길이 1754b

public class Main_BOJ_5430_AC_골드5 {
	static int T;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			char[] func = br.readLine().toCharArray();
			int N = Integer.parseInt(br.readLine());
			String num = br.readLine();
			num = num.substring(1, num.length()-1);
			String[] list = num.split(",");
			
			// 1. init
			Deque<String> deq = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				deq.add(list[i]);
			}
			
			// 2. AC
			System.out.println(AC(func, deq));
		}
	}

	private static String AC(char[] func, Deque<String> deq) {
		int rcnt = 0;
		for (int i = 0; i < func.length; i++) {
			if(func[i] == 'R') {
				rcnt++;
			}else if(func[i] == 'D') {
				if(deq.isEmpty()) {
					return "error";
				}
				if(rcnt%2==0) {
					deq.pollFirst();
				}else {
					deq.pollLast();
				}
			}
		}
		
		StringBuffer st = new StringBuffer();
		st.append('[');
		if(!deq.isEmpty()) { // deq가 비었는지를 확인 - deleteCharAt은 deq가 비어있지 않을 경우만 사용해야 한다.
			if(rcnt%2==0) {
				while(!deq.isEmpty()) {
					st.append(deq.pollFirst());
					st.append(',');
				}
				st.deleteCharAt(st.length()-1);
			}else {
				while(!deq.isEmpty()) {
					st.append(deq.pollLast());
					st.append(',');
				}
				st.deleteCharAt(st.length()-1);
			}
		}
		st.append(']');
		return st.toString();
	}
}
