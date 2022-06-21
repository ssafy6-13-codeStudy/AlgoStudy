import java.io.*;
import java.util.*;

public class Main {

    /*
    배
    골드 5
    시간: 356ms
    메모리: 15108kb
     */
    static int N,M;
    static ArrayList<Integer> crane = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }
        crane.sort(Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        box.sort(Collections.reverseOrder());

        if(box.get(0) > crane.get(0)) System.out.println(-1);
        else System.out.println(move());
    }

    private static int move() {
        int level = 0;

        while (!box.isEmpty()) {
            int idx=0;
            for (int i = 0; i < crane.size(); i++) {
                if(idx==box.size())
                    break;
                else if(crane.get(i)>=box.get(idx))
                    box.remove(idx);
                else {
                    idx++;
                    i--;
                }
            }
            level++;
        }
        return level;
    }

}
