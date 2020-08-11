import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int calc (int c) {
        if (c == 1) {
            return 0;
        }
        return Integer.toString(c).length();
    }
    
    public int solution(String s) {
        int l = s.length();
        int answer = l;
        for (int i = 1; i < l; i++) {
            String prev = s.substring(0, i);
            int c = 1, sum = 0;
            for (int j = i; j < l; j += i) {
                String next = s.substring(j, j + i <= l ? j + i : l);
                if (!prev.equals(next)) {
                    sum += i + calc(c);
                    c = 0;
                    prev = next;
                }
                ++c;
            }
            sum += prev.length() + calc(c);
            answer = (sum < answer) ? sum : answer;
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        Solution solution = new Solution();
        System.out.println(solution.solution(s));
    }
}