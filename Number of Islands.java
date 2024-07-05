class Solution {
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        Union<Integer> unionFind = new Union<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int index = i * n + j;
                    unionFind.u.put(index, index); // Each cell starts as its own parent
                }
            }
        }

        // Union adjacent land cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int index = i * n + j;
                    // Check right cell
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        int rightIndex = i * n + (j + 1);
                        unionFind.union(index, rightIndex);
                    }
                    // Check downward cell
                    if (i + 1 < m && grid[i + 1][j] == '1') {
                        int downIndex = (i + 1) * n + j;
                        unionFind.union(index, downIndex);
                    }
                }
            }
        }
        
        // Count unique islands
        Set<Integer> uniqueIslands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int index = i * n + j;
                    uniqueIslands.add(unionFind.find(index));
                }
            }
        }
        
        return uniqueIslands.size();

        
    }
    public static class Union<T>{
        private Map<T,T> u = new HashMap<>();
        public T find(T x){
            T y = u.getOrDefault(x,x);
            if(y != x){
                y = find(y);
                u.put(x,y);
            }
            return y;
        }
        public void union(T x, T y){
            u.put(find(x), find(y));
        }

    }
}