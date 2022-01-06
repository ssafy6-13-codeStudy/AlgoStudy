package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//못품
//시간 
//메모리 
//코드길이 

public class Main_BOJ_1339_단어수학_골드4 {
	static class Word implements Comparable<Word>{
		int word;
		int score = 0;
		int cnt = 1;
		int seq; // 최대 단어순서
		
		public Word(int word, int seq) {
			this.word = word;
			this.seq = seq;
		}

		@Override
		public int compareTo(Word o) {
			if(this.seq == o.seq) {
				return o.cnt - this.cnt;
			}
			return o.seq - this.seq;
		}

		@Override
		public String toString() {
			return "Word [word=" + (char)(word+'A') + ", score=" + score + ", cnt=" + cnt + ", seq=" + seq + "]";
		}
	}
	
	static int N;
	static boolean[] visit;
	static String[] word;
	static int sc;
	static int sum;
	
	static ArrayList<Word> wordlist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[26];
		sc = 9;
		wordlist = new ArrayList<>();
		word = new String[N];
		sum = 0;
		
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();
			char[] temp = word[i].toCharArray();
			for (int j = 0; j < temp.length; j++) {
				if(visit[temp[j]-'A']) {
					for (Word w : wordlist) {
						if(w.word == temp[j]-'A') {
							//더 높은 순서로 변경
							if(w.seq < temp.length-j-1) {
								w.seq = temp.length-j-1;
							}
							w.cnt++;
							break;
						}
					}
				}else {
					wordlist.add(new Word(temp[j]-'A', temp.length-j-1));
					visit[temp[j]-'A'] = true;
				}
			}
		}
		
		// 1. 자릿수가 가장 많은 문자열
		// 2. 같은 자리수에 가장 비중이 많은 문자
		Collections.sort(wordlist);
		
		// 점수 넣기 + 
		for (Word w : wordlist) {
			w.score = sc;
			sc--;
		}
		
		// 단어를 점수로 만들기
		for (int i = 0; i < N; i++) {
			char[] temp = word[i].toCharArray();
			for (int j = 0; j < temp.length; j++) {
				for (Word w : wordlist) {
					if(temp[j]-'A' == w.word) {
						sum += w.score*Math.pow(10, temp.length-j-1);
						break;
					}
				}
			}
		}
		
		System.out.println(sum);
	}
}