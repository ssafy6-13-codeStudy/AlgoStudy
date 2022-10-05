import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int sr;
        int sc;
        int er;
        int ec;
        int type;
        
        public Node(int sr, int sc, int er, int ec, int type) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
            this.type = type;
        }
        
        @Override
        public int compareTo(Node o) {
            int n = this.sc - o.sc;
            if(n != 0) return n;
            n = this.sr - o.sr;
            if(n != 0) return n;
            return this.type - o.type;
        }
    }
    public int[][] solution(int n, int[][] build_frame) {
        
        List<Node> list = new ArrayList<>();
        List<Node>[][] map = new ArrayList[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for(int[] build : build_frame) {
            int r = build[1];
            int c = build[0];
            int type = build[2]; // 0은 기둥, 1은 보
            int del = build[3]; // 0은 삭제, 1은 삽입
            int er = r - 1;
            int ec = c;
            if(type == 1) {
                er = r;
                ec = c + 1;
            }
            Node node = new Node(r, c, er, ec, type);
            if(del == 1) {
                if(check(node, map)) {
                    // list.add(node);
                    map[r][c].add(node);
                }
            } else {
                for(Node cur : map[r][c]) {
                    if(cur.type == type) {
                        map[r][c].remove(cur);
                        break;
                    }
                }
                boolean flag = true;
                outer : for (int i = 0; i <= n; i++) {
                    for (int j = 0; j <= n; j++) {
                        for (Node cur : map[i][j]) {
                            if(!check(cur, map)) {
                                flag = false;
                                break outer;
                            }
                            // if(!check(cur, map)) {
                            //     System.out.println(node.sr+ " "+node.sc+" 삭제에");
                            //     System.out.println(cur.sr + " " + cur.sc + " : type "+cur.type);
                            // }
                        }
                    }
                }
                if(!flag) map[r][c].add(node);
                
            }
        }
        for(int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (Node node : map[i][j]) {
                    list.add(node);
                }
            }
        }
        list.sort(null);
        int[][] answer = new int[list.size()][3];
        int o = 0;
        for(Node node : list) {
            answer[o][0] = node.sc;
            answer[o][1] = node.sr;
            answer[o][2] = node.type;
            o++;
        }
        return answer;
    }
    
    static public boolean check(Node node, List<Node>[][] map) {
        
        int N = map.length;
        if(node.type == 0) {
            // 기둥인 경우
            if(node.sr == 0) return true;
            for(Node cur : map[node.sr][node.sc]) {
                if(cur.type == 1) return true;
            }
            if(node.sc-1 >= 0) {
                for(Node cur : map[node.sr][node.sc-1]) {
                    if(cur.type == 1) return true;
                }
            }
            for(Node cur : map[node.sr-1][node.sc]) {
                if(cur.type == 0) return true;
            }
            return false;
        } else {
            // 보 인 경우
            for (Node cur : map[node.sr-1][node.sc]) {
                if(cur.type == 0) return true;
            }
            for (Node cur : map[node.er-1][node.ec]) {
                if(cur.type == 0) return true;
            }
            
            if(node.sc - 1 >= 0 && node.sc + 1 < N) {
                int flag = 0;
                for (Node cur : map[node.sr][node.sc - 1]) {
                    if(cur.type == 1) {
                        flag++;
                        break;
                    } 
                }
                for (Node cur : map[node.sr][node.sc + 1]) {
                    if(cur.type == 1) {
                        flag++;
                        break;
                    } 
                }
                if(flag == 2) return true;
            }
            return false;
        }
    }
}
/*
+4
테스트 1 〉	통과 (0.92ms, 69.6MB)
테스트 2 〉	통과 (1.04ms, 79.6MB)
테스트 3 〉	통과 (0.74ms, 73MB)
테스트 4 〉	통과 (1.09ms, 86.6MB)
테스트 5 〉	통과 (0.96ms, 79.5MB)
테스트 6 〉	통과 (1.64ms, 77.5MB)
테스트 7 〉	통과 (0.72ms, 82.7MB)
테스트 8 〉	통과 (1.49ms, 78.6MB)
테스트 9 〉	통과 (0.98ms, 73.3MB)
테스트 10 〉	통과 (10.37ms, 99.3MB)
테스트 11 〉	통과 (23.56ms, 95.8MB)
테스트 12 〉	통과 (29.09ms, 93.6MB)
테스트 13 〉	통과 (119.56ms, 119MB)
테스트 14 〉	통과 (16.13ms, 87.3MB)
테스트 15 〉	통과 (19.20ms, 92.7MB)
테스트 16 〉	통과 (27.37ms, 108MB)
테스트 17 〉	통과 (50.38ms, 111MB)
테스트 18 〉	통과 (47.39ms, 101MB)
테스트 19 〉	통과 (78.00ms, 118MB)
테스트 20 〉	통과 (99.48ms, 116MB)
테스트 21 〉	통과 (55.18ms, 107MB)
테스트 22 〉	통과 (45.93ms, 103MB)
테스트 23 〉	통과 (77.50ms, 113MB)
*/
