import java.util.*;

class Solution {
  public int[] parent;
    
  public int solution(int n, int[][] costs) {
    int answer = 0, i, j;
    parent = new int[n];
    for (i = 0; i < parent.length; parent[i] = i++)
      ;
    Arrays.sort(costs, new Comparator<>() {
      @Override
      public int compare(int[] a, int[] b) {
        if (a[2] > b[2])
          return 1;
        else
          return -1;
      }
    });
    for (i = 0; i < costs.length; i++) {
      if (find(costs[i][0]) == find(costs[i][1]))
        continue;
      union(costs[i][0], costs[i][1]);
      answer += costs[i][2];
    }
    return answer;
  }

  public int find(int a) {
    if (a == parent[a])
      return parent[a];
    else
      return parent[a] = find(parent[a]);
  }
    
  public void union(int a, int b) {
    a = find(a);
    b = find(b);
    if (a > b)
      parent[a] = b;
    else
      parent[b] = a;
  }
    
}