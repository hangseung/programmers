#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    if (n % 2 == 1) {
        return 0;
    }
    
    vector<long long int> d(n / 2 + 1, 0);
    d[0] = 1;
    
    for (int i = 1; i <= n / 2; i++) {
        d[i] = d[i - 1];
        for (int j = 0; j < i; j++) {
            d[i] += d[j] * 2;
            d[i] %= 1000000007;
        }
    }
    
    return d[n / 2];
}
