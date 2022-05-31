package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 시간 : 84ms
// 메모리 : 11584KB

public class BOJ_11559 {	
	
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int R = 12;
		int C = 6;
		
		int [][] map = new int[R][C];
		
		// .은 46 A는 65 알파벳은 65 이상
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
//		for (int[] bs : map) {
//			System.out.println(Arrays.toString(bs));
//		}
		
		// 한 라운드에 삭제할 좌표 모아두는 리스트
		List<int[]> list = new ArrayList<>();
		// 한 라운드에 방문 체크
		boolean[][] v = new boolean[R][C];
		// 몇 개 부쉈는지
		int cnt = -1;
		// 라운드 수
		int total = 0;
		
		
		while(cnt!=0) {
			// 라운드 시작하면 없어진 뿌요 개수 초기화
			cnt = 0;
			// 4개 이상 모여있는 뿌요 찾기
			// 바닥부터 시작해서 방문한 적 없는 애들만 bfs
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < C; j++) {
					// 방문한 적 없고 46(.)아니면 4개 이상인지 알아보려는 함수 호출
					if(!v[i][j] && map[i][j]!=46) {
						// 4개 이상이면 cnt++
						if(find(map, v, list, i, j)) {
							cnt++;
						}
					}
				}
			}
			// 부술 뿌요 더미가 있으면 라운드 개수 ++
			if(cnt!=0) total++;
			// 부술 뿌요 더미가 없으면 반복문 벗어남
			else break;
			// 뿌요 더미 삭제
			for (int[] is : list) {
				map[is[0]][is[1]] = 46;
			}
			// 리스트 초기화
			list = new ArrayList<>();
			// 떠 있는 뿌요 아래로 내려주기
			down(map);
			// 방문 체크 초기화
			v = new boolean[R][C];
		};

		// 라운드 수 출력
		System.out.println(total);
		
		
		
	}

	// 뿌요 내려주는 함수
	private static void down(int[][] map) {

		// 제일 낮은 곳이 11이니까
		int bottom = 11;
		
		
		for (int j = 0; j < 6; j++) {
			// 각 열마다 제일 낮은 곳은 11이니까 bottom 초기화
			bottom = 11;
			for (int i = 11; i >= 0; i--) {
				// . 보다 크면 알파벳
				if(map[i][j]>46) {
					// 나오는 것마다 무조건 바닥으로 내림
					map[bottom][j] = map[i][j];
					// 그리고 현재 자리를 .으로 만들어줌.
					// 단 현재 자리와 현재 바닥이 같은 경우에는 안 함
					if(bottom!=i) map[i][j] = 46;
					// 바닥을 한 칸 올림
					bottom--;
					
				}
			}
		}
		
	}

	// 부술 뿌요가 있나?
	private static boolean find(int[][] map, boolean[][] v, List<int[]> list, int rr, int rc) {
		
		// 필요 변수 선언
		int R = 12;
		int C = 6;
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		// bfs할 que
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {rr,rc});
		v[rr][rc] = true;
		// 현재 위치의 뿌요 색
		int color = map[rr][rc];
		// 몇 개가 모여있는지 저장할 list
		List<int[]> nlist = new ArrayList<>();
		nlist.add(new int[] {rr,rc});
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0], c = cur[1];
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				// 범위 벗어나지 않고, 방문한 적 없고, 지정된 색과 같아야 함
				if(nr>=R || nc>=C || nr<0 || nc<0 || v[nr][nc] || color!=map[nr][nc]) continue;
				v[nr][nc] = true;		
				nlist.add(new int[] {nr,nc});
				que.add(new int[] {nr,nc});
				
			}
		}
		// 이번에 모은 뿌요 개수가 4개 이상이면
		if(nlist.size()>=4) {
			// 이번 라운드 삭제 리스트에 추가하고
			for (int[] is : nlist) {
				list.add(is);
			}
			// true 리턴
			return true;
		}
		// 4 미만이면 삭제할 수 없으므로 그냥 false 리턴
		return false;
	}

}
