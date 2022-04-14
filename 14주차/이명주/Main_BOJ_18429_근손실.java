import java.util.*;
import java.io.*;

/*
    근손실
    시간 : 88ms
    메모리 : 11760kb
*/

public class Main {

    static int N, K;
    static int count=0;
    static int weight;
    static int[] kit;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N =Integer.parseInt(st.nextToken());
        K =Integer.parseInt(st.nextToken());

        v =new boolean[N];
        kit=new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< N; i++)
            kit[i]=Integer.parseInt(st.nextToken());

        perm(0,500);
        System.out.println(count);
    }

    static void perm(int cnt, int weight) {

        if(cnt== N) {
            count+=1;
            return;
        }


        for(int i = 0; i< N; i++) {

            if(!v[i]&&(weight- K +kit[i])>=500) {

                v[i]=true;
                perm(cnt+1,weight- K +kit[i]);
                v[i]=false;
            }
        }
    }
}