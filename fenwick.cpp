long long tree[MAX_N];
 
void update(int idx, long long val){
    while(idx < MAX_N){
        tree[idx] = max(tree[idx], val);
        idx += (idx & (-idx));
    }
}
 
long long query(int idx){
    long long ret = 0;
    while(idx > 0){
        ret = max(ret, tree[idx]);
        idx -= (idx & (-idx));
    }
    return ret;
}
