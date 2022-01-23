import java.io.*;
import java.util.*;

/*
    Contact
    골드 5
    시간 : 144ms
    메모리 : 14848kb
*/

public class Main {

    /*
    + 반복
    | 혹은
    */

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        String regex= "(100+1+|01)+";

        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            if(s.matches(regex)) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
