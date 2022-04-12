import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 76ms
// 메모리 : 11660KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[][] map = new int[B][A];
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] robot = new int[N+1][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            robot[i][0] = r;
            robot[i][1] = c;
			char d = st.nextToken().charAt(0);
			if(d == 'E') robot[i][2] = 3;
			else if(d == 'W') robot[i][2] = 1;
			else if(d == 'S') robot[i][2] = 0;
			else robot[i][2] = 2;
			map[r][c] = i;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			char op = st.nextToken().charAt(0);
			int s = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < s; j++) {
				if(op=='L') {
					if(--robot[r][2]==-1)robot[r][2] = 3;
					
				}else if(op=='R') {
					if(++robot[r][2]==4) robot[r][2] = 0;
				}else {
                    int d = robot[r][2];
                    int rr = robot[r][0];
                    int rc = robot[r][1];
					int nr = rr + dr[d];
					int nc = rc + dc[d];
					if(nr<0 || nr>=B || nc<0 || nc>=A) {
						sb.append("Robot ");
						sb.append(r);
						sb.append(" crashes into the wall");
						System.out.println(sb.toString());
						return;
					}
					if(map[nr][nc]!=0) {
						sb.append("Robot ");
						sb.append(r);
						sb.append(" crashes into robot ");
						sb.append(map[nr][nc]);
						System.out.println(sb.toString());
						return;
					}
					map[nr][nc] = r;
					map[rr][rc] = 0;
					robot[r][0] = nr;
					robot[r][1] = nc;
				}
			}
			
		}
		System.out.println("OK");
	}

}
