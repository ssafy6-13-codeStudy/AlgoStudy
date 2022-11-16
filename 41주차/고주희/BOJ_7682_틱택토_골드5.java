import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 시간 : 108ms
// 메모리 : 15052KB
public class Main {

	static boolean flag;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringBuilder sb = new StringBuilder();
		while(!(str = br.readLine()).equals("end")) {
			char[][] map = new char[3][3];
			int X = 0;
			int O = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = str.charAt(i*3 +j);
					if(map[i][j] == 'O') O++;
					else if(map[i][j] == 'X') X++;
				}
			}
			if(O > X || Math.abs(O - X) >= 2) {
				sb.append("invalid").append("\n");
				continue;
			}
			flag = false;
			char[][] num = new char[3][3];
			for (char[] cs : num) {
				Arrays.fill(cs, '.');
			}
			dfs(0, 0, num, new boolean[9], map);
			if(flag) {
				sb.append("valid").append("\n");
			} else {
				sb.append("invalid").append("\n");
			}
		}
		System.out.println(sb.substring(0, sb.length()-1));
	}

	private static void dfs(int cnt, int j, char[][] num, boolean[] v, char[][] map) {

		if(!is_same(map, num)) {
			return;
		}
		if(flag) return;
		if(check(num) != '.') {
			if(exact_same(map, num)) {
				flag = true;
			}
			return;
		}
		if(cnt == 9) {
			if(exact_same(map, num)) {
				flag = true;
				return;
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if(v[i]) continue;
			v[i] = true;
			if(cnt % 2 == 0) num[i/3][i%3] = 'X';
			else num[i/3][i%3] = 'O';
			dfs(cnt+1, j, num, v, map);
			num[i/3][i%3] = '.';
			v[i] = false;
		}
	}

	private static boolean exact_same(char[][] map, char[][] num) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[i][j] != num[i][j]) return false;
			}
		}
		return true;
	}


	private static boolean is_same(char[][] map, char[][] num) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[i][j] == '.' && num[i][j] != '.') return false;
				else if(map[i][j] == 'O' && num[i][j] == 'X') return false;
				else if(map[i][j] == 'X' && num[i][j] == 'O') return false;
			}
		}
		return true;
	}

	private static char check(char[][] map) {

		// 가로
		for (int i = 0; i < 3; i++) {
			char stand = map[i][0];
			if(stand == '.') continue;
			boolean flag = true;
			for (int j = 1; j < 3; j++) {
				if(stand != map[i][j]) {
					flag = false;
					break;
				}
			}
			if(flag) return stand;
		}
		// 세로
		for (int i = 0; i < 3; i++) {
			char stand = map[0][i];
			if(stand == '.') continue;
			boolean flag = true;
			for (int j = 1; j < 3; j++) {
				if(stand != map[j][i]) {
					flag = false;
					break;
				}
			}
			if(flag) return stand;
		}
		// 대각선
		char stand = map[0][0];
		if(stand != '.') {			
			boolean flag = true;
			for (int i = 1; i < 3; i++) {
				if(stand != map[i][i]) {
					flag = false;
					break;
				}
			}
			if(flag) return stand;
		}
		
		stand = map[0][2];
		if(stand != '.') {			
			boolean flag = true;
			for (int i = 1; i < 3; i++) {
				if(stand != map[i][2-i]) {
					flag = false;
					break;
				}
			}
			if(flag) return stand;
		}
		return '.';
	}

}
