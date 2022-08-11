import java.util.*;
class Solution {
    static int hole;
    public boolean solution(int[][] key, int[][] lock) {
        hole = 0;
        for(int[] is : lock) {
            for(int i : is) {
                if(i==0) hole++;
            }
        }
        for(int i = 0; i < 4; i++) {
            if(check(key, lock)) return true;
            key = turnKey(key);
            for(int[] is : key) {
                System.out.println(Arrays.toString(is));
            }
            System.out.println();
        }
        return false;
    }
    
    public int[][] turnKey(int[][] key) {
        int N = key.length;
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[j][N-1-i] = key[i][j];
            }
        }
        return map;
    }
    
    public boolean check(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        for(int i = -N; i < N; i++) {
            for(int j = -N; j < N; j++) {
                int keyhole = 0;
                boolean smallFlag = true;
                for(int r = 0; r < M; r++) {
                    for(int c = 0; c < M ; c++) {
                        if(i+r < 0 || j + c < 0 || i + r >= N || j + c >= N) continue;
                        if(key[r][c] == lock[i+r][j+c]) {
                            smallFlag = false;
                            break;
                        } else {
                            if(lock[i+r][j+c]==0) keyhole++;
                        }
                    }
                    if(!smallFlag) break;
                }
                if(smallFlag && keyhole==hole) return true;
            }
        }
        return false;
    }
}
