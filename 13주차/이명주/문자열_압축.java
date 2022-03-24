import java.io.*;
import java.util.*;

class Solution {
  public int solution(String s) {
    int answer = s.length();

    for (int i = 1; i <= s.length() / 2; i++) {

      String result = "";
      String pre = s.substring(0, i);
      int cnt = 1;

      for (int j = i; j <= s.length() - i; j += i) {

        String tmp = s.substring(j, j + i);

        if (tmp.equals(pre)) {
          cnt++;
        } else {
          if (cnt > 1)
            result += cnt + "";

          result += pre;
          pre = tmp;
          cnt = 1;
        }
      }

      if (cnt > 1)
        result += cnt + "";

      result += pre;
      answer = Math.min(answer, result.length() + s.length() % i);

    }
    return answer;
  }
}