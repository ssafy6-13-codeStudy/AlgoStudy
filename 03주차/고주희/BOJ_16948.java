package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 92ms
// 메모리 : 12772KB

public class BOJ_16948 {

	
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 탐색
		int[] dr = {-2,-2,0,0,2,2};
		int[] dc = {-1,1,-2,2,-1,1};
		// 정답 - 도착 못하는 경우 -1이니까 디폴트로 -1
		int answer = -1;
		
		int N = Integer.parseInt(br.readLine());
		// 방문 체크
		boolean[][] v = new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {r1, c1, 0});
		v[r1][c1] = true;
		// 만약 처음부터 도착지라면 0 출력
		if(r1==r2 && c1==c2) answer = 0;
		
		// bfs
		outer : while(!que.isEmpty() && !(r1==r2 && c1==c2)) {
			int[] cur = que.poll();
			//System.out.println(Arrays.toString(cur));
			int r = cur[0]; int c = cur[1]; int cnt = cur[2];
			// 6방향 탐색
			for (int d = 0; d < 6; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				// 이미 갔던 곳이거나 범위 밖이면 무시
				if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
				// 도착지면 cnt+1해서 answer에 저장 => while문 나가기
				if(nr==r2 && nc==c2) {
					answer = cnt+1;
					break outer;
				}
				//도착지가 아니면 방문 체크 후 que에 저장
				v[nr][nc] = true;
				que.add(new int[] {nr, nc, cnt+1});
			}
		}
		// answer 출력
		System.out.println(answer);
	}


}
