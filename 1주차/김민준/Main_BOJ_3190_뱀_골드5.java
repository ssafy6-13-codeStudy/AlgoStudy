import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3190_뱀_골드5 {
	static class Loc{
		int r;
		int c;
		
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	};
	
	static int N, K, L;
	static int[][] map;
	static Queue<int[]> move;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static int len, time;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 1;
		}
		L = Integer.parseInt(br.readLine());
		move = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String direction = st.nextToken(); 
			if(direction.equals("L")) {
				move.add(new int[] {time, 1});
			}else if(direction.equals("D")) {
				move.add(new int[] {time, 3});
			}
		}
		
		// 뱀의 시작위치 (뱀값 = 2)
		map[0][0] = 2;
		time = 0;
		start();
		
		System.out.println(time+1);
		
	}

	private static void start() {
		ArrayList<Loc> snake = new ArrayList<>();
		snake.add(new Loc(0,0)); // 뱀 시작위치
		int d = 0;
		
		while(true) {
			Loc head = snake.get(snake.size()-1);
			int nr = head.r + dr[d];
			int nc = head.c + dc[d];
			
			//벽 닿을 때
			if(!check(nr,nc)) return;
			//몸 닿을 때
			if(map[nr][nc] == 2) return;
			
			//몸 늘려 전진
			if(map[nr][nc] != 1) { // 사과 없다면
				// 꼬리가 움직임
				Loc tail = snake.get(0);
				map[tail.r][tail.c] = 0;
				snake.remove(0); 
			}
			snake.add(new Loc(nr,nc));
			map[nr][nc] = 2; // 전진한 칸에 뱀의 머리 위치
			
			time++;
			//방향변환정보 확인
			if(!move.isEmpty() && time == move.peek()[0]) {
				d = (d+move.peek()[1])%4;
				move.poll(); // 값 빼기
			}
		}
	}
	
	public static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
