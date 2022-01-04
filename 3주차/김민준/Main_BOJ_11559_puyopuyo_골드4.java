package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//시간 140ms
//메모리 14444kb
//코드길이 2794

public class Main_BOJ_11559_puyopuyo_골드4 {
	static class Loc{
		int r;
		int c;

		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static char[][] map;
	static int N = 12;
	static int M = 6;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j];
			}
		}

		count = 0;

		while(true) {
			boolean check = false;
			// 1. 현재 상태에서 터질 수 있는 뿌요 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 뿌요가 있는 지점이라면
					if(map[i][j] != '.') {
						ArrayList<Loc> list = bfs(i,j, map[i][j]);
						//4개 이상 연결되어 있다면
						if(list.size() >= 4) {
							check = true;
							// 받아온 위치 정보를 map에 갱신
							for (Loc loc : list) {
								map[loc.r][loc.c] = '.';
							}
						}
					}
				}
			}

			//연쇄 유무
			if(check) {
				count++;
			}else {
				break;
			}
			
			// 2. 터진 후에 아래로 뿌요가 내려감
			for (int i = N-1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != '.') {
						char puyo = map[i][j]; 
						int cr = i;
						int nr = i + dr[2]; // 아래 방향
						
						while(check(nr,j) && map[nr][j] == '.') { // 아래에 바닥이나 뿌요가 있으면 안됨
							map[nr][j] = puyo;
							map[cr][j] = '.';

							cr = nr;
							nr += dr[2];
						}
					}
				}
			}
		}

		System.out.println(count);
	}

	private static ArrayList<Loc> bfs(int r, int c, char puyo) {
		Queue<Loc> q = new LinkedList<>();
		ArrayList<Loc> list = new ArrayList<>();
		boolean[][] visit = new boolean[N][M];
		visit[r][c] = true;

		q.offer(new Loc(r,c));
		list.add(new Loc(r,c));
		while(!q.isEmpty()) {
			Loc curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];

				if(!check(nr,nc)) continue;
				if(visit[nr][nc]) continue;

				if(map[nr][nc] == puyo) {
					visit[nr][nc] = true;
					q.offer(new Loc(nr,nc));
					list.add(new Loc(nr,nc));
				}
			}
		}
		
		return list;
	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}