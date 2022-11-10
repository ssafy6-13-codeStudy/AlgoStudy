import java.util.*;
import java.io.*;

/*
11692kb
408ms
 */
class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (true) {
            int cnt = 0;
            int n = N;

            while(n!=0){
                if(n%2==1){
                    cnt+=1;
                }
                n /= 2;
            }

            if (cnt <= K) break;
            answer++;
            N++;
        }

        System.out.println(answer);
    }
}