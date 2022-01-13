// 32508KB
// 596ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main_5052_전화번호목록 {
	static BufferedReader br ;
	static int T,N;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			boolean flag = true;
			N = Integer.parseInt(br.readLine());

			String[] phonebook = new String[N];
			for (int i = 0; i < N; i++) {
				phonebook[i] = br.readLine();
			}
			
			Arrays.sort(phonebook);
			
			for (int i = 0; i < N-1; i++) {
				if(phonebook[i+1].startsWith(phonebook[i])) {
					flag = false;
					break;
				}
			}
			
			System.out.println(flag?"YES":"NO");
			
		}
	}

}
