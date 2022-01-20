package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 시간 : 768ms
// 메모리 : 111976KB
public class BOJ_5430 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String op = br.readLine();
			int N = Integer.parseInt(br.readLine());
			
			Deque<Integer> que = new LinkedList<>();
			String num = br.readLine();
//			System.out.println(num.length());
			if(N==0) {
				if(op.contains("D")) System.out.println("error");
				else System.out.println("[]");
				continue;
			}
				
			
			String[] arr = num.substring(1, num.length()-1).split(",");
//			System.out.println(Arrays.toString(arr));
//			System.out.println();
			for (String string : arr) {
				que.add(Integer.parseInt(string));
//				System.out.println(string);
			}
			String answer = "";
			boolean flag = true;
			char[] opp = op.toCharArray();
			int len = opp.length;
			for (int i = 0; i < len; i++) {
				if(opp[i]=='D') {
					if(que.isEmpty()) {
						answer = "error";
						break;
					}
					else {
						if(flag) {
							que.removeFirst();
						}else {
							que.removeLast();
						}
					}
				}else {
					flag = !flag;
				}
			}
			if(answer.equals("error")) System.out.println(answer);
			else {
				int size = que.size();
				StringBuilder sb = new StringBuilder("[");
				while(!que.isEmpty()) {
					sb.append(flag? que.removeFirst() : que.removeLast());
					if(que.size()!=0)sb.append(',');
				}
				sb.append(']');
				System.out.println(sb.toString());
//				System.out.print("[");
//				if(flag) {
//					
//					for (int i = 0; i < size; i++) {
//						System.out.print(que.pop());
//						if(i!=size-1)System.out.print(",");
////						System.out.println(i);
//					}
//				}else {
//					for (int i = size-1; i >=0; i--) {
//						System.out.print(que.pollLast());
//						if(i!=0)System.out.print(",");
//					}
//				}
//				System.out.println("]");
			}
		}
	}

}
