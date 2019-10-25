class Segment_tree {
        long INF = (long) 1e14;
        long[] sum;
        long[] lazy;
        int n;

        Segment_tree(int x) {
            n = x;
            sum = new long[4 * n];
            lazy = new long[4 * n];
            Arrays.fill(lazy, -1);
            build(1, 0, n - 1);
        }

        void build(int node, int tl, int tr) {
            if (tl == tr) { // leaf{
                sum[node] = 0;
            } else {
                int mid = tl + tr >> 1, left = node * 2, right = left + 1;
                build(left, tl, mid);
                build(right, mid + 1, tr);
                merge(node);
            }
        }

        long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        long query(int node, int tl, int tr, int l, int r) {
            if (r < tl || tr < l)
                return -INF;
            if (tl >= l && tr <= r)
                return sum[node];
            if (lazy[node] != -1)
                propagate(node);
            int mid = tl + tr >> 1, left = node * 2, right = left + 1;
            return Math.max(query(left, tl, mid, l, r), query(right, mid + 1, tr, l, r));
        }

        void merge(int node) {
            //do stuff like min, max, sum, etc
            sum[node] = Math.max(sum[node << 1], sum[node << 1 | 1]);
        }

        void rangeupdate(int i, int j, long val) {
            rangeupdate(1, 0, n - 1, i, j, val);
        }

        void rangeupdate(int node, int tl, int tr, int l, int r, long val) {
            if (r < tl || tr < l) {
                return;
            }
            if (tl >= l && tr <= r) {
                sum[node] = val;
                lazy[node] = val;
            } else {
                int mid = tl + tr >> 1, left = node * 2, right = left + 1;
                if (lazy[node] != -1)
                    propagate(node);
                rangeupdate(left, tl, mid, l, r, val);
                rangeupdate(right, mid + 1, tr, l, r, val);
                merge(node);
            }
        }

        void propagate(int node) {
            lazy[node * 2] = lazy[node];
            lazy[node * 2 + 1] = lazy[node];
            sum[node * 2] = lazy[node];
            sum[node * 2 + 1] = lazy[node];
            lazy[node] = -1;
        }

    }
