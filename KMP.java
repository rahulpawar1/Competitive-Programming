void KMPSearch(char[] pat, char[] txt) {
        int M = pat.length;
        int N = txt.length;
        int[] lps = computeLPSArray(pat, M);

        int i = 0;
        int j = 0;
        while (i < N) {
            if (pat[j] == txt[i]) {
                j++;
                i++;
            }
            if (j == M) {
                //do stuff with the index which match
                j = lps[j - 1];
            }
            else if (i < N && pat[j] != txt[i]) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }
    int[] computeLPSArray(char[] pat, int M) {
        int[] lps = new int[M];
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < M) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }