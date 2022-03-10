class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j]==1){
                    union(i, j, parent, rank);
                }
            }
        }
        for(int i=0; i<n; i++){
            parent[i] = find(i, parent);
        }
        boolean[] p = new boolean[n];
        
        for(int i : parent){
            p[i] = true;
        }
        for(boolean i : p){
            if(i) answer++;
        }
        return answer;
    }
    
    public static void union(int x, int y, int[] parent, int[] rank){
        int px = find(x, parent);
        int py = find(y, parent);
        
        if(px==py) return;
        if(rank[px]>=rank[py]){
            parent[py] = px;
            rank[px]++;
        }else{
            parent[px] = py;
            rank[py]++;
        }
        
    }
    public static int find(int x, int[] parent){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x], parent);
    }
}
