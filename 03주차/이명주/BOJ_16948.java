import java.io.*;
import java.util.*;

/*
	 데스나이트
	 실버 1
	 시간 : 92ms
	 메모리 : 12304kb
	 풀이시간 : 20분
*/

public class Main_BOJ_16948_데스나이트 {
	

	/*
	 * dr dc에 이동 할 수 있는 여섯가지 경우를 저장. 
	 * 출발 point를 큐에 add 
	 * 반복문으로 갈 수 있는 좌표들을 큐에 차례로 저장 (방문 여부 체크하면서) 
	 * 목적 좌표에 도착한다면 bfs의 depth를 출력 
	 * 큐가 빌 때 까지 목적 좌표에 도착하지 않는다면 -1 출력
	 */

	static int N;
	static boolean[][] visited;
	static Point p1, p2;
	static int[] dr = { -2, -2, 0, 0, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		visited = new boolean[N][N];

		p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

		System.out.println(bfs());

	}

	private static int bfs() {

		Queue<Point> q = new LinkedList<>();

		q.add(p1);
		visited[p1.r][p1.c] = true;

		while (!q.isEmpty()) {

			Point cur = q.poll();

			for (int d = 0; d < 6; d++) {

				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || visited[nr][nc]) continue;

				if (nr == p2.r && nc == p2.c) return cur.time + 1;

				q.add(new Point(nr, nc, cur.time + 1));
				visited[nr][nc] = true;
			}
		}

		return -1;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static class Point {
		int r;
		int c;
		int time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
