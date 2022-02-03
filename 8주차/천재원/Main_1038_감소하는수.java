// 116ms
// 13060kb

import java.util.*;


public class Main_1038_감소하는수 {
	static int idx = 0;
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) {
	// 0 1 2 3 4 5 6 7 8 9
	// 10
	// 21 20
	// 32 31 30
	// 40 41 42 43
	// 50 51 52 53 54
	// 60 61 62 63 64 65
	// 70 71 72 73 74 75 76
	// 80 81 82 83 84 85 86 87
	// 90 91 92 93 94 95 96 97 98
		// 1 의자리 -> 9개 , 10의자리 -> 45개, 100의자리 
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i=0; i<10; i++) {
			num(i,1);
		}
		Collections.sort(list);
		System.out.println(N > 1022 ? -1 : list.get(N));
	}
	private static void num(long i, int idx) {
		if(idx > 10) return;
		
		list.add(i);
		for (int j = 0; j < i%10; j++) {
			num((i*10)+j, idx +1);
		}
		
	}
}
