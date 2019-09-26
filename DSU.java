class DSU{
	int[] par, sz;
	DSU(int n){
		par = new int[n];
		sz = new int[n];
		int i = 0;
		for(i = 0; i < n; i++){
			par[i] = i;
			sz[i] = i;
		}
	}
	int find(int x){
		if(par[x] != x)
			return par[x] = find(par[x]);
		return par[x];
	}
	void unite(int x, int y){
		int x_root = find(x);
		int y_root = find(y);
		if(x_root == y_root)
			return;
		Random rand = new Random();
		int prob = rand.nextInt();
		if(prob % 2 == 0){
			par[x_root] = y_root;
			sz[y_root] += sz[x_root];
		}
		else{
			par[y_root] = x_root;
			sz[x_root] += sz[y_root];
		}
	}
}