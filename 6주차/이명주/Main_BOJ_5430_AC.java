import java.io.*;
import java.util.*;

/*
    AC
    골드 5
    시간 : ms
    메모리 : kb
    틀림
*/

public class Main {

    static StringBuilder sb = new StringBuilder();
    static String order;
    static int n;
    static boolean isReverse = false;
    static Deque<Integer> deque;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        outer: for (int t = 0; t < T; t++) {
            isReverse = false; // 역순인지 확인
            deque = new LinkedList<>(); 
            order = br.readLine();
            n = Integer.parseInt(br.readLine());

            String arr = br.readLine();
            arr = arr.substring(1, arr.length() - 1);
            StringTokenizer st = new StringTokenizer(arr, ",");

            //덱에 입력받은 숫자 입력
            for (int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            
            for (char c : order.toCharArray()) {
                //역순
                if (c == 'R') {
                    isReverse = !isReverse;
                } else { // D일 경우 역순이면 마지막 숫자 제거
                    if (deque.isEmpty()) {
                        sb.append("error\n");
                        continue outer;
                    }
                    if (isReverse)
                        deque.removeLast();
                    else deque.removeFirst();
                }
            }

            sb.append("[");

            while(!deque.isEmpty()){
              //역순일시 마지막 노드부터 sb에 추가 
                if(isReverse){
                    sb.append(deque.removeLast()).append(",");
                } else
                    sb.append(deque.removeFirst()).append(",");
            }
            if(n>1) sb.deleteCharAt(sb.length()-1); //마지막 , 제거

            sb.append("]\n");

        }

        System.out.println(sb.toString());

    }
}