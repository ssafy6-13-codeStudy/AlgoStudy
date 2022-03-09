package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 560ms
//메모리 36804kb
//코드길이 2618b
public class Main_BOJ_20057_마법사상어와토네이도_골드3 {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int[][] percent = {{0,0,2,0,0},{0,10,7,1,0},{5,0,0,0,0},{0,10,7,1,0},{0,0,2,0,0}};
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+4][N+4];
		visit = new boolean[N+4][N+4];
		
		for(int i = 2; i < N+2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 2; j < N+2; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tornado();
		int sum = 0;
		int minus = 0;
		
		for(int i = 0; i < N+4; i++) {
			for (int j = 0; j < N+4; j++) {
				sum += map[i][j];
			}
		}
		for(int i = 2; i < N+2; i++) {
			for (int j = 2; j < N+2; j++) {
				minus += map[i][j];
			}
		}
		System.out.println(sum - minus);
	}

	private static void tornado() {
		int r = N/2+2;
		int c = N/2+2;
		visit[r][c] = true;
		// map 맨쪽 맨 위까지 온다면 멈춤
		while(true) {
			for(int d = 0; d < 4; d++) {
				while(true) { 
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(!check(nr,nc)) {
						return;
					}
					
					int sand = map[nr][nc];
					int spread = 0;
					//주변 비율위치에 모래가 뿌려짐
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							if(percent[i][j] != 0) {
								int temp = (sand * percent[i][j])/100;
								map[nr-2+i][nc-2+j] += temp;
								spread += temp;
							}
						}
					}
					// a 자리에 남은 모래가 쌓임
					map[nr+dr[d]][nc+dc[d]] += sand - spread;
					// 이동한 토네이도 위치는 모래가 0이 됨
					map[nr][nc] = 0;
					
					// 이동한 위치가 현재 위치
					r = nr;
					c = nc;
					visit[r][c] = true;
					if(!visit[r+dr[(d+1)%4]][c+dc[(d+1)%4]]) {
						break;
					}
				}
				// 배율 배열 회전
				turnLeft();
			}
		}
	}
	
	// 00 = 04, 01 = 14, 02 = 24,..., 04 = 44
	public static void turnLeft() {
		int[][] temp = new int[5][5];
		
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = percent[j][4-i];
			}
		}	
		percent = temp;
	}
	
	public static boolean check(int r, int c) {
		return r >= 2 && c >= 2 && r < N+2 && c < N+2;
	}
}
