//https://cp-algorithms.com/geometry/nearest_points.html

#include <bits/stdc++.h>
using namespace std;

struct pt {
    long double x, y;
    int id;
};

struct cmp_x {
    bool operator()(const pt & a, const pt & b) const {
        return a.x < b.x || (a.x == b.x && a.y < b.y);
    }
};

struct cmp_y {
    bool operator()(const pt & a, const pt & b) const {
        return a.y < b.y;
    }
};

int n;
vector<pt> a, t;
long double mindist;
pair<int, int> best_pair;

long double dist(const pt & a, const pt & b){
    return sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
}

void upd_ans(const pt & a, const pt & b) {
    long double distt = dist(a, b);
    if (distt < mindist) {
        mindist = distt;
        best_pair = {a.id, b.id};
    }
}

void rec(int l, int r) {
    if (r - l <= 3) {
        for (int i = l; i < r; ++i) {
            for (int j = i + 1; j < r; ++j) {
                upd_ans(a[i], a[j]);
            }
        }
        sort(a.begin() + l, a.begin() + r, cmp_y());
        return;
    }

    int m = (l + r) >> 1;
    int midx = a[m].x;
    rec(l, m);
    rec(m, r);

    merge(a.begin() + l, a.begin() + m, a.begin() + m, a.begin() + r, t.begin(), cmp_y());
    copy(t.begin(), t.begin() + r - l, a.begin() + l);

    int tsz = 0;
    for (int i = l; i < r; ++i) {
        if (abs(a[i].x - midx) < mindist) {
            for (int j = tsz - 1; j >= 0 && a[i].y - t[j].y < mindist; --j)
                upd_ans(a[i], t[j]);
            t[tsz++] = a[i];
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);
    int tt;
    cin >> tt;
    for(int xx = 1; xx <= tt; xx++){
        cin >> n;
        t.resize(n);
        a.resize(n);
        int i = 0;
        for(i = 0; i < n; i++){
            cin >> a[i].x >> a[i].y;
            a[i].id = i;
        }
        sort(a.begin(), a.end(), cmp_x());
        mindist = 1E20;
        rec(0, n);
        int ind1 = best_pair.first;
        int ind2 = best_pair.second;
        //do stuff here
    }
}