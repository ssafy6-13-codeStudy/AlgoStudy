import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static ArrayList<int []> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N;i++) {
            arr[i] = stoi(st.nextToken());
        }

        if(N==1 || (N==2 && arr[0]!=arr[1])) {
            System.out.println("A");
            System.exit(0);
        }else if(N==2) {
            System.out.println(arr[0]);
            return;
        }

        int a,b;
        if(arr[0]==arr[1]) {
            a = 1;
            b = 0;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - a*arr[0];
        }

        if(check(a,b))
            System.out.println(arr[N-1]*a+b);
        else
            System.out.println("B");

    }

    public static boolean check(int a,int b) {
        for (int i = 1; i < N-1; i++) {
            if(arr[i+1] != (arr[i]*a+b))
                return false;
        }

        return true;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

}