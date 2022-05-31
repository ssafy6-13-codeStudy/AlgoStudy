package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 96ms
//메모리 13844kb
public class Main_BOJ_19942_다이어트_골드5 {
	static int N;
	static int[] minValue; // 단 지 탄 비
	static int[][] ingredient;
	static int min;
	static int[] result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minValue = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		minValue[0] = Integer.parseInt(st.nextToken());
		minValue[1] = Integer.parseInt(st.nextToken());
		minValue[2] = Integer.parseInt(st.nextToken());
		minValue[3] = Integer.parseInt(st.nextToken());
		
		ingredient = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredient[i][0] = Integer.parseInt(st.nextToken());
			ingredient[i][1] = Integer.parseInt(st.nextToken());
			ingredient[i][2] = Integer.parseInt(st.nextToken());
			ingredient[i][3] = Integer.parseInt(st.nextToken());
			ingredient[i][4] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		int[] nums = new int[N];
		Arrays.fill(nums, -1);
		dfs(0, 0, 0, 0, 0, 0, nums);
		
		if(min != Integer.MAX_VALUE) {			
			System.out.println(min);
			for (int i = 0; i < N; i++) {
				if(result[i] == -1) break;
				System.out.print(result[i]+1+" ");
			}
			System.out.println();
		}else {
			System.out.println(-1);
		}
	}

	private static void dfs(int start, int cnt, int p, int f, int s, int v, int[] nums) {
		if(p >= minValue[0] && f >= minValue[1] && s >= minValue[2] && v >= minValue[3]) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if(nums[i] == -1) break;
				sum += ingredient[nums[i]][4];
			}
			if(min > sum) {
				min = sum;
				result = nums.clone();
			}
			return;
		}
		for (int i = start; i < N; i++) {
			nums[cnt] = i;
			dfs(i+1, cnt+1, p+ingredient[i][0], f+ingredient[i][1], s+ingredient[i][2], v+ingredient[i][3], nums);
			nums[cnt] = -1;
		}
		
	}
}
