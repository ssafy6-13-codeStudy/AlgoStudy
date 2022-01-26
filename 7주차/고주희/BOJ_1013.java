package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간 : 144ms
// 메모리 : 12868KB

public class BOJ_1013 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			int status = 0;
			int i = 0;
			int len = str.length();
//			int flag = 0;
			outer : while(i!=len) {
				int c = str.charAt(i)-'0';
//				System.out.print(status+"에서 "+c+"가 들어와서 ");
				switch (status) {
				case 0:
					if(c==0) status = 1;
					else status = 3;
					break;
				case 1:
					if(c==0) status = -1;
					else status = 2;
					break;
				case 2:
					if(c==0) status = 1;
					else status = 3;
					break;
				case 3:
					if(c==0) status = 4;
					else status = -1;
					break;
				case 4:
					if(c==0) status = 5;
					else status = -1;
					break;
				case 5:
					if(c==0) status = 5;
					else status = 6;
					break;
				case 6:
					if(c==0) status = 1;
					else status = 7;
					break;
				case 7:
					if(c==0) status = 8;
					else status = 7;
					break ;
				case 8:
					if(c==0) status = 5;
					else status = 2;
					break ;	
				default:
					break;
				}
				i++;
//				System.out.println(status+"로 갑니다"+flag);
			}
			System.out.println(status==2 || status==6? "YES":"NO");
		}
			
	}


}
