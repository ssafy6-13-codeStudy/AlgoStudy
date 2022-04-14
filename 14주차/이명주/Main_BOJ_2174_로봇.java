import java.util.*;
import java.io.*;

/*
    로봇
    시간 : 92ms
    메모리 : 13092kb
*/

public class Main {

    static int A,B,N,M;
    static int [][] map;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    static ArrayList<robot> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken()); //가로
        B = Integer.parseInt(st.nextToken()); //세로

        map = new int[B][A];

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //로봇수
        M = Integer.parseInt(st.nextToken()); // 명령수

        int num=0;

        // 로봇 입력 받기
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            String dir = st.nextToken();

            map[B-y-1][x] = i;
            if(dir.equals("N")) num=0;
            else if(dir.equals("E")) num=1;
            else if(dir.equals("S")) num=2;
            else if(dir.equals("W")) num=3;

            list.add(new robot(i,B-y-1,x,num));
        }

        // 명령 입력
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int robot = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int repetition = Integer.parseInt(st.nextToken());
            move(robot,command,repetition);
        }

        System.out.println("OK");

    }

    public static void move(int robot, String command, int repetition) {

        for(int i=0;i<list.size();i++) {
            if(list.get(i).num==robot) { //움직일 로봇
                int direction = list.get(i).dir;
                if (command.equals("L")) { //왼쪽으로 90도
                    for(int j=0;j<repetition;j++) {
                        if(direction==0) direction=3;
                        else direction--;
                    }
                    list.set(i, new robot(list.get(i).num,list.get(i).x,list.get(i).y,direction));
                }else if(command.equals("R")) { //오른쪽으로 90도
                    for(int j=0;j<repetition;j++) {
                        if(direction==3) direction=0;
                        else direction++;
                    }
                    list.set(i, new robot(list.get(i).num,list.get(i).x,list.get(i).y,direction));
                }else if(command.equals("F")) { //현재 방향에서 한칸 앞으로
                    int x = list.get(i).x;
                    int y = list.get(i).y;
                    map[x][y] = 0;
                    int nx=0, ny=0;
                    for(int j=0;j<repetition;j++) {
                        nx = x+dx[direction];
                        ny = y+dy[direction];

                        if(check(nx,ny)) {
                            // 비어 있으면 앞으로
                            if(map[nx][ny]==0) {
                                x = nx;
                                y = ny;
                            }else {
                                System.out.println("Robot "+list.get(i).num+" crashes into robot "+map[nx][ny]);
                                System.exit(0);
                            }
                        }else {
                            System.out.println("Robot "+list.get(i).num+" crashes into the wall");
                            System.exit(0);
                        }
                    }
                    map[nx][ny] = list.get(i).num;
                    list.set(i, new robot(list.get(i).num,nx,ny,direction));
                }
            }
        }

    }

    static class robot{
        int num ,x, y, dir;

        public robot(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static boolean check(int x, int y) {
        return x>=0 && x<B && y>=0 && y<A;
    }
}