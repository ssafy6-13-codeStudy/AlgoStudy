package week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//시간 340ms
//메모리 15136kb
public class Main_BOJ_1092_배_골드5 {
	static int N, M;
	static Integer[] crane;
	static List<Integer> box;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		crane = new Integer[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(crane, Collections.reverseOrder());
		
		M = Integer.parseInt(br.readLine());
		box = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(box, Collections.reverseOrder());
		
		System.out.println(cal());
	}
	
	public static int cal() {
		if(box.get(0) > crane[0]) {
			return -1;
		}
		
		int cnt = 0;
		while(!box.isEmpty()) {
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if(idx == box.size()) break;
				if(crane[i] >= box.get(idx)) {
					box.remove(idx);
				}else {
					idx++;
					i--;
				}
			}
			cnt++;
		}
		
		return cnt;
	}
}
