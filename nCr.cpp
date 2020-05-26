long long power(long long x, long long y, long long p){
    long long res = 1; 
    x = x % p;
    if (x == 0) return 0;
    while (y > 0){
        if (y & 1)
            res = (res*x) % p;
        y = y>>1;
        x = (x*x) % p;
    }
    return res;
}
long long fact[MAX_N], inv_fact[MAX_N];
void pre(){
    fact[0] = 1;
    inv_fact[0] = 1;
    int i = 0;
    for(i = 1; i < MAX_N; i++){
        fact[i] = fact[i - 1] * i;
        fact[i] %= mod;
        inv_fact[i] = power(fact[i], mod - 2, mod);
    }
}
long nCr(int n, int r){
    if(n < r)
        return 0;
    long long ans = fact[n];
    ans *= inv_fact[r];
    ans %= mod;
    ans *= inv_fact[n - r];
    ans %= mod;
    return ans;
}
