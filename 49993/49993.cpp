#include <string>
#include <vector>

using namespace std;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    vector<int> indices(26, -1);
    for (int i = 0; i < skill.length(); i++) {
        indices[skill.at(i) - 'A'] = i;
    }
    for (string s : skill_trees) {
        vector<bool> chk(26, false);
        bool p = true;
        for (int i = 0; i < s.length() && p; i++) {
            chk[s.at(i) - 'A'] = true;
            if (indices[s.at(i) - 'A'] != -1) {
                for (int j = 0; j < indices[s.at(i) - 'A'] && p; j++) {
                    if (!chk[skill.at(j) - 'A']) {
                        p = false;
                    }
                }
            }
        }
        if (p) {
            ++answer;
        }
    }
    return answer;
}