package week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//시간 204ms
//메모리 46412kb
public class Main_BOJ_1253_좋다_골드4 {
	static int N;
	static int[] num;
	static HashMap<Integer, Integer> map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		map = new HashMap<>();
		int count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(num[i])) {
				map.put(num[i], 1);
			}else {
				map.put(num[i], map.get(num[i]) + 1);
			}
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				int sum = num[i] + num[j];
				if(map.containsKey(sum)) {
					int cnt = map.get(sum);
					
					if(num[i] == 0 && num[j] == 0) {
						if(cnt < 3) continue;
					}else if(num[i] == 0 || num[j] == 0) {
						if(cnt < 2) continue;
					}
					count += cnt;
					map.remove(sum);
				}
			}
		}
		
		System.out.println(count);
	}
}
