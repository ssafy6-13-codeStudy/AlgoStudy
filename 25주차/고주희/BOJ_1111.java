import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 : 76ms
// 메모리 : 11560KB
public class Main {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		if(N <= 2) {
			if(N==2 && num[0]==num[1]) System.out.println(num[0]);
			else System.out.println("A");
			return;
		}
		int a = 0;
		int b = 0;
		if(num[2]-num[1] == 0 && num[1]-num[0] == 0) {
			a = 1;
			b = 0;
		} else if(num[1]-num[0] == 0) {
			a = 0;
			b = num[1] - num[0] * a;
		} else {
			a = (num[2]-num[1])/(num[1]-num[0]);
			b = num[1] - num[0] * a;
		}
		
		for (int i = 1; i < N; i++) {
			if(num[i-1]*a+b!=num[i]) {
				System.out.println("B");
				return;
			}
		}
		System.out.println(num[N-1]*a+b);
	}

}
