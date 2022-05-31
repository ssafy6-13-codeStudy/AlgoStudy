import java.io.*;
import java.util.*;

/*
    오큰수
    골드 4
    시간 : 1044ms
    메모리 : 202876kb
*/

public class Main {

    /*
        오른쪽에 있으면서 A보다 큰 수 중에 가장 왼쪽에 있는 수
    */

    static int[] nums;
    static int[] result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        nums = new int[N];
        result = new int[N];
        Arrays.fill(result, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            // 스택이 안비고 현재 숫자가 스택 맨 위 숫자보다 크면 pop하기
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }

            stack.push(i);
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb.toString());
    }


}
