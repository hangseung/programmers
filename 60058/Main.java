import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public Boolean isRight(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (count == 0) {
                    return false;
                }
                --count;
            }
            else {
                ++count;
            }
        }
        return count == 0;
    }

    public String solution(String p) {
        int l = p.length();
        if (isRight(p)) {
            return p;
        }

        String u = "", v = "";
        int c = 0;
        for (int i = 0; i < l; i++) {
            c += (p.charAt(i) == '(') ? 1 : -1;
            if (c == 0) {
                u = p.substring(0, i + 1);
                v = (i + 1 < l) ? p.substring(i + 1, l) : "";
                break;
            }
        }

        if (isRight(u)) {
            return u + solution(v);
        }
        
        String ret = '(' + solution(v) + ')';
        for (int i = 1; i < u.length() - 1; i++) {
            ret += (u.charAt(i) == '(') ? ')' : '(';
        }
        return ret;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String p = reader.readLine();
        Solution solution = new Solution();
        System.out.println(solution.solution(p));
    }    
}