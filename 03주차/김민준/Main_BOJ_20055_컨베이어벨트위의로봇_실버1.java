package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 268ms
//메모리 16800kb
//코드길이 2389b

public class Main_BOJ_20055_컨베이어벨트위의로봇_실버1 {
	static int N, K;
	static int[] belt;
	static boolean[] robot;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2*N];
		robot = new boolean[2*N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		cal();
		System.out.println(count);
	}

	private static void cal() {
		while(true) {
			count++; // 단계
			// 1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다. -- 값 옮기는 순서 주의! 뒤에서부터 바꿔야 오른쪽 회전
			int temp = belt[2*N-1];
			for (int i = 2*N-1; i > 0; i--) {
				belt[i] = belt[i-1];
				robot[i] = robot[i-1];
			}
			belt[0] = temp;
			robot[0] = false; // 아래에서 올라오는 벨트라 로봇이 없음
			robot[N-1] = false; // 로봇 내려줌
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			for (int i = N-1; i > 0; i--) {
				if(robot[i-1]) { // 로봇이 있을 때
					// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
					if(!robot[i] && belt[i] >= 1) {
						//로봇 올리면 내구도 감소
						belt[i]--;
						robot[i] = true;
						robot[i-1] = false;
					}
				}
			}
			// 3.올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}
			
			// 4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int cnt = 0;
			for (int i = 0; i < 2*N; i++) {
				if(belt[i] == 0) {
					cnt++;
					if(cnt >= K) {
						return;
					}
				}
			}
		}
		
	}
	
}
