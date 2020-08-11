import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(Arrays.stream(scoville).boxed().collect(Collectors.toList()));

        while (priorityQueue.size() >= 2) {
            Integer a = priorityQueue.poll();
            if (a >= K) {
                break;
            }

            Integer b = priorityQueue.poll();
            priorityQueue.offer(a + b * 2);
            ++answer;
        }

        Integer a = priorityQueue.poll();
        if (a < K) {
            answer = -1;
        }

        return answer;
    }

    Solution () {};
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        line = in.readLine();
        int[] scovilles = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        
        line = in.readLine();
        int k = Integer.parseInt(line);

        Solution solution = new Solution();
        int answer = solution.solution(scovilles, k);
        System.out.printf("%d\n", answer);
    }
}