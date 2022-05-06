package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//시간 80ms
//메모리 11740kb
public class Main_BOJ_1759_암호만들기_골드5 {
	static int L, C;
	static char[] words;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		words = new char[C];
		for (int i = 0; i < C; i++) {
			words[i] = st.nextToken().charAt(0); 
		}
		
		Arrays.sort(words);
		sb = new StringBuilder();
		
		dfs(0, new char[L], 0);
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int cnt, char[] word, int start) {
		if(cnt == L) {
			int vcnt = 0;
			for (int i = 0; i < L; i++) {
				if(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u'){
					vcnt++;
				}
			}
			if(vcnt >= 1 && L - vcnt >= 2) {				
				sb.append(String.valueOf(word)).append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			word[cnt] = words[i];
			dfs(cnt+1, word, i+1);
		}
	}
}
