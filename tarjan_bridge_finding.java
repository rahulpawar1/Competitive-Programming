boolean[] visited = new boolean[MAXN];
    int[] tin = new int[MAXN], low = new int[MAXN];
    int timer = 0;
    void tarjan(int v, int p) {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        for (Edge e : graph[v]) {
            int to = e.other(v);
            if (to == p) continue;
            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                low[v] = Math.min(low[v], low[to]);
                if (low[to] > tin[v]) {
                    e.is_bridge = true;
                    bridges.add(e);
                }
            }
        }
    }