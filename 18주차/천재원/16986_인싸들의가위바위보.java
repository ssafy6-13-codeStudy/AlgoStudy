import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16986_인싸들의가위바위보_골드3 {
	static int N, K;
	static int versus[][];
	static Queue<Integer> KyungHee, MinHo;
	static Map<Integer, List<Integer>> winMap = new HashMap<>();
	static final int DRAW = 1, WIN = 2, LOSE =3;
	static BufferedReader br;
	static StringTokenizer st ;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		versus = new int[N][N];
		KyungHee = new LinkedList<>();
		MinHo = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int vs = Integer.parseInt(st.nextToken());
				versus[i][j] = vs;
				if(vs == 2) {
					if(winMap.get(j) == null) {
						winMap.put(j, new ArrayList<>());
					}
					winMap.get(j).add(i);
				}
			}
		}
		
		init(KyungHee);
		init(MinHo);
		
		// 순서 : AB BC CA CB BA AC
		// 지우경희, 경희민호, 민호지우, 민호경희, 경희지우, 지우민호
		backtracking(new int[3],0, new boolean[N]);
		System.out.println("0");
	}

	// 지우 0 경희 1 민호 2
	private static void backtracking(int[] cnt, int time, boolean[] visited) {
		
		// 실패
		if(cnt[1] == K || cnt[2] == K || time == 60) {
			return;
		}
		
		// 성공
		if(cnt[0] == K) {
			System.out.println("1");
			System.exit(0);
			return;
		}
		
		switch(time%6) {
			case 0 :{ // 지우경희
				int kh = KyungHee.poll();
				if(winMap.get(kh) == null) {
					if(!visited[kh]) {
						cnt[0] +=1;
						visited[kh] = true;
						backtracking(cnt, time+1, visited);
						visited[kh] = false;
						cnt[0] -=1;
					}else {
						cnt[0] +=1;
						visited[kh] = true;
						backtracking(cnt, time+1, visited);
						visited[kh] = false;
						cnt[0] -=1;
					}
				}
				for(int jiwoo : winMap.get(kh)) {
					if(vs(jiwoo, kh, visited, false)) {
						cnt[0] +=1;
						visited[kh] = true;
						backtracking(cnt, time+1, visited);
						visited[kh] = false;
						cnt[0] -=1;
					}
				}
			}case 1 :{ // 경희민호
				int kh = KyungHee.poll();
				int mh = MinHo.poll();
				if(versus[kh][mh] == WIN) { // 경희 승
					cnt[1] +=1;
					backtracking(cnt, time+1, visited);
				}else { // 비김 || 민호 승
					cnt[2] +=1;
					backtracking(cnt, time+1, visited);
				}
			}case 2 :{ // 민호지우  
				int mh = MinHo.poll();
				for(int jiwoo : winMap.get(mh)) {
					if(vs(jiwoo, mh, visited, true)) {
						cnt[0] +=1;
						visited[mh] = true;
						backtracking(cnt, time+1, visited);
						visited[mh] = false;
						cnt[0] -=1;
					}
				}
			}case 3 :{ // 민호경희
				int kh = KyungHee.poll();
				int mh = MinHo.poll();
				if(versus[mh][kh] == WIN) { // 민호 승
					cnt[2] +=1;
					backtracking(cnt, time+1, visited);
				}else { // 비김 || 경희 승
					cnt[1] +=1;
					backtracking(cnt, time+1, visited);
				}
			}case 4 :{ // 경희지우
				int kh = MinHo.poll();
				for(int jiwoo : winMap.get(kh)) {
					if(vs(jiwoo, kh, visited, true)) {
						cnt[0] +=1;
						visited[kh] = true;
						backtracking(cnt, time+1, visited);
						visited[kh] = false;
						cnt[0] -=1;
					}
				}
			}case 5 :{ // 지우민호
				int mh = KyungHee.poll();
				for(int jiwoo : winMap.get(mh)) {
					if(vs(jiwoo, mh, visited, false)) {
						cnt[0] +=1;
						visited[mh] = true;
						backtracking(cnt, time+1, visited);
						visited[mh] = false;
						cnt[0] -=1;
					}
				}
			}
		}
	}

	private static boolean vs(int jiwoo, int enemy, boolean[] visited, boolean flag) {
		if(flag && !visited[enemy]) {
			return true;
		}
		else if(!visited[jiwoo]) {
			return true;
		}
		return false;
	}

	private static void init(Queue<Integer> kyungHee2) throws IOException{
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<20; i++) {
			kyungHee2.offer(Integer.parseInt(st.nextToken())-1);
		}
	}
}
