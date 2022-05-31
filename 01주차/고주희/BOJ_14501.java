
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14501 {

	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
    // 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// work[i][0]는 소요일수, work[i][1]는 금액
    int[][] work = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			work[i][0] = Integer.parseInt(st.nextToken());
			work[i][1] = Integer.parseInt(st.nextToken());
      // 시작일+소요일수가 일할 날보다 크면 저장하지 않음
			if(work[i][0]+i>N) {
				work[i][0] = work[i][1] = -1;
			}
		}
		max = Integer.MIN_VALUE;
    // 부분집합으로 구하기
		subset(work, 0, new boolean[N]);
		System.out.println(max);
	}

	private static void subset(int[][] work, int cnt, boolean[] v) {

		int N = work.length;
		//부분집합 다 구한 경우
    if(cnt==N) {
      // 일하는 날 저장하는 boolean 배열
			boolean[] havework = new boolean[N];
			int paid = 0; // 받을 금액
			for (int i = 0; i < N; i++) {
        // 일하는 날이면
				if(v[i]) {
          // 만일 이미 그 날 일정이 있으면 바로 리턴
					if(havework[i]) return;
          // 일정이 없는 경우 당일+소요일수-1까지 일정 잡기
					int endday = i+work[i][0];
					for (int j = i; j < endday; j++) {
						havework[j] = true;
					}
          // 금액 추가
					paid+=work[i][1];
				}
			}
      // 최댓값인지 비교
			max = Math.max(max, paid);
			return;
		}
		// 부분집합 구하기
		v[cnt] = true;
		subset(work, cnt+1, v);
		v[cnt] = false;
		subset(work, cnt+1, v);
	}



}
