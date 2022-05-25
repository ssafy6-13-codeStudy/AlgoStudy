import java.io.*;
import java.util.*;


public class Main_BOJ_1062_가르침_골드4 {
	static int N,K;
	static int Cnt = 5;
	static int answer;
	static List<char[]> words = new ArrayList<>();
	static boolean alphabet[] = new boolean[26];
	static boolean initVisted[] = new boolean[26];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//a n t i c
		init(initVisted);
		init(alphabet);
		
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			tmp = tmp.replace("anta", "");
			tmp = tmp.replace("tica","");
			char[] tmp_arr = tmp.toCharArray();
			for(int p=0; p<tmp_arr.length; p++) {
				if(!alphabet[tmp_arr[p]-'a']) {
					alphabet[tmp_arr[p]-'a'] = true;
					Cnt +=1;
				}
			}
			words.add(tmp_arr);
		}
		
		if(K<5) {
			System.out.println("0");
		}else if(K>Cnt) {
			System.out.println(N);
		}else {
			dfs(5, 0, initVisted);
			System.out.println(answer);
		}
	}
	
	private static void dfs(int cnt, int src, boolean[] visited) {		
		if(cnt == K) {
			int wordCnt = 0;
			for(int i=0 ;i<N; i++) {
				boolean flag = true;
				for(int j=0; j<words.get(i).length; j++) {
					if(!visited[words.get(i)[j] - 'a']) {
						flag = !flag;
						break;
					}
				}
				if(flag) wordCnt +=1;
			}
			answer = Math.max(answer, wordCnt);
			return;
		}
		
		for(int i=0; i<26; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(cnt+1, i+1, visited);
				visited[i] = false;
			}
		}
	}


	private static void init(boolean[] arr) {
		arr['a'-'a'] = true;
		arr['n'-'a'] = true;
		arr['t'-'a'] = true;
		arr['i'-'a'] = true;
		arr['c'-'a'] = true;
	}
}
