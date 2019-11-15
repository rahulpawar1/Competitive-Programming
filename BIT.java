	long getSum(long BITree[], int index) {
        index += 1;
        long sum = 0;
        while (index>0)
        {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }
    void updateBIT(long BITree[], int index, int val) {
        index = index + 1;
        while (index <= n)
        {
            BITree[index] += val;
            index += index & (-index);
        }
    }
    long sum(int x, long BITTree1[], long BITTree2[]) {
        return (getSum(BITTree1, x) * x) - getSum(BITTree2, x);
    }
    void updateRange(long BITTree1[], long BITTree2[], int val, int l, int r) {
        updateBIT(BITTree1,l,val);
        updateBIT(BITTree1,r+1,-val);
        updateBIT(BITTree2,l,val*(l-1));
        updateBIT(BITTree2,r+1,-val*r);
    }
    long rangeSum(int l, int r, long BITTree1[], long BITTree2[]){
        return sum(r, BITTree1, BITTree2) - sum(l-1, BITTree1, BITTree2);
    }