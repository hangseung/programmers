import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String skillTree : skill_trees) {
            String s = "";
            for (char c : skillTree.toCharArray()) {
                if (skill.indexOf(c) != -1) {
                    s += c;
                }
            }
            
            boolean possible = true;
            for (char c : skill.toCharArray()) {
                if (!possible || s.length() == 0) {
                    break;
                }
                
                int index = s.indexOf(c);
                if (index != 0) {
                    possible = false;
                }
                s = s.replace(Character.toString(c), "");
            }

            if (possible) {
                ++answer;
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String skill = reader.readLine();
        String rawSkillTrees = reader.readLine();
        String[] skillTrees = rawSkillTrees.split(" ");

        Solution solution = new Solution();
        System.out.printf("%d\n", solution.solution(skill, skillTrees));
    }
}