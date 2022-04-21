import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 시간 : 76ms
// 메모리 : 11540KB
public class Main {

	static class Map {
		int hor;
		int ver;
		int cross;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] room = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				room[i][j] = str.charAt(j*2);
			}
		}
		Map[][] map = new Map[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Map();
			}
		}
		map[0][1].hor = 1;
		for (int j = 2; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if(room[i][j]==49) continue;
				map[i][j].hor += map[i][j-1].cross+map[i][j-1].hor;
				if(i-1<0) continue;
				map[i][j].ver += map[i-1][j].ver + map[i-1][j].cross;
				if(room[i-1][j]==49 || room[i][j-1]==49) continue;
				map[i][j].cross += map[i-1][j-1].cross + map[i-1][j-1].ver + map[i-1][j-1].hor;
			}
		}
		System.out.println(map[N-1][N-1].cross+map[N-1][N-1].hor+map[N-1][N-1].ver);
	}


}
