import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {
	
	static int Convey[][] ;
	static int N,K,up,down;
	static int time;
	static final int ON =1;
	static final int OFF=0;
	static BufferedReader br;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Convey = new int[2*N][2];
		up = 0;
		down = N-1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			Convey[i][0] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			int out_order = 0;
			time +=1;
			up = up -1 == -1 ? 2*N-1 : up -1;
			down = down - 1 == -1 ? 2*N-1 : down -1;
			
			if(Convey[down][1] == ON) Convey[down][1] = OFF;
			
			int cur = down;
			int prev = cur -1 == -1 ? 2*N-1 : cur-1;
			while(up != cur) {
				prev = cur -1 == -1 ? 2*N-1 : cur-1;
				if(Convey[prev][1] == ON && Convey[cur][0] > 0 && Convey[cur][1] == 0) {
					Convey[prev][1] = OFF;
					Convey[cur][1] = ON;
					Convey[cur][0] -=1;
				}
				cur = cur == 0 ? 2*N-1:cur -1;
			}
			if(Convey[down][1] == ON ) Convey[down][1] = OFF;

			if(Convey[up][0] != 0) {
				Convey[up][1] = ON;
				Convey[up][0] -=1;
			}
			for (int i = 0; i < 2*N; i++) {
				if(Convey[i][0] == 0) out_order +=1;
			}
			
			if(out_order >= K) {
				break;
			}
		}
		System.out.println(time);
	}
}
