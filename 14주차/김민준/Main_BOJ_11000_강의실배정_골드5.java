package week16;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간 672ms
//메모리 69292kb
public class Main_BOJ_11000_강의실배정_골드5 {
	public static class Comp implements Comparator<int[]>{
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0]==o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		}
	}
	
	static int N;
	static int[][] schedule;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		schedule = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(schedule, new Comp());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(schedule[0][1]);
		for (int i = 1; i < N; i++) {
			if(pq.peek() > schedule[i][0]) {
				pq.offer(schedule[i][1]);
			}else {
				pq.poll();
				pq.offer(schedule[i][1]);
			}
		}
		System.out.println(pq.size());
	}
}
