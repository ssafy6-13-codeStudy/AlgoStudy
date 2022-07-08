import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 484ms
// 메모리 : 46964KB
public class Main {

	static class Country implements Comparable<Country>{
		long people;
		long loc;
		
		public Country(long people, long loc) {
			this.people = people;
			this.loc = loc;
		}
		
		@Override
		public int compareTo(Country o) {
			return Long.compare(this.loc, o.loc);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Country[] ctr = new Country[N];
		
		long total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long loc = Long.parseLong(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			total += people;
			ctr[i] = new Country(people, loc);
		}
		
		if(total%2==1) {
			total = total/2 + 1;
		} else {
			total = total/2;
		}
		Arrays.sort(ctr);
		
		long tmp = 0;
		long answer = 0;
		for(int i = 0; i < N; i++) {
			tmp += ctr[i].people;
            if(total <= tmp) {
				answer = ctr[i].loc;
				break;
			}
		}
		System.out.println(answer);
	}

}
