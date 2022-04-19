import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시간 : 72ms
// 메모리 : 11604KB
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[] parent = new int[N+1];
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			parent[child] = p;
		}
		
		List<Integer> slist = new ArrayList<>();
		slist.add(s);
		findparent(slist, s, parent);
		List<Integer> elist = new ArrayList<>();
		elist.add(e);
		findparent(elist, e, parent);
		
		System.out.println(commonparent(slist, elist));
	}

	private static void findparent(List<Integer> list, int s, int[] parent) {

		if(parent[s]==0) return;
		list.add(parent[s]);
		findparent(list, parent[s], parent);
	}

	private static int commonparent(List<Integer> slist, List<Integer> elist) {
		int slen = slist.size();
		int elen = elist.size();
		for (int i= 0; i<slen; i++) {
			for (int j=0; j<elen; j++) {
				if(slist.get(i)==elist.get(j)) {
					 return i+j;
				}
			}
		}
		return -1;
	}
}
