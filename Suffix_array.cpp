vector<int> suffix_array(string s) {
    s += "!";
    const int ALPHA = 256, n = (int) s.size();
    vector<int> suf(n), cls(s.begin(), s.end());
    iota(suf.begin(), suf.end(), 0);
    for (int len = 0, num_cls = ALPHA; len < n; len ? len <<= 1 : ++len) {
        vector<int> suf_aux(n), new_cls(n), radix(num_cls);
        for (int i = 0; i < n; ++i) {
            suf_aux[i] = suf[i] - len;
            if (suf_aux[i] < 0) suf_aux[i] += n;
            radix[cls[suf_aux[i]]]++;
        }
        for (int i = 1; i < num_cls; ++i) radix[i] += radix[i - 1];
        for (int i = n - 1; ~i; --i) suf[--radix[cls[suf_aux[i]]]] = suf_aux[i];
        num_cls = 1; new_cls[suf[0]] = 0;
        for (int i = 1; i < n; ++i) {
            if (cls[suf[i]] != cls[suf[i - 1]] || cls[suf[i] + len] != cls[suf[i - 1] + len])
                ++num_cls;
            new_cls[suf[i]] = num_cls - 1;
        }
        cls = std::move(new_cls);
    }
    for (int i = 0; i < n - 1; ++i) suf[i] = suf[i + 1];
    suf.pop_back();
    return suf;
}
// kasai O(n)
vector<int> compute_lcp(const string &s, const vector<int> &suf) {
    const int n = (int) s.size();
    vector<int> rank(n), lcp(n - 1);
    for (int i = 0; i < n; ++i) rank[suf[i]] = i;
    for (int i = 0, k = 0; i < n; ++i, k = max(0, k - 1)) {
        if (rank[i] == n - 1) { k = 0; continue; }
        int j = suf[rank[i] + 1];
        while (i + k < n && j + k < n && s[i + k] == s[j + k]) ++k;
        lcp[rank[i]] = k;
    }
    return lcp;
}
