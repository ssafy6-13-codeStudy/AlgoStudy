package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 200ms
//메모리 19732kb
public class Main_BOJ_2116_주사위쌓기_골드4 {
	static int N;
	static int[][] dices; // 위 서 남 동 북 아래
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		for (int i = 0; i < 6; i++) {
			int top = dices[0][i];			
			int bottom = dices[0][findReverse(i)];
			int sum = 0;
			sum += findMax(top, bottom);
			
			for (int j = 1; j < N; j++) {
				for (int k = 0; k < 6; k++) {
					if(dices[j][k] == top) { // j번 주사위의 아랫면과 이전 주사위의 윗면이 같다면
						// 현재 층의 아랫면 윗면 갱신
						bottom = dices[j][k];
						top = dices[j][findReverse(k)];
						break;
					}
				}
				sum += findMax(top, bottom);
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
		
	}
	
	public static int findMax(int top, int bottom) {
		int result = 6;
		for (int i = 6; i > 0; i--) {
			if(i == top || i == bottom) continue;
			result = i;
			break;
		}
		return result;
		
	}
	
	public static int findReverse(int d) {
		int reverse = 0;
		if(d == 0) {
			reverse = 5;
		}else if(d == 1) {
			reverse = 3;
		}else if(d == 2) {
			reverse = 4;
		}else if(d == 3) {
			reverse = 1;
		}else if(d == 4) {
			reverse = 2;
		}else {
			reverse = 0;
		}
		return reverse;
	}
}
