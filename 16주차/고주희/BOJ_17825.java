import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 시간 : 172ms
// 메모리 : 35028KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] order = new int[10];
		String str = br.readLine();
		for (int i = 0; i < 10; i++) {
			order[i] = str.charAt(i*2)-'0';
		}
		max = Integer.MIN_VALUE;
		dfs(1, order, new int[10]);
		System.out.println(max);
	}

	private static void dfs(int start, int[] order, int[] player) {

		if(start==10) {
			getscore(player, order, new boolean[33]);
			return;
		}
		for (int i = 0; i < 4; i++) {
			player[start] = i;
			dfs(start+1, order, player);
		}
	}

	private static void getscore(int[] player, int[] order, boolean[] point) {

		int score = 0;
		
		int[] sp = new int[4];
		for (int j = 0; j < 10; j++) {
				int i = player[j];
				if(sp[i]!=21) {
					int tmp = sp[i];
					for (int k = 0; k < order[j]; k++) {
						switch (sp[i]) {
						case 5:
							if(k==0)sp[i] = 22;
							else sp[i]++;
							break;
						case 10:
							if(k==0) sp[i] = 25;
							else sp[i]++;
							break;
						case 15:
							if(k==0) sp[i] = 27;
							else sp[i]++;
							break;
						case 24:
							sp[i] = 30;
							break;
						case 26:
							sp[i] = 30;
							break;
						case 32:
							sp[i] = 20;
							break;
						case 21:
							break;
						default:
							sp[i]++;
							break;
						}
						if(sp[i]==21) break;
						
					}
					if(sp[i]!=21 && point[sp[i]]) {
						return;
					}
					point[tmp] = false;
					point[sp[i]] = true;
					score += map[sp[i]];
				}
		}
		if(max<score) max = score;
	}

	// 윷놀이 판
	// 5에서 시작하면 22, 10에서 시작하면 25, 15에서 시작하면 27
		// 24에서는 30, 26에서는 30, 29에서는 30
		// 32에서 21
	static int max;
	static int[] map = {0,2,4,6,8,10, //5
						12,14,16,18,20, //10
						22,24,26,28,30, //15
						32,34,36,38,40, 0, //21
						13,16,19,22,24, // 26
						28,27,26,25,30,35}; // 32
	

}
