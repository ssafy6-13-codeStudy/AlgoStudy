import java.util.*;
import java.io.*;


// 1*1 1*2 2*1
// 빨간칸에 블록을 놓고 초록 파랑으로 쭉쭉 ㄲ
// 한 줄이 꽉차면 1점 획득 + 그 줄 초기화 + 그만큼 땡김
// 특별 칸에 블록이 있으면 블록이 있는 열의 수만큼 오른쪽 열에 있는 타일이 사라짐
// 사라진 열의 수만큼 이동
// 특별칸에 2열(행)만큼 타일이 있으면 그만큼 끝부터 사라진다. 그리고 땡긴다.
// 동시에 일어나면 점수 획득 후 연한 칸 처리
// N = 10000
// 출력 : 점수 & 타일이 들어있는 칸의 수
// 참고
//https://velog.io/@hammii/%EB%B0%B1%EC%A4%80-20061-%EB%AA%A8%EB%85%B8%EB%AF%B8%EB%85%B8%EB%8F%84%EB%AF%B8%EB%85%B8-2-java
class Main {

    static int N;
    static int score;
    static boolean[][] blue;
    static boolean[][] green;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        blue = new boolean[4][6];
        green = new boolean[6][4];
        score = 0;

        for (int i = 0; i < N; i++) {
            // 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 초록 파랑으로 이동
            move(type, r, c);

            // 이동 및 점수 계산
            checkGreen();
            checkBlue();

        }
        System.out.println(score);
        System.out.print(count());
    }

    public static void move(int type, int r, int c) {

        if (type == 1) {
            int max = 0;
            // 초록
            for (int i = 0; i < 6; i++) {
                if (!green[i][c]) max = i;
                else break;
            }
            green[max][c] = true;
            // 파랑
            max = 0;
            for (int i = 0; i < 6; i++) {
                if (!blue[r][i]) max = i;
                else break;
            }
            blue[r][max] = true;
        } else if (type == 2) { // ㅁㅁ
            int max = 0;
            // 초록
            for (int i = 0; i < 6; i++) {
                if (!green[i][c] && !green[i][c + 1]) max = i;
                else break;
            }
            green[max][c] = true;
            green[max][c + 1] = true;
            // 파랑
            max = 0;
            for (int i = 1; i < 6; i++) {
                if (!blue[r][i] && !blue[r][i - 1]) max = i;
                else break;
            }
            blue[r][max] = true;
            blue[r][max - 1] = true;
        } else { // 2*1
            int max = 0;
            // 초록
            for (int i = 1; i < 6; i++) {
                if (!green[i][c] && !green[i - 1][c]) max = i;
                else break;
            }
            green[max][c] = true;
            green[max - 1][c] = true;
            // 파랑
            max = 0;
            for (int i = 0; i < 6; i++) {
                if (!blue[r][i] && !blue[r + 1][i]) max = i;
                else break;
            }
            blue[r][max] = true;
            blue[r + 1][max] = true;
        }
    }

    public static void checkGreen() {

        List<Integer> list = new ArrayList<>();
        // 꽉 찬 칸 체크
        for (int i = 2; i < 6; i++) {
            int count = 0;
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) count++;
            }
            if (count == 4) {
                list.add(i);
                score++;
            }
        }
        // 빈 만큼 옆으로 밀기
        if (list.size() > 0) moveGreen(list);

        list = new ArrayList<>();
        // 특별 칸 체크
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    list.add(i);
                    break;
                }
            }
        }

        // 특별 칸만큼 밀기
        if (list.size() > 0) moveGreen(list);

    }

    public static void moveGreen(List<Integer> list) {

        for (Integer n : list) {
            if (n > 1) {
                // n행부터 아래로 밀어준다.
                for (int i = n; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        green[i][j] = green[i - 1][j];
                    }
                }
                for (int i = 0; i < 4; i++)
                    green[0][i] = false;
            } else {
                // 특별칸 밀기
                for (int i = 5; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        green[i][j] = green[i - 1][j];
                    }
                }
                for (int i = 0; i < 4; i++)
                    green[0][i] = false;
            }
        }
    }

    public static void checkBlue() {
        List<Integer> list = new ArrayList<>();
        // 꽉 찬 칸 체크
        for (int i = 2; i < 6; i++) {
            int count = 0;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) count++;
            }
            if (count == 4) {
                list.add(i);
                score++;
            }
        }
        if (list.size() > 0) moveBlue(list);

        list = new ArrayList<>();
        // 특별 칸 체크
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    list.add(i);
                    break;
                }
            }
        }

        if (list.size() > 0) moveBlue(list);
    }

    public static void moveBlue(List<Integer> list) {

        for (Integer n : list) {
            if (n > 1) {
                // n열부터 오른쪽으로 밀어준다.
                for (int i = n; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        blue[j][i] = blue[j][i - 1];
                    }
                }
                for (int i = 0; i < 4; i++)
                    blue[i][0] = false;
            } else {
                // 특별칸 밀기
                for (int i = 5; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        blue[j][i] = blue[j][i - 1];
                    }
                }
                for (int i = 0; i < 4; i++)
                    blue[i][0] = false;
            }
        }
    }

    public static int count() {
        int cnt = 0;
        for (boolean[] arr : green) {
            for (boolean b : arr) {
                if (b) cnt++;
            }
        }

        for (boolean[] arr : blue) {
            for (boolean b : arr) {
                if (b) cnt++;
            }
        }

        return cnt;
    }

}