long[] dp = new long[1 << n];
for(i = 0; i < (1 << n); ++i)
    dp[i] = A[i];
for(i = 0; i < n; ++i){ 
    for(int mask = 0; mask < (1 << n); ++mask){
        if((mask & (1<<i)) > 0)
            dp[mask] += dp[mask^(1<<i)]; //reverse if submasks are required instead of supermasks
    }
}