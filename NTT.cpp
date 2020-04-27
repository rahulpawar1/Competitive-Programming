//Credits : https://github.com/meooow25/competitive-programming/blob/master/NTT.cpp
//Problem Link : https://www.codechef.com/problems/RECNDSUB


#include <bits/stdc++.h>
using namespace std;

const long long MOD = 163577857;
const int MAX_N = 1e5 + 5;
const int MAX_T = 111;

long long powmod(long long a, int p) {
    long long r = 1;
    while (p) {
        if (p & 1) r = r * a % MOD;
        p >>= 1; a = a * a % MOD;
    }
    return r;
}

long long fact[MAX_N], invfact[MAX_N];

void init() {
    fact[0] = invfact[0] = 1;
    for (int i = 1; i < MAX_N; i++) {
        fact[i] = i * fact[i - 1] % MOD;
        invfact[i] = powmod(fact[i], MOD - 2);
    }
}

long long nCr(int n, int r){
	if(n < r)
		return 0;
	long long hola = fact[n];
	hola *= invfact[n - r];
	hola %= MOD;
	hola *= invfact[r];
	hola %= MOD;
	return hola;
}
namespace NTT {
    const long long P = MOD;
    const int K = 20;
    const int ROOT = 23;
    const int N = 1 << K;
    vector<int> W, invW;
    vector<int> rev;

    void init() {
        int n2 = N >> 1;
        W.resize(n2 + 1); W[0] = 1;
        W[1] = powmod(ROOT, (P - 1) / N);
        for (int i = 2; i <= n2; i++)
            W[i] = 1LL * W[i - 1] * W[1] % P;
        invW.resize(n2 + 1); invW[n2] = W[n2];
        for (int i = n2 - 1; i >= 0; i--)
            invW[i] = 1LL * invW[i + 1] * W[1] % P;
        rev.resize(N);
    }

    void transform(vector<int> &a, bool inv=false) {
        int k = 0; while ((1 << k) < a.size()) k++;
        int n = 1 << k;
        a.resize(n, 0);
        rev[0] = 0;
        for (int i = 1; i < n; i++) {
            rev[i] = rev[i >> 1] >> 1 | ((i & 1) << (k - 1));
            if (i < rev[i]) swap(a[i], a[rev[i]]);
        }

        vector<int> &twiddle = inv ? invW : W;
        for (int len = 2; len <= n; len <<= 1) {
            int half = len >> 1, diff = N / len;
            for (int i = 0; i < n; i += len) {
                int pw = 0;
                for (int j = i; j < i + half; j++) {
                    int v = 1LL * a[j + half] * twiddle[pw] % P;
                    a[j + half] = a[j] - v;
                    if (a[j + half] < 0) a[j + half] += P;
                    a[j] += v;
                    if (a[j] >= P) a[j] -= P;
                    pw += diff;
                }
            }
        }

        if (inv) {
            long long inv_n = powmod(n, P - 2);
            for (int &x : a) x = x * inv_n % P;
        }
    }

    void convolve(vector<int> &a, vector<int> &b) {
        int m = a.size() + b.size() - 1;
        a.resize(m, 0); transform(a);
        b.resize(m, 0); transform(b);
        for (int i = 0; i < a.size(); i++)
            a[i] = 1LL * a[i] * b[i] % P;
        transform(a, true);
        a.resize(m);
    }
};

int T, ans[MAX_T];
pair<pair<int, int>, int> A[MAX_T];

int arr[MAX_N];

void solve() {
    

    int n;
    cin >> n;
    int i = 0;
    int c1 = 0, c2 = 0, c3 = 0;
    for(i = 0; i < n; i++){
    	int x;
    	cin >> x;
    	if(x == -1)
    		c1++;
    	else if(x == 1)
    		c2++;
    	else
    		c3++;
    }
    int prev = 0;
    vector<int> neg;
    vector<int> pos;
    for(i = 0; i <= n; i++)
    	neg.push_back(nCr(c1, n - i));
    for(i = 0; i <= n; i++)
    	pos.push_back(nCr(c2, i));
    NTT::convolve(pos, neg);
    for(i = 0; i <= 2 * n; i++){
        long long ans = pos[i];
        ans *= powmod(2, c3);
        ans %= MOD;
        if(i == n)
            ans--;
        cout << ans << " ";
    }
    cout << endl;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);
    init();
    NTT::init();
    cin >> T;
    while(T--){
    	solve();
    }

    return 0;
}
