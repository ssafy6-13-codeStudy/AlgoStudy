// 96ms
// 12684kb

import java.util.*;
import java.io.*;


public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, answer = Integer.MAX_VALUE;
	static Food grocery[];
	static boolean[] answerArr;
	static int PROTEIN, FAT, CARBON, VITAMIN;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		grocery = new Food[N];
		answerArr = new boolean[N];
		st = new StringTokenizer(br.readLine());
		PROTEIN = Integer.parseInt(st.nextToken());
		FAT = Integer.parseInt(st.nextToken());
		CARBON = Integer.parseInt(st.nextToken());
		VITAMIN = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			grocery[i] = new Food(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					);
		}
		dfs(0,0,new boolean[N]);
		if(answer != Integer.MAX_VALUE) {
			System.out.println(answer);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<N; i++) {
				if(answerArr[i]) sb.append(i+1+" ");
			}
			System.out.println(sb.toString());
		}else {
			System.out.println("-1");
		}

	}
	
	private static void dfs(int sum, int cur, boolean[] visited) {
		
		if(sum >= answer) {
			return;
		}
		
		if(sum < answer) {
			if(check(visited)) {
				answer = Math.min(sum, answer);
				System.arraycopy(visited, 0, answerArr, 0, N);
				return;
			} 
		}
		
		for(int i=cur; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(sum + grocery[i].price, i+1, visited);
			visited[i] = false;
		}
		
	}

	private static boolean check(boolean[] visited) {
		
		int pSum = 0;
		int fSum = 0;
		int cSum = 0;
		int vSum = 0;
		for(int i=0; i<N; i++) {
			if(!visited[i]) continue;
			Food food = grocery[i];
			pSum += food.protein;
			fSum += food.fat;
			cSum += food.carbon;
			vSum += food.vitamin;
		}
		
		if(pSum >= PROTEIN && cSum >= CARBON && fSum >= FAT && vSum >= VITAMIN) return true;
		
		return false;
	}

	public static class Food{
		int protein;
		int fat;
		int carbon;
		int vitamin;
		int price;
		
		public Food(int protein, int fat, int carbon, int vitamin, int price) {
			this.protein = protein;
			this.fat = fat;
			this.carbon = carbon;
			this.vitamin = vitamin;
			this.price = price;
		}
	}
}
