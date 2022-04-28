import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 132ms
// 메모리 : 15784KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] dice = new int[N][6];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				dice[i][j] = str.charAt(j*2)-'0';
			}
		}
		
		// (0, 5), (1, 3), (2, 4)가 서로 마주보는 면이다.
		int max = -1;
		for (int i = 0; i < 6; i++) {
			// 바닥이 i면일 때
			int total = 0;
			int top = dice[0][findopp(i)]; // 윗면에 적힌 값은 top 
			total += findmax(0, i, dice); // top, bottom 제외한 값 중 가장 큰 값 찾기
			for (int j = 1; j < N; j++) { // 나머지는
				for (int k = 0; k < 6; k++) { 
					if(dice[j][k]==top) { // 이전 꺼의 top과 값이 같은 면을 찾아서
						top = dice[j][findopp(k)]; // 새로운 top을 찾는다
						total += findmax(j, k, dice); // 그리고 top, bottom 제외한 값 중 가장 큰 값 찾기
						break;
					}
				}
			}
			if(max<total) max = total;
		}
		System.out.println(max);
	}

	private static int findmax(int r, int c, int[][] dice) {

		int N = dice.length;
		int max = -1;
		int opp = findopp(c);
		for (int i = 0; i < 6; i++) {
			if(i!=c && i!=opp) {
				if(max<dice[r][i]) max = dice[r][i];
			}
		}
		return max;
	}

	private static int findopp(int c) {
		int opp = 0;
		switch (c) {
		case 0:
			opp = 5;
			break;
		case 1:
			opp = 3;
			break;
		case 2:
			opp = 4;
			break;
		case 3:
			opp = 1;
			break;
		case 4:
			opp = 2;
			break;
		default:
			break;
		}		
		return opp;
	}

}
