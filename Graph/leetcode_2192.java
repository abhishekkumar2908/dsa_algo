import java.util.*;

class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        List<List<Integer>> ancestors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
            ancestors.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[1]).add(edge[0]);
        }

        /*
        edges:-

            [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]

        adj:-

            3 -> {0, 1}
            4 -> {0, 2}
            7 -> {2, 3}
            5 -> {3}
            6 -> {3, 4}


        */

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(i, i, adj, ancestors, visited);
        }
        for (List<Integer> ancestorList : ancestors) {
            Collections.sort(ancestorList);
        }
        return ancestors;
    }

    void dfs(int node, int thisNode, Map<Integer, List<Integer>> adj, List<List<Integer>> ancestors, boolean[] visited) {
        visited[thisNode] = true;
        for (int neighbor : adj.get(thisNode)) {
            if (!visited[neighbor]) {
                ancestors.get(node).add(neighbor);
                dfs(node, neighbor, adj, ancestors, visited);
            }
        }
    }
}

/*
    for node = 5, it will first mark 5 true, then call its neighbor and mark 3 true
    then on calling dfs for node = 5 and thisNode = 3
    0 and 1 will be marked true and hence ancestors for 5 { 3,0,1} will get added in ancestors
*/
