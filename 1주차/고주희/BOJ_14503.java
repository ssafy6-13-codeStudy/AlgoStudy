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
		room[rr][rc] = 2;
		int count = 1;
		int fail = 0;
		while(true) {
			if(cangoleft(rr, rc, rd, room)) {
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
				fail++;
				rd = (rd+3)%4;
				if(fail==4) {
					if(canback(rr, rc, rd, room)) {
						int nd = (rd+2)%4;
						rr +=dr[nd];
						rc +=dc[nd];
					}else {
						break;
					}
					fail = 0;
				}
				
			}
		}
		System.out.println(count);
		
	}
	private static boolean canback(int rr, int rc, int rd, int[][] room) {
		int nd = (rd+2)%4;
		if(room[rr+dr[nd]][rc+dc[nd]]==1) return false;
		else return true;
		
	}
	private static boolean cangoleft(int rr, int rc, int rd, int[][] room) {
		int nd = (rd+3)%4;
		if(room[rr+dr[nd]][rc+dc[nd]]==0) return true;
		else return false;
	}
	
}
