#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq;
    for (auto i : scoville) {
        pq.push(i);
    }
    int answer = 0;
    while (!pq.empty() && pq.top() < K) {
        int a = pq.top();
        pq.pop();
        if (pq.empty()) {
            return -1;
        }
        int b = pq.top();
        pq.pop();
        pq.push(a + b * 2);
        ++answer;
    }
    if (pq.empty() || pq.top() < K) {
        return -1;
    }
    return answer;
}
