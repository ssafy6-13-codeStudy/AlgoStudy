import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 300ms
// 메모리 : 15164KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] pointer = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pointer[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(pointer);
		
		int M = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		int[] count = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int w = Integer.parseInt(st.nextToken());
			if(w > pointer[N-1]) {
				System.out.println(-1);
				return;
			}
			list.add(w);
		}
		
        list.sort(null);
		
		int time = 0;
		while(!list.isEmpty()) {
            int j = list.size()-1;
			for (int i = N-1; i >= 0; i--) {
                if(pointer[i] >= list.get(j)) {
                    list.remove(j--);
                    if(j==-1) break;
                } else {
                    j--;
                    if(j==-1) break;
                    i++;
                }
			}
			time++;
			if(list.size()==0) break;
			
		}
		System.out.println(time);
	}

}
