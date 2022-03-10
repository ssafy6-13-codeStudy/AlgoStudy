import java.util.*;

class Solution {
  static boolean[][] visit;

  public int solution(int n, int[][] computers) {
    visit = new boolean[n][n];
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      if (!visit[i][i]) {
        bfs(i, n, computers);
        cnt++;
      }
    }

    return cnt;
  }

  public void bfs(int start, int n, int[][] computers) {
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    visit[start][start] = true;
    while (!q.isEmpty()) {
      int temp = q.poll();
      for (int i = 0; i < n; i++) {
        if (visit[temp][i])
          continue;
        if (computers[temp][i] == 1) {
          q.add(i);
          visit[temp][i] = true;
          visit[i][temp] = true;
        }
      }
    }
  }
}