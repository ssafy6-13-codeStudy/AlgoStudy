import java.util.*;
import java.io.*;

class Main {
    /*
    거꾸로해도 똑같은 문자
    유사회문 : 한 문자를 삭제해서 회문으로 만들 수 있는 문자열

    0: 회문
    1: 유사회문
    2: x

    반례 : abbab..
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String s = br.readLine();

            sb.append(func(s) + "\n");
        }

        System.out.println(sb);
    }

    public static int func(String s) {
        int l = 0;
        int r = s.length() - 1;
        int cnt = 0;

        while (l <= r) {
            // 같으면 넘겨
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                cnt++;
                int ll = l + 1;
                int rr = r;

                // 왼쪽 이동하고 체크
                while (ll <= rr) {

                    if (s.charAt(ll) == s.charAt(rr)) {
                        ll++;
                        rr--;
                    } else {
                        cnt++;
                        break;
                    }
                }
                if (cnt == 1) return 1;

                ll = l;
                rr = r - 1;

                // 오른쪽 이동하고 체크
                while (ll <= rr) {

                    if (s.charAt(ll) == s.charAt(rr)) {
                        ll++;
                        rr--;
                    } else {
                        cnt++;
                        break;
                    }
                }

                if(cnt == 2) return 1;
                else return 2;
            }
        }
        return cnt;
    }
}