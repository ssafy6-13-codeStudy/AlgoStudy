package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 ms
//메모리 kb
public class Main_BOJ_2174_로봇시뮬레이션_골드5 {
	public static class Robot{
		int x;
		int y;
		int d;
		
		public Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static int A,B;
	static int N,M;
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	static char[] dir = {'N','E','S','W'};
	static Robot[] robots;
	static int[][] visit;
	static String result = "OK";
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		robots = new Robot[N];
		visit = new int[B][A];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			char d = st.nextToken().charAt(0);
			for (int j = 0; j < 4; j++) {
				if(d == dir[j]) {					
					robots[i-1] = new Robot(x, y, j);
					break;
				}
			}			
			visit[y][x] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotidx = Integer.parseInt(st.nextToken()) -1;
			char order = st.nextToken().charAt(0);
			int repeat = Integer.parseInt(st.nextToken());
			
			boolean flag = cal(robotidx, order, repeat);
			if(flag) break;
		}
		System.out.println(result);
	}
	
	public static boolean cal(int robotidx, char order, int repeat) {
		// start
		for (int i = 0; i < repeat; i++) {			
			// 직진 일때
			if(order == 'F') {
				int nx = robots[robotidx].x + dx[robots[robotidx].d];
				int ny = robots[robotidx].y + dy[robots[robotidx].d];
				
				if(ny < 0 || ny >= B || nx < 0 || nx >= A) {
					result = "Robot "+(robotidx+1)+" crashes into the wall";
					return true;
				}
				if(visit[ny][nx] != 0) {
					result = "Robot "+(robotidx+1)+" crashes into robot "+visit[ny][nx];
					return true;
				}
				visit[robots[robotidx].y][robots[robotidx].x] = 0;
				visit[ny][nx] = (robotidx+1);
				robots[robotidx].x = nx;
				robots[robotidx].y = ny;
			}else if(order == 'R'){		
				if(robots[robotidx].d == 3) {
					robots[robotidx].d = 0;
				}else {
					robots[robotidx].d++;
				}
			}else if(order == 'L') {
				if(robots[robotidx].d == 0) {
					robots[robotidx].d = 3;
				}else {
					robots[robotidx].d--;
				}
			}
		}
		
		return false;
	}
	
	public static void print() {
		for (int i = 0; i < B; i++) {
			for (int j = 0; j < A; j++) {
				System.out.print(visit[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
