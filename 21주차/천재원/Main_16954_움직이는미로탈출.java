// 	176120kb
//   316ms

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
public class Main_16954_움직이는미로탈출 {
	public static void main(String[] args) throws IOException{
		char[][] Map = new char[8][8];
		char[][][] TimeStamp = new char[9][8][8];
		int N = 8;
		int[] dr = {0,-1,-1,0,1,1,1,0,-1};
		int[] dc = {0,0,1,1,1,0,-1,-1,-1};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			Map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				TimeStamp[0][i][j] = Map[i][j];
			}
		}		
		for(int t=1; t<N; t++) {
			for(int i=N-1; i>0; i--) {
				for(int j=0; j<N; j++) {
					TimeStamp[t][i][j] = TimeStamp[t-1][i-1][j];
				}
			}
			
			for(int i=0; i<N; i++) {
				TimeStamp[t][0][i] = '.';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				TimeStamp[8][i][j] = '.';
			}
		}
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {7,0,0});
		
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			if(TimeStamp[cur[2]][cur[0]][cur[1]] == '#') {
				continue;
			}
			
			for(int i=0; i<dr.length; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(nr>=0 && nr<N && nc>=0 && nc<N && TimeStamp[cur[2]][nr][nc] != '#') {
					if(nr==0 && nc==7) {
						System.out.println("1");
						System.exit(0);
					}
					que.offer(new int[] {nr,nc,cur[2]+1 > 7 ? 8 : cur[2]+1});
				}
			}
		}
		System.out.println("0");
	}
}
