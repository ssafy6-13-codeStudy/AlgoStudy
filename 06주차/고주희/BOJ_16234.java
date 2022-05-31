package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 916ms
// 메모리 : 298372KB

public class BOJ_16234 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 방향
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		// 변수 입력
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		// 맵 입력
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			// 영토 여는 나라끼리 그룹화
			int group = 1;
			// 그룹 & 방문 표시
			int[][] v = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이미 그룹에 속한 나라는 지나감
					if(v[i][j]!=0) continue;
					
					// bfs로 진행
					Queue<int[]> que = new LinkedList<>();
					que.offer(new int[] {i,j});
					v[i][j] = group;
					int total = map[i][j];
					List<int[]> list = new ArrayList<int[]>();
					list.add(new int[] {i,j});
					while(!que.isEmpty()) {
						int[] cur = que.poll();
						int r = cur[0], c = cur[1];
						for (int d = 0; d < 4; d++) {
							int nr = r+dr[d];
							int nc = c+dc[d];
							if(nr<0 || nc<0 || nr >=N || nc>=N || v[nr][nc]!=0) continue;
							int minus = Math.abs(map[r][c]-map[nr][nc]);
							// total과 좌표 저장
							if(minus>=L && minus <= R) {
								que.offer(new int[] {nr, nc});
								v[nr][nc] = group;
								total +=map[nr][nc];
								list.add(new int[] {nr,nc});
							}
						} 
					}
					// 인구조절
					int size = list.size();
					for (int[] ks : list) {
						map[ks[0]][ks[1]] = total/size;
					}
					group++;

				}
			}

			if(group==N*N+1) break;
			
			time++;

		}
		System.out.println(time);
	}

}
