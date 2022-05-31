// 876 ms
// 203664 kb

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main_21610_마법사상어와비바라기 {
	static int map[][] ;
	static int N,M;
	static int dr[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dc[] = {0,-1,-1,0,1,1,1,0,-1};
	static int dr2[] = {-1,-1,1,1};
	static int dc2[] = {-1,1,-1,1};
	static Queue<int[]> cloud = new LinkedList<int[]>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = T2I(st.nextToken());
		M = T2I(st.nextToken());

		map = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = T2I(st.nextToken());
			}
		}
		cloud.add(new int[] {N,1});
		cloud.add(new int[] {N,2});
		cloud.add(new int[] {N-1,1});
		cloud.add(new int[] {N-1,2});
		
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int dirc = T2I(st.nextToken());
			int len = T2I(st.nextToken());
			boolean[][] visited = new boolean[N+1][N+1];

			// 구름을 len 만큼 이동 
			for (int l = 1; l <= len; l++) {
				int size = cloud.size();
				for (int j = 1; j <= size; j++) {
					int[] cur = cloud.poll();
					cloud.add(new int[] {
					cur[0] + dr[dirc] == 0 ? N : ((cur[0] + dr[dirc]) == N +1 ? 1 : cur[0]+dr[dirc]),
					cur[1] + dc[dirc] == 0 ? N : ((cur[1] + dc[dirc]) == N +1 ? 1 : cur[1]+dc[dirc]) }
					);
				}
				
			}
			// 구름의 비의 양 1 증가
			int size = cloud.size();
			for (int j = 0; j < size; j++) {
				int[] cur = cloud.poll();

				map[cur[0]][cur[1]] +=1;
				cloud.add(cur);
			}

			// 구름 대각선 위치에 물이 있는 지 확인 후 cnt 만큼 증가
			for (int j = 0; j < size; j++) {
				int [] cur = cloud.poll();
//				System.out.println("cloud position : " + Arrays.toString(cur));
				visited[cur[0]][cur[1]] = true;
				int cnt =0;
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr2[d];
					int nc = cur[1] + dc2[d];
					if(nr>=1 && nr<=N && nc>= 1 && nc<= N && map[nr][nc] >0) {
						cnt +=1;
					}
				}
				map[cur[0]][cur[1]] += cnt;
			}			
//			if(i == M ) break;
			// 물의 양이 2 이상이면서 구름이 소멸되지 않은 곳에서 구름이 생성
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if(map[r][c] >= 2 && !visited[r][c]) {
						cloud.add(new int[] {r,c});
						map[r][c] -= 2;
					}
				}
			}
		}
		
		System.out.println(countWater());
		
	}
	
	public static int countWater() {
		int result =0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				result += map[r][c];
			}
		}
		return result;
	}

	public static int T2I(String token) {
		return Integer.parseInt(token);
	}
}
