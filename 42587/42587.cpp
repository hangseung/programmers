#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    int it = 0;
    vector<int> copied;
    for (int p : priorities) {
        copied.push_back(p);
    }
    sort(copied.begin(), copied.end(), greater<int>());
    vector<bool> chk(priorities.size(), false);
    for (int next : copied) {
        while (!(!chk[it] && priorities[it] == next)) {
            it = (it + 1) % copied.size();
        }
        ++answer;
        chk[it] = true;
        if (it == location) {
            break;
        }
        it = (it + 1) % copied.size();
    }
    return answer;
}