ArrayList<Integer>[] tree = new ArrayList[MAXN];
    int[] par = new int[MAXN];
    int[] sz = new int[MAXN];
    int[] dep = new int[MAXN];
    boolean[] off = new boolean[MAXN];
    void calc_sz(int u, int p) {
        sz[u] = 1;
        for (int v : tree[u]){
            if (v != p && !off[v]) {
                calc_sz(v, u);
                sz[u] += sz[v];
            }
        }
    }

    int get_centroid(int u, int p, int cur_sz) {
        for (int v : tree[u]) {
            if (v != p && !off[v]) {
                if (sz[v] * 2 > cur_sz)
                    return get_centroid(v, u, cur_sz);
            }
        }
        return u;
    }

    void centroid_decompose(int u, int p) {
        calc_sz(u, u);
        int cen = get_centroid(u, u, sz[u]);
        off[cen] = true;
        par[cen] = p;
        if(p != -1)
            dep[cen] = dep[p] + 1;
        for (int v : tree[cen]) {
            if (!off[v])
                centroid_decompose(v, cen);
        }
    }