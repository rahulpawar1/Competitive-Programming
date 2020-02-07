int[] dist = new int[MAXN];
    int[][] anc = new int[lgN][MAXN];
    void dfs(int v, int p){
        anc[0][v] = p;
        for (int i = 1; i < lgN; i++)
            anc[i][v] = anc[i - 1][anc[i - 1][v]];
        for(int i = 0; i < tree[v].size; i++){
            int u = tree[v].get(i);
            if(u != p) {
                dist[u] = dist[v] + 1;
                dfs(u, v);
            }
        }
    }
    int LCA(int u, int v) {
        if (dist[u] < dist[v]) {
            int t = u;
            u = v;
            v = t;
        }
        int diff = dist[u] - dist[v];
        for (int i = lgN - 1; i >= 0; i--) {
            if (((1 << i) & diff) != 0) {
                u = anc[i][u];
            }
        }
        if (u == v)
            return u;
        for (int i = lgN - 1; i >= 0; i--) {
            if (anc[i][u] > 0 && anc[i][u] != anc[i][v]) {
                u = anc[i][u];
                v = anc[i][v];
            }
        }
        return anc[0][u];
    }
