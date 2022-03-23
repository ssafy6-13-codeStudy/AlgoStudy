import java.io.*;
import java.util.*;

/*
    크게 만들기
    시간 : 360ms
    메모리 : 40536kb
*/
public class Main {

  static int N, K;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    String num = br.readLine();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < N; i++) {
      char n = num.charAt(i);

      // 안비었다면 비교
      while (K != 0 && !stack.isEmpty()) {
        char pre = stack.peek();
        if (pre < n) {
          stack.pop();
          K--;
        } else {
          break;
        }
      }

      stack.push(n);

    }

    for (int i = 0; i < K; i++) {
      stack.pop();
    }

    for (int i = 0; i < stack.size(); i++) {
      sb.append(stack.get(i));
    }
    System.out.println(sb);
  }
}
