// 80ms
// 11744 kb

import java.util.*;
import java.io.*;

public class Main_16928_뱀과사다리게임 {
	static int N,M;
	static Map<Integer, Integer>ladder = new HashMap<>();
	static Map<Integer, Integer>snake = new HashMap<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int map[] = new int[101];
	static boolean visited[] = new boolean[101];
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Queue<Integer> que = new LinkedList<Integer>();
		int now = 1;
		que.add(now);
		map[now] = 0;
		while(!que.isEmpty()) {
			int cur = que.poll();
			for (int i = 1; i <= 6; i++) {
				int nr = cur + i;
				if(ladder.containsKey(nr)) nr = ladder.get(nr);
				else if(snake.containsKey(nr)) nr = snake.get(nr);
				if(nr>100) continue; 
				if(!visited[nr]) { 
					que.add(nr);
					map[nr] = map[cur] +1;
					visited[nr] = true;
				}else if(map[nr] > map[cur] +1) { // 이미 방문했는데 만약 더 짧은 cnt가 나올수 있으면 재방문
					que.add(nr);
					map[nr] = map[cur] +1;
				}
			}
			
			
		}
		System.out.println(map[100]); // 100번째 출력 ㅇㅋ?
		
	}

}
