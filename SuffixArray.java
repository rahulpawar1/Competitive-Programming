       public int[] suffixArray(String s) {
            int n = s.length();
            Suffix[] su = new Suffix[n];
            for (int i = 0; i < n; i++) {
                su[i] = new Suffix(i, s.charAt(i) - '$', 0);
            }
            for (int i = 0; i < n; i++) su[i].next = (i + 1 < n ? su[i + 1].rank : -1);
            Arrays.sort(su);
            int[] ind = new int[n];
            for (int length = 4; length < 2 * n; length <<= 1) {
                int rank = 0, prev = su[0].rank;
                su[0].rank = rank;
                ind[su[0].index] = 0;
                for (int i = 1; i < n; i++) {
                    if (su[i].rank == prev && su[i].next == su[i - 1].next) {
                        prev = su[i].rank;
                        su[i].rank = rank;
                    } else {
                        prev = su[i].rank;
                        su[i].rank = ++rank;
                    }
                    ind[su[i].index] = i;
                }
                for (int i = 0; i < n; i++) {
                    int nextP = su[i].index + length / 2;
                    su[i].next = nextP < n ? su[ind[nextP]].rank : -1;
                }
                Arrays.sort(su);
            }
            int[] suf = new int[n];
            for (int i = 0; i < n; i++) suf[i] = su[i].index;
            return suf;
        }

        

        public class Suffix implements Comparable<Suffix> {
            int index;
            int rank;
            int next;

            public Suffix(int ind, int r, int nr) {
                index = ind;
                rank = r;
                next = nr;
            }

            public int compareTo(Suffix s) {
                if (rank != s.rank) return Integer.compare(rank, s.rank);
                return Integer.compare(next, s.next);
            }

        }
