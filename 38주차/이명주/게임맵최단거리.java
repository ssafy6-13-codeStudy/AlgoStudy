import java.util.*;

class Solution {
    static int R,C;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    
    public int solution(int[][] maps) {
        
        R = maps.length-1;
        C = maps[0].length-1;
        
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        int answer=1;
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{0,0});
        maps[0][0] = 0;
        
        while(!queue.isEmpty()) {
            
            int n = queue.size();
            
            for(int i=0; i<n; i++) {
                int[] now = queue.poll();
                
                if(now[0]==R && now[1]==C) return answer;
                
                for(int d=0; d<4; d++) {
                    int nr = now[0] + dr[d];
                    int nc = now[1] + dc[d];
                    
                    if(check(nr,nc) && maps[nr][nc]==1) {
                        maps[nr][nc] = 0;
                        queue.add(new int[]{nr,nc});
                    }
                }
            }
            answer++;
        }
        
        return -1;
    }
    
    public static boolean check(int r,int c) {
        return r>=0 && r<=R && c>=0 && c<=C;
    }
}

/*
+5
정확성  테스트
테스트 1 〉	통과 (0.14ms, 71.7MB)
테스트 2 〉	통과 (0.16ms, 98.9MB)
테스트 3 〉	통과 (0.19ms, 74.7MB)
테스트 4 〉	통과 (0.14ms, 66.7MB)
테스트 5 〉	통과 (0.12ms, 77.5MB)
테스트 6 〉	통과 (0.17ms, 77.6MB)
테스트 7 〉	통과 (0.26ms, 75.1MB)
테스트 8 〉	통과 (0.19ms, 73.6MB)
테스트 9 〉	통과 (0.23ms, 65.1MB)
테스트 10 〉	통과 (0.21ms, 72.5MB)
테스트 11 〉	통과 (0.20ms, 71.5MB)
테스트 12 〉	통과 (0.15ms, 70.4MB)
테스트 13 〉	통과 (0.19ms, 88MB)
테스트 14 〉	통과 (0.19ms, 71.6MB)
테스트 15 〉	통과 (0.14ms, 74.2MB)
테스트 16 〉	통과 (0.17ms, 72.2MB)
테스트 17 〉	통과 (0.19ms, 71.5MB)
테스트 18 〉	통과 (0.12ms, 69.3MB)
테스트 19 〉	통과 (0.15ms, 66.9MB)
테스트 20 〉	통과 (0.16ms, 79.5MB)
테스트 21 〉	통과 (0.18ms, 74.6MB)
효율성  테스트
테스트 1 〉	통과 (7.28ms, 52.7MB)
테스트 2 〉	통과 (2.64ms, 52.6MB)
테스트 3 〉	통과 (5.37ms, 52.5MB)
테스트 4 〉	통과 (4.83ms, 60.5MB)
 */