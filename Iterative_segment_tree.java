int n;
    long[] segtree;
    long[] arr = new long[MAXN];
    long merge(long x, long y){
        return x + y;
    }
    void build_segtree(){
        int i = 0;
        for(i = n - 1; i > 0; i--)
            segtree[i] = merge(segtree[i << 1], segtree[i << 1 | 1]);
    }
    void segtree_update(int ind, long val){
        for(segtree[ind += n] = val; ind > 1; ind >>= 1)
            segtree[ind >> 1] = merge(segtree[ind], segtree[ind ^ 1]);
    }
    long segtree_query(int l, int r){
        long res = 0;
        for(l += n, r += n; l < r; l >>= 1, r >>= 1){
            if((l & 1) == 1)
                res = merge(res, segtree[l++]);
            if((r & 1) == 1)
                res = merge(res, segtree[--r]);
        }
        return res;
    }