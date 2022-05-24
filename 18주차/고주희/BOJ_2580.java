import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 328ms
// 메모리 : 15936KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] sudoku = new int[9][9];
		boolean[][][] number = new boolean[3][9][10];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findNumber(sudoku, number, 0);
	}

	private static void findNumber(int[][] sudoku, boolean[][][] number, int cnt) {

		if(cnt==81) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]);
					sb.append(" ");
				}
				sb.append("\n");
			}
	        System.out.println(sb.toString());
			System.exit(0);
		}
		int r = cnt/9;
		int c = cnt%9;
        int n3x3 = (r / 3) * 3 + (c / 3);
		if(sudoku[r][c]!=0) {
			findNumber(sudoku, number, cnt+1);
			return;
		}
		for (int j = 0; j < 9; j++) {
			number[0][r][sudoku[r][j]] = true;
			number[1][c][sudoku[j][c]] = true;
		}
		int startr = r/3 * 3;
		int startc = c/3 * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(sudoku[startr+i][startc+j]==0) continue;
				if(startr+i==r && startc+j==c) continue;
				number[2][n3x3][sudoku[startr+i][startc+j]] = true;
			}
		}
		int count = 0;
		for (int i = 1; i < 10; i++) {
				if(number[0][r][i] || number[1][c][i] || number[2][n3x3][i]) continue;
				number[0][r][i] = true;
				number[1][c][i] = true;
				number[2][n3x3][i] = true;
				sudoku[r][c] = i;
				count++;
				findNumber(sudoku, number, cnt+1);
				number[0][r][i] = false;
				number[1][c][i] = false;
				number[2][n3x3][i] = false;
				sudoku[r][c] = 0;
		}
		if(count==0) {
			sudoku[r][c] = 0;
			return;
		}
	}

}
