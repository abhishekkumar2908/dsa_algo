class Solution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int l = isConnected[0].length;
        int[] parents = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            rank[i] = 0;
        }
        int city = 0;
        for (int[] conn : isConnected) {
            // int l = conn.length;
            Queue<Integer> attached = new LinkedList<>();
            for (int i = 0; i < l; i++) {
                if (conn[i] == 1 && i != city) {
                    attached.add(i);
                }
            }
            int curr_city_parent = getP(city, parents);
            while (!attached.isEmpty()) {
                int connected_city = attached.poll();
                union(curr_city_parent, connected_city, parents, rank);
            }
            city++;
        }
        Set<Integer> provinces = new HashSet<>();
        for (int p = 0; p < n; p++) {
            provinces.add(getP(p, parents));
        }
        return provinces.size();
    }

    public int getP(int x, int[] parent) {
        if (x == parent[x]) return parent[x];
        return parent[x] = getP(parent[x], parent);
    }

    public void union(int x, int y, int[] parent, int[] rank) {
        int xParent = getP(x, parent);
        int yParent = getP(y, parent);
        if (xParent != yParent) {
            if (rank[xParent] > rank[yParent]) parent[yParent] = xParent;
            else if (rank[xParent] < rank[yParent]) parent[xParent] = yParent;
            else {
                parent[yParent] = xParent;
                rank[xParent] += 1;
            }
        } else return;
    }
}
