import java.util.*;
import java.io.*;

/*
    싸지방에 간 준하
    골드 3
    시간 : 760ms
    메모리 : 60765kb
*/

public class Main {

    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Computer> computers = new PriorityQueue<>(new MyComparator());
    static PriorityQueue<Computer> emptylist = new PriorityQueue<>(new Comparator<Computer>() {
        @Override
        public int compare(Computer o1, Computer o2) {
            return o1.index-o2.index;
        }
    });
    static ArrayList<Person> people = new ArrayList<>();
    static int[] count = new int[1000001];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            people.add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.start - o2.start;
            }
        });

        computers.add(new Computer(1, people.get(0).end));
        count[1]=1;
        int num = 1;

        for (int i = 1; i < N; i++) {
            Person person = people.get(i);

            while(!computers.isEmpty()){
                Computer computer = computers.peek();
                if(computer.end<=person.start)
                    emptylist.add(computers.poll());
                else break;
            }

            if (!emptylist.isEmpty()){
                Computer computer = emptylist.poll();
                computers.add(new Computer(computer.index,person.end));
                count[computer.index]++;
            }
            else {
                computers.add(new Computer(++num, person.end));
                count[num]++;
            }

        }

        sb.append(num).append("\n");

        for (int i = 1; i <= num; i++) {
            sb.append(count[i]).append(" ");
        }

        System.out.println(sb);
    }

    public static class Person {
        int start;
        int end;

        public Person(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class Computer {
        int index;
        int end;

        public Computer(int index, int end) {
            this.index = index;
            this.end = end;
        }
    }

    public static class MyComparator implements Comparator<Computer> {

        @Override
        public int compare(Computer o1, Computer o2) {
            return o1.end-o2.end;
        }
    }

}