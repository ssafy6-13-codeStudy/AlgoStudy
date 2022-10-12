import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 160ms
// 메모리 : 16924KB
public class Main {

	static class Node {
		int type;
		int r;
		int c;
		int sub_r;
		int sub_c;
		
		public Node(int type, int r, int c) {
			this.type = type;
			this.r = r;
			this.c = c;
			if(type == 2) {
				this.sub_r = r;
				this.sub_c = c + 1;
			} else if(type == 3) {
				this.sub_r = r + 1;
				this.sub_c = c;
			} else {
				this.sub_r = -1;
				this.sub_c = -1;
			}
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int score = 0;
		int[][] green = new int[6][4];
		int[][] blue = new int[6][4];
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			Node red = new Node(type, x, y);
			score += go_to_green(red, green);
			score += go_to_blue(red, blue);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(score).append("\n");
		sb.append(left_block(green, blue));
		System.out.println(sb);
	}
	private static int left_block(int[][] green, int[][] blue) {

		int total = 0;
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j] > 0) total++;
				if(blue[i][j] > 0) total++;
			}
		}
		return total;
	}
	private static int go_to_blue(Node red, int[][] blue) {

		// type 2 (1 x 2) -> blue에서는 type3와 같음
		// type 3 (2 x 1) -> blue에서는 type2와 같음
		int r = red.c;
		int c = red.r;
		red.r = r;
		red.c = c;
		if(red.type == 2) {
			red.type = 3;
			red.sub_r = r + 1;
			red.sub_c = c;
		} else if(red.type == 3) {
			red.type = 2;
			red.sub_r = r;
			red.sub_c = c + 1;
		} 
		get_red(red, blue);
		int total = 0;
		int score = 0;
		while((score = clean_hole(blue)) > 0) {
			total += score;
		}
		while(block_in_first(blue)) {
			move_down_absolute(blue);
		}
		return total;
	}
	

	private static int go_to_green(Node red, int[][] green) {

		get_red(red, green);
		int total = 0;
		int score = 0;
		while((score = clean_hole(green)) > 0) {
			total += score;
		}
		while(block_in_first(green)) {
			move_down_absolute(green);
		}
		return total;
	}
	
	private static void move_down_absolute(int[][] green) {
		for (int i = 4; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				green[i+1][j] = green[i][j];
				green[i][j] = 0;
			}
		}
	}
	private static boolean block_in_first(int[][] green) {

		for (int i = 0; i < 4; i++) {
			if(green[1][i] > 0) return true;
		}
		return false;
	}

	private static int clean_hole(int[][] green) {

		int score = 0;
		for (int i = 2; i < 6; i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if(green[i][j] > 0) cnt++;
			}
			if(cnt == 4) {
				score++;
				for (int j = 0; j < 4; j++) {
					green[i][j] = 0;
				}
				for (int j = i-1; j >= 0; j--) {
					for (int k = 0; k < 4; k++) {
						green[j+1][k] = green[j][k];
						green[j][k] = 0;
					}
				}
				
			}
			
		}
		return score;
	}
	private static void get_red(Node red, int[][] green) {

		int idx = 0;
		if(red.type == 1 || red.type == 3) {
			for (int i = 0; i < 6; i++) {
				if(green[i][red.c] == 0) {
					idx = i;
				} else break;
			}
			if(red.type == 1) green[idx][red.c] = 1;
			else {
				green[idx-1][red.c] = 3;
				green[idx][red.c] = 5;
			}
		} else if (red.type == 2) {			
			for (int i = 0; i < 6; i++) {
				if(green[i][red.c] == 0 && green[i][red.sub_c] == 0) {
					idx = i;
				} else break;
			}
			green[idx][red.c] = 2;
			green[idx][red.sub_c] = 4;
		}
	}

}
