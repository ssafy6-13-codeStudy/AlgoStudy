import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 376ms
// 메모리 : 24468KB

public class BOJ_20055 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
    // 입력받기
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
    // 총 컨테이터 길이
		int n = 2*N;
		
		int[] con = new int[n]; // 내구도
		boolean[] ex = new boolean[n]; // 로봇 위치
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			con[i] = Integer.parseInt(st.nextToken());
		}
		
		int stop = 0; // 내구도가 0이 된 컨테이너 세는 변수
		int start = 0; // 올라가는 곳
		int end = N-1; // 내려가는 곳
		int robot = 1; // 몇 번째?
		// 벨트 위 로봇 리스트
		List<int[]> list = new ArrayList<>();
		
		while(true) {
      // 올라가는 곳과 내려가는 곳 위치 변경
			start--;
			if(start==-1) start = n-1;
			end--;
			if(end==-1) end = n-1;
      // 벨트 위 로봇 움직이기
			for (int[] is : list) {
        // 로봇이 다음에 갈 곳
				int next = is[0]+1;
				if(next==n) next = 0;
        // 벨트가 움직여서 움직이기 전에 내려야하거나, 조건이 다 되어서 움직인 다음에 내리는 곳이면
				if((con[next]!=0 && !ex[next] && next==end) || is[0]==end) {
          // 로봇 존재 지워주고
					ex[is[0]] = false;
          // 이따 지우도록 표시
					is[0] = -1;
          // 움직여서 내리는 곳이면 내구도 줄여
					if(next==end && con[next]!=0) {
						con[next]--;
            // 내구도 줄였는데 0이면 stop 올려줌
						if(con[next]==0) stop++;
					}
					
				}
        // 다음에 움직일 곳이 내구도가 남아있고 로봇도 없으면
				else if(con[next]>=1 && !ex[next]) {
          // 기존 자리에 있던 로봇 존재 지우고
					ex[is[0]] = false;
          // 위치 변경
					is[0] = next;
          // 새로운 위치에 로봇 존재 넣어주고
					ex[next] = true;
          // 새로운 위치 내구도 줄임
					con[next]--;
          // 줄인 내구도가 0이면 stop올려줌
					if(con[next]==0) stop++;
				}
			}
      // 아까 지워야하는 거 -1로 체크해둔 거 지우기
			int len = list.size();
			for (int i = len-1; i >=0; i--) {
				if(list.get(i)[0]==-1) {
					list.remove(i);
				}
				
			}
			// 올리는 위치 내구도가 남아있고 로봇이 없으면
			if(con[start]!=0 && !ex[start]) {
        // 로봇을 리스트에 넣어줌
				list.add(new int[] {start, robot});
        // 내구도 줄여줌
				con[start]--;
        // 줄인 내구도가 0이면 stop 올려줌
				if(con[start]==0) stop++;
        // 로봇 존재 체크
				ex[start] = true;
			}
			// stop이 K개 이상이면 멈추기
			if(stop>=K) break;
      // 단계+1
			robot++;
			
		}
		
		System.out.println(robot);
	}

}
