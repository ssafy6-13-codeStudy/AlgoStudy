package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dr = {0,1, 0,-1};
		int[] dc = {1,0,-1,0}; //오->아래->왼->위
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] apple = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			apple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		List<int[]> direc = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int dir = st.nextToken().equals("L")? -1 : 1;
			direc.add(new int[] {time, dir});
		}
		List<int[]> snake = new LinkedList<>();
		snake.add(new int[] {0,0});
		int snakedir = 0; 
		int time = 0;
		outer : while(true) {
//			System.out.println("hi");
			time++;
			int[] head = snake.get(snake.size()-1);
			int nr = head[0]+dr[snakedir];
			int nc = head[1]+dc[snakedir];
			if(nc<0 || nc>=N || nr<0 || nr>=N) {
				break;
			}
			for (int[] i : snake) {
//				System.out.println("여긴?");
				if(nr==i[0] && nc==i[1]) {
					break outer;
				}
			}
			if(apple[nr][nc]==0) {
				snake.remove(0);
				snake.add(new int[] {nr,nc});
			}
			else {
				snake.add(new int[] {nr,nc});
				apple[nr][nc] = 0;
			}
			
			if(!direc.isEmpty()) {
				int[] dir = direc.get(0);
				if(dir[0]==time) {
					if(dir[1]==1) snakedir = (snakedir+1)%4;
					else snakedir = (snakedir+3)%4;
					direc.remove(0);
				}
				
			}
			
		}
		System.out.println(time);
	}


}
