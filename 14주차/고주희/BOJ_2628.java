import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시간 : 80ms
// 메모리 : 11624KB
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int T = Integer.parseInt(br.readLine());
		int r = 0;
		int c = 0;
		List<Integer> row = new ArrayList<>();
		List<Integer> col = new ArrayList<>();
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().charAt(0)=='0') row.add(Integer.parseInt(st.nextToken()));
			else col.add(Integer.parseInt(st.nextToken()));
		}
		row.sort(null);
		col.sort(null);
		int minr = 0;
		int minc = 0;
		for (Integer integer : row) {
			minr = Math.max(integer-r, minr);
			r = integer;
		}
		minr = Math.max(minr, M-r);
		for (Integer integer : col) {
			minc = Math.max(integer-c, minc);
			c = integer;
		}
		minc = Math.max(minc, N-c);
		System.out.println(minr*minc);
	}

}
