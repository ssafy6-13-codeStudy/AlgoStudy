import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇청소기_골드5 {
   static int N, M;
   static int[] dr = {-1, 0, 1, 0}; // 0북, 1동, 2남, 3서
   static int[] dc = {0, 1, 0, -1};
   static int R, C, D;
   static int[][] map;
   static int count;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][M];
      st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      D = Integer.parseInt(st.nextToken());
      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < M; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      //start
      count = 0;
      cal(R,C,D);
      
      System.out.println(count);
   }

   private static void cal(int rr, int cc, int dd) {
      if(map[rr][cc] == 0) {
         map[rr][cc] = -1;
         count++;
      }
      boolean clean = false;
      int temp = dd;
      for (int i = 0; i<4; i++) {
         int d = (temp+3)%4;
         int nr = dr[d]+rr;
         int nc = dc[d]+cc;
         if(check(nr,nc) && map[nr][nc] == 0) {
           cal(nr,nc,d);
           clean = true;
           break;
         }
         temp = (temp+3)%4;
      }
      
      if(!clean) {         
         int back_r = rr+dr[(dd+2)%4];
         int back_c = cc+dc[(dd+2)%4];
         if(check(back_r,back_c) && map[back_r][back_c] != 1) {         
           cal(back_r, back_c, dd);
         }
      }
   }
   
   private static boolean check(int r, int c) {
      return r>=0 && r<N && c >=0 && c<M;
   }

}
