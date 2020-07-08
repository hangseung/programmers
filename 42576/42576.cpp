#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    sort(participant.begin(), participant.end());
    sort(completion.begin(), completion.end());
    
    vector<string>::iterator completionIterator = completion.begin();
    for (auto player : participant) {
        if (player != *completionIterator) {
            return player;
        }
        ++completionIterator;
    }
}
