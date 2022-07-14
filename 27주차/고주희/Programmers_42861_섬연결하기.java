import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        Arrays.sort(costs, new Comparator<int[]>() {
			    @Override
				public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[2], o2[2]);
		        }
        });
        int answer = 0;
        for(int[] cost : costs) {
            if(union(cost[0], cost[1], parent)) {
                answer += cost[2];  
            }
        }
                    
        return answer;
    }
                    
    static public boolean union(int a, int b, int[] parent) {
        int pa = find(a, parent);
        int pb = find(b, parent);
        if(pa==pb) return false;
        else {
            parent[pa] = pb;
            return true;
        }
    }
    
    static public int find(int a, int[] parent) {
        if(parent[a]==a) return a;
        else return parent[a] = find(parent[a], parent);
    }
}
