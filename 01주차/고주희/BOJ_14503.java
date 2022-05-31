package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간: 128ms
// 메모리: 14416KB

public class BOJ_14503 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int rr = Integer.parseInt(st.nextToken());
		int rc = Integer.parseInt(st.nextToken());
		int rd = Integer.parseInt(st.nextToken());
		
		int[][] room = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 로봇청소기 초기 위치 청소 => count도 증가
		room[rr][rc] = 2;
		int count = 1;
		// 탐색 실패 횟수
		int fail = 0; 
		while(true) {
			// 왼쪽을 청소할 수 있는가?
			if(cangoleft(rr, rc, rd, room)) {
				// 있으면 방향, 위치 바꾸고 해당 자리 청소, 지금까지 모았던 fail값 0으로
				rd = (rd+3)%4;
				rr +=dr[rd];
				rc +=dc[rd];
				count++;
				room[rr][rc]=count;
				fail = 0;
//				for (int i = 0; i < room.length; i++) {
//					System.out.println(Arrays.toString(room[i]));
//				}
//				System.out.println();
			}else {
				// 청소할 수 없으면 fail값++
				fail++;
				// 방향 바꿔주고
				rd = (rd+3)%4;
				// 만일 사방이 다 청소할 수 없다면
				if(fail==4) {
					// 후진할 수 있는 지 확인
					if(canback(rr, rc, rd, room)) {
						// 후진 가능한 경우 위치 변경, 방향은 변경하지 않음
						int nd = (rd+2)%4;
						rr +=dr[nd];
						rc +=dc[nd];
					}else {
						// 후진 불가능한 경우 while문 break
						break;
					}
					//후진했다면 fail값 초기화
					fail = 0;
				}
				
			}
		}
		// count 출력
		System.out.println(count);
		
	}
	// 후진 가능한지 알아내는 함수
	private static boolean canback(int rr, int rc, int rd, int[][] room) {
		int nd = (rd+2)%4;
		if(room[rr+dr[nd]][rc+dc[nd]]==1) return false;
		else return true;
		
	}
	// 왼쪽 칸이 청소 가능한 칸인지 알아보는 함수
	private static boolean cangoleft(int rr, int rc, int rd, int[][] room) {
		int nd = (rd+3)%4;
		if(room[rr+dr[nd]][rc+dc[nd]]==0) return true;
		else return false;
	}
	
}
