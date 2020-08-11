import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int priority : priorities) {
            queue.offer(priority);
        }

        int n = priorities.length;
        boolean[] printed = new boolean[n];
        Arrays.fill(printed, false);
        int it = 0;
        while (!queue.isEmpty()) {
            int biggest = queue.poll();
            ++answer;
            while (!(priorities[it] == biggest && !printed[it])) {
                it = (it + 1) % n;
            }
            printed[it] = true;
            if (it == location) {
                return answer;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int[] priorities = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        line = reader.readLine();
        int location = Integer.parseInt(line);

        Solution solution = new Solution();
        System.out.println(solution.solution(priorities, location));
    }
}