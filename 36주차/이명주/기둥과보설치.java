import java.util.*;

// 기둥은 바닥 위 or 보의 한쪽 끝 or 다른 기둥 위
// 보는 한쪽 끝이 기둥 위 or 양쪽 끝이 다른 보와 연결

// x,y(설치할 좌표) a(0 기둥 1 보) b(0 삭제, 1 설치)
// 보는 오른쪽, 기둥은 위쪽으로 설치

// x,y,a : 좌표 + (0 기둥 1 보)
// 같은 좌표일 때 기둥이 보보다 앞으로
class Solution {
    
    static ArrayList<Frame> frames;
    static boolean[][] bo;
    static boolean[][] ki;
    static int N;
    public static class Frame implements Comparable<Frame> {
        int x;
        int y;
        int a;
        
        public Frame(int x,int y,int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }
        
        @Override
        public int compareTo(Frame o) {
            if(o.x!=this.x) return this.x-o.x;
            else if(o.y!=this.y) return this.y - o.y;
            else return this.a-o.a;
        }
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        bo = new boolean[n+1][n+1];
        ki = new boolean[n+1][n+1];
        N = n;
        frames = new ArrayList<>();
        
        for(int[] arr: build_frame) {
            int x = arr[0];
            int y = arr[1];
            
            //기둥
            if(arr[2]==0) {
                if(arr[3]==0) remove(x,y,0);
                else {
                    if(buildK(x,y)) ki[x][y] = true;
                }
            } 
            else { //보
                if(arr[3]==0) remove(x,y,1);
                else {
                    if(buildB(x,y)) bo[x][y] = true;
                }
            }
        }
        
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                if(bo[i][j]) {
                    frames.add(new Frame(i,j,1));
                }
                if(ki[i][j])
                    frames.add(new Frame(i,j,0));
            }
        }
        
        Collections.sort(frames);
        int[][] answer = new int[frames.size()][3];
        
        for(int i=0; i<answer.length; i++) {
            Frame f = frames.get(i);
            answer[i][0] = f.x;
            answer[i][1] = f.y;
            answer[i][2] = f.a;
        }
        
        return answer;
    }
    
    // 기둥 짓기 가능?
    public static boolean buildK(int x, int y) {
        // 바닥
        if(y==0) return true;
        // 기둥 위
        if(check(x,y-1) && ki[x][y-1]) return true;
        // 보 끝
        if((check(x-1,y) && bo[x-1][y]) || (check(x+1,y) && bo[x+1][y]))
            return true;
        
        return false;
    }
    
    public static void remove(int x,int y,int type) {
        if(type==0) ki[x][y] = false;
        else bo[x][y] = false;
        
        boolean b = true;
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                if(bo[i][j] && !buildB(i,j)) {
                    b = false;
                    break;
                }
                if(ki[i][j] && !buildK(i,j)){
                    b = false;
                    break;
                }
            }
        }
        if(!b) {
            if(type == 0) ki[x][y] = true;
            else bo[x][y] = true;
        }
    }
    
    // 보 짓기 가능?
    public static boolean buildB(int x,int y) {
        // 한쪽 끝이 기둥 위
        if((check(x+1,y-1) && ki[x+1][y-1]) || (check(x,y-1) && ki[x][y-1]))
            return true;
        // 양쪽 끝이 보
        if((check(x-1,y) && bo[x-1][y]) && (check(x+1,y) && bo[x+1][y]))
            return true;
        
        return false;
    }
    
    public static boolean check(int x,int y) {
        return x>=0 && x<=N && y>=0 && y<=N;
    }
    
}