package week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//시간 512ms
//메모리 48028kb
public class Main_BOJ_2141_우체국_골드4 {
	static class Loc implements Comparable<Loc>{
		long loc;
		long people;
		
		public Loc(long loc, long people) {
			this.loc = loc;
			this.people = people;
		}
		
		@Override
		public int compareTo(Loc o) {
			return (int)(this.loc - o.loc);
		}
	}
	
	static int N;
	static List<Loc> list;
	static long totalPeople;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		totalPeople = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long loc = Long.parseLong(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			list.add(new Loc(loc, people));
			totalPeople += people;
		}
		
		Collections.sort(list);
		
		long sum = 0;
		for (Loc loc : list) {
			sum += loc.people;
			
			if(sum >= (totalPeople+1)/2) {
				System.out.println(loc.loc);
				break;
			}
		}
	}
}
