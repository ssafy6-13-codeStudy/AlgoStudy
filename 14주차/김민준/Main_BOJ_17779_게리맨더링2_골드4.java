package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간 ms
//메모리 kb
public class Main_BOJ_17779_게리맨더링2_골드4 {
	static int N;
	static int[][] map;
	static int total_min;
	static int sum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sum = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
			}
		}
		
		// d1, d2 >= 1, 0 <= x < x + d1 + d2 < N, 0 <= y - d1 < y < y +d2 
		total_min = Integer.MAX_VALUE;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if(x + d1 + d2 >= N) break;
						if(y - d1 < 0) break;
						if(y + d2 >= N) break;
						
						cal(x,y,d1,d2);
					}
				}
			}
		}
		System.out.println(total_min);
	}

	private static void cal(int x, int y, int d1, int d2) {
		int[] sumlist = new int[5];
        boolean[][] check = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            check[x + i][y - i] = true;
            check[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            check[x + i][y + i] = true;
            check[x + d1 + i][y - d1 + i] = true;
        }


        // 1지구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (check[i][j]) break;
                sumlist[0] += map[i][j];
            }
        }

        // 2지구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (check[i][j]) break;
                sumlist[1] += map[i][j];
            }
        }

        // 3지구
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (check[i][j]) break;
                sumlist[2] += map[i][j];
            }
        }

        // 4지구
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (check[i][j]) break;
                sumlist[3] += map[i][j];
            }
        }

        // 5지구
        sumlist[4] = sum;

        for (int i = 0; i < 4; i++) {
            sumlist[4] -= sumlist[i];
        }

        Arrays.sort(sumlist);

        total_min = Math.min(total_min, sumlist[4] - sumlist[0]);
	}
}
