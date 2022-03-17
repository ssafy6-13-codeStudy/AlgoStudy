import java.io.*;
import java.util.*;

/*
    신입 사원
    시간 : 1736ms
    메모리 : 301436kb
*/

public class Main {

    static ArrayList<Person> array;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            array = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                array.add(new Person(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));

            }

            array.sort(new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.score - o2.score;
                }
            });

            int answer = 1;

            int min = array.get(0).rank;

            for (int i = 1; i < N; i++) {
                if(array.get(i).rank < min){
                    answer++;
                    min = array.get(i).rank;
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static class Person {
        int score;
        int rank;

        public Person(int score, int rank) {
            this.score = score;
            this.rank = rank;
        }
    }
}