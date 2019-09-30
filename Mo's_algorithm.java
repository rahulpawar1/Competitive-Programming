import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.Comparator;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Aman Kumar Singh
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        EstimatingProgress solver = new EstimatingProgress();
        solver.solve(1, in, out);
        out.close();
    }

    static class EstimatingProgress {
        int MAXN = 200005;
        PrintWriter out;
        InputReader in;
        long[] arr = new long[MAXN];
        int sqrt;
        
        final Comparator<Tuple> com = new Comparator<Tuple>() {
            public int compare(Tuple t1, Tuple t2) {
                if (t1.x / sqrt != t2.x / sqrt)
                    return Integer.compare(t1.x / sqrt, t2.x / sqrt);
                else
                    return Integer.compare(t1.y, t2.y);
            }
        };

        void add(int idx) {
            //do stuff
        }

        void remove(int idx) {
            //do stuff
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            this.out = out;
            this.in = in;
            int n = ni();
            int i = 0;
            sqrt = (int) Math.sqrt(n);
            for (i = 0; i < n; i++)
                arr[i] = nl();
            int q = ni();
            Tuple[] query = new Tuple[q];
            for (i = 0; i < q; i++)
                query[i] = new Tuple(ni() - 1, ni() - 1, i);
            Arrays.sort(query, com);
            int cur_l = 0, cur_r = -1;
            for (Tuple curr : query) {
                while (cur_l > curr.x) {
                    cur_l--;
                    add(cur_l);
                }
                while (cur_r < curr.y) {
                    cur_r++;
                    add(cur_r);
                }
                while (cur_l < curr.x) {
                    remove(cur_l);
                    cur_l++;
                }
                while (cur_r > curr.y) {
                    remove(cur_r);
                    cur_r--;
                }
                ans[curr.id] = invariant;
            }
            for (i = 0; i < q; i++)
                pn(ans[i]);
        }

        int ni() {
            return in.nextInt();
        }

        long nl() {
            return in.nextLong();
        }

        void pn(long zx) {
            out.println(zx);
        }

        class Tuple {
            int x;
            int y;
            int id;

            Tuple(int a, int b, int c) {
                x = a;
                y = b;
                id = c;
            }

        }

    }
}

