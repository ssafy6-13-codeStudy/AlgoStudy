import java.io.*;
import java.util.*;

public class Main {

  /*
    숨바꼭질3
    시간 : 160ms
    메모리 : 17676kb
     */

	static int N, K;
	static int result = Integer.MAX_VALUE;
	static boolean[] v = new boolean[100001];
	static PriorityQueue<node> q;

	static public class node implements Comparable<node> {
		int n;
		int sec;

		public node(int n, int sec) {
			this.n = n;
			this.sec = sec;
		}

		@Override
		public int compareTo(node o) {
			return this.sec - o.sec;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		v[N] = true;
		bfs();

		System.out.println(result);

	}

	private static void bfs() {
		q = new PriorityQueue<>();

		q.add(new node(N, 0));

		while (!q.isEmpty()) {
			node cur = q.poll();
			v[cur.n] = true;
			if (cur.n == K) {
//				if (result < cur.sec) return;
				result = cur.sec;
				return;
			}

			add(cur.n - 1, cur.sec + 1); // 뒤로 한칸
			add(cur.n + 1, cur.sec + 1); // 앞으로 한칸
			add(cur.n * 2, cur.sec); // 순간이동

		}
	}

	private static void add(int n, int sec) {
		if (n >= 0 && n <= 100000) {
			if (!v[n]) {
				q.add(new node(n, sec));
			}
		}
	}
}