import java.io.*;
import java.util.*;

/*
    종이자르기
    시간 : 76ms
    메모리 : 11660kb
*/

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(br.readLine());

        LinkedList<Integer> listx = new LinkedList<>();
        LinkedList<Integer> listy = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int path = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            if (path == 0) {
                listx.add(point);
            }
            if (path == 1) {
                listy.add(point);
            }
        }

        listx.add(h);
        listx.add(0);
        listy.add(w);
        listy.add(0);

        Collections.sort(listx);
        Collections.sort(listy);

        int x = 0;
        // x의 최대값 구하기
        for (int i = listx.size() - 1; i > 0; i--) {
            int temp = listx.get(i) - listx.get(i - 1);
            if (x < temp) {
                x = temp;
            }
        }

        int y = 0;
        // y의 최대값 구하기
        for (int i = listy.size() - 1; i > 0; i--) {
            int temp = listy.get(i) - listy.get(i - 1);
            if (y < temp) {
                y = temp;
            }
        }

        System.out.println(x * y);
    }

}