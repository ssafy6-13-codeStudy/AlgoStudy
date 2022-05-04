import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 76ms
// 메모리 : 11560KB

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dices = new int[6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		if(N!=1) {
			long total = findown(dices) * ((long)(N-2)*(N-2)*5 + (N-2) * 4) + findtwo(dices) * ((N-2) * 8 + 4) + findthree(dices) * 4;
			System.out.println(total);
		}else {
			long total = 0;
			int max = -1;
			for (int i = 0; i < 6; i++) {
				total += dices[i];
				if(dices[i]>max) max = dices[i];
			}
			System.out.println(total-max);
		}
	}

	
	private static long findthree(int[] dices) {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					if(i + j == 5 || j + k == 5 || k + i == 5) continue;
					if(i==j || j==k || k==i) continue;
					if(min>dices[i]+dices[j]+dices[k]) min = dices[i]+dices[j]+dices[k];
				}
			}
		}
		return min;
	}

	private static long findown(int[] dices) {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			if(dices[i]<min) min = dices[i];
		}
		return min;
	}

	// (0,5), (1,4), (2,3)은 서로 반대편
	private static long findtwo(int[] dices) {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if(i + j == 5) continue;
				if(i==j) continue;
				if(min>dices[i]+dices[j]) min = dices[i] + dices[j];
			}
		}
		return min;
	}

}
