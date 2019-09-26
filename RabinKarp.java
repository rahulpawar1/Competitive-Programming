class Rabin_Karp{
        long[] hash = new long[MAXN];
        long[] pow = new long[MAXN];
        int p = 31;
        void pre(String st){
            hash[0] = 0;
            int i = 0;
            pow[0] = 1;
            for(i = 1; i < MAXN; i++)
                pow[i] = (pow[i - 1] * p) % mod;
            for(i = 1; i <= st.length(); i++)
                hash[i] = (hash[i - 1] + ((st.charAt(i - 1) - 'a' + 1) * pow[i - 1]) % mod) % mod;
        }
        long hash_val(int l, int r){
            long hashes = hash[r] - hash[l - 1] + mod;
            hashes %= mod;
            hashes *= power(pow[l - 1], mod - 2);
            hashes %= mod;
            return hashes;
        }
    }