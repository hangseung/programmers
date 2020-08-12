import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> set = new HashSet<>();
        char lastCharacter = words[0].charAt(0);
        for (int i = 0; i < words.length; i++) {
            if (words[i].charAt(0) != lastCharacter || set.contains(words[i])) {
                return new int[]{i % n + 1, i / n + 1};
            }
            lastCharacter = words[i].charAt(words[i].length() - 1);
            set.add(words[i]);
        }

        return new int[]{0, 0};
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int n = Integer.parseInt(line);
        line = reader.readLine();
        Solution solution = new Solution();
        int[] answer = solution.solution(n, line.split(", "));
        System.out.println(answer[0] + " " + answer[1]);
    }
}