import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// 시간 : 128ms
// 메모리 : 16152KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		StringBuilder sb = new StringBuilder();
		
		
		while(!(str = br.readLine()).equals("0 0 0")) {
			st = new StringTokenizer(str);
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][][] map = new int[L][R][C];
			int[] start = new int[3];
			int[] end = new int[3];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					char[] col = br.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						char tmp = col[k];
						if(tmp =='#') map[i][j][k] = -1;
						else if(tmp == 'S') {
							start[0] = i;
							start[1] = j;
							start[2] = k;
						}else if(tmp == 'E') {
							end[0] = i;
							end[1] = j;
							end[2] = k;
						}
					}
				}
				br.readLine();
			}
			
			
			int answer = findLoad(start, end, map);
			
			
			if(answer==0) sb.append("Trapped!\n");
			else sb.append("Escaped in ").append(answer - 1).append(" minute(s).\n");
			
		}
		System.out.println(sb);
	}
    
    public static int findLoad(int[] start, int[] end, int[][][] map) {
        
        int[] dl = {-1,1,0,0,0,0};
		    int[] dr = {0,0,-1,1,0,0};
		    int[] dc = {0,0,0,0,-1,1};
        int L = map.length;
        int R = map[0].length;
        int C = map[0][0].length;
        
        Queue<int[]> que = new LinkedList<>();
			  que.offer(start);
			  map[start[0]][start[1]][start[2]] = 1;
			
        while(!que.isEmpty()) {
          int[] cur = que.poll();
          if(cur[0]==end[0] && cur[1]==end[1] && cur[2]==end[2]) {
            return map[cur[0]][cur[1]][cur[2]];
          }
          for (int d = 0; d < 6; d++) {
            int nl = cur[0] + dl[d];
            int nr = cur[1] + dr[d];
            int nc = cur[2] + dc[d];
            if(nl >= L || nr >= R || nc >= C || nl < 0 || nr < 0 || nc < 0) continue;
            if(map[nl][nr][nc]!=0) continue;
            map[nl][nr][nc] = map[cur[0]][cur[1]][cur[2]] + 1;
            que.offer(new int[] {nl, nr, nc});
          }
        }
        return 0;
       }
       

}
