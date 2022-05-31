package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//시간 292ms
//메모리 19536kb
//코드길이 506b

public class Main_BOJ_1013_Contact_골드5{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if(str.matches("(100+1+|01)+")) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}
