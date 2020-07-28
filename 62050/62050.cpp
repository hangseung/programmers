#include <string>
#include <vector>
#include <queue>
#include <cstdlib>
#include <utility>
#include <algorithm>

using namespace std;

struct Edge {
    int s, e, v;
    
    bool operator< (const Edge &rhs) const {
        return v < rhs.v;
    }
    
    Edge (int _s, int _e, int _v) : s(_s), e(_e), v(_v) {}
};

int N;

int dr[4] = {-1, 0, 1, 0};
int dc[4] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r >= 0 && r < N && c >= 0 && c < N;
}

int get_root(vector<int> parents, int node) {
    while (parents[node] != -1) {
        node = parents[node];
    }
    
    return node;
}

int solution(vector< vector<int> > land, int height) {
    N = land.size();
    int V = 0;
    
    vector<int> vertex_numbers(N * N, -1);
    for (int i = 0; i < N * N; i++) {
        if (vertex_numbers[i] != -1) {
            continue;
        }
        
        vertex_numbers[i] = V;

        queue<int> q;
        q.push(i);
        while (!q.empty()) {
            int index = q.front();
            q.pop();
            
            int r = index / N, c = index % N;
            for (int j = 0; j < 4; j++) {
                int nr = r + dr[j], nc = c + dc[j];
                int ni = nr * N + nc;
                
                if (inRange(nr, nc)
                    && vertex_numbers[ni] == -1
                    && abs(land[r][c] - land[nr][nc]) <= height) {
                    q.push(ni);
                    vertex_numbers[ni] = V;
                }
            }
        }
        
        ++V;
    }

    vector< vector< pair<int, int> > > adj(V, vector< pair<int, int> >(0));
    
    for (int i = 0; i < N * N; i++) {
        int r = i / N, c = i % N;
        for (int j = 0; j < 2; j++) {
            int nr = r + dr[j], nc = c + dc[j];
            int ni = nr * N + nc;
            
            if (!inRange(nr, nc) || vertex_numbers[i] == vertex_numbers[ni]) {
                continue;
            }
            
            int va = vertex_numbers[i], vb = vertex_numbers[ni];
            int distance = abs(land[r][c] - land[nr][nc]);
            
            bool found = false;
            for (int k = 0; k < adj[va].size(); k++) {
                if (adj[va][k].first == vb) {
                    if (distance < adj[va][k].second) {
                        adj[va][k].second = distance;
                    }
                    
                    found = true;
                    break;
                }
            }
            
            if (found) {
                continue;
            }
            
            for (int k = 0; k < adj[vb].size(); k++) {
                if (adj[vb][k].first == va) {
                    if (distance < adj[vb][k].second) {
                        adj[vb][k].second = distance;
                    }
                    
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                adj[va].push_back(make_pair(vb, distance));
            }
        }
    }
    
    vector<Edge> edges;
    for (int i = 0; i < adj.size(); i++) {
        for (auto e : adj[i]) {
            edges.push_back(Edge(i, e.first, e.second));
        }
    }
    
    sort(edges.begin(), edges.end());
    
    vector<int> parents(V, -1);
    int answer = 0;
    
    for (auto edge : edges) {
        if (V <= 1) {
            break;
        }
        
        int s_root = get_root(parents, edge.s), e_root = get_root(parents, edge.e);
        if (s_root != e_root) {
            answer += edge.v;
            parents[e_root] = s_root;
            
            --V;
        }
    }
    
    return answer;
}

int main() {
    int n, h;
    scanf("%d %d", &n, &h);

    vector< vector<int> > v(n, vector<int>());
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int input;
            scanf("%d", &input);
            v[i].push_back(input);
        }
    }

    int answer = solution(v, h);

    printf("%d\n", answer);

    return 0;
}