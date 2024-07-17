/*
    Given two arrays, first for numbers and second array  for indexes for queries ( (a,b) => a is queries[i] and b is queries[i+1])
    to return sum of the numbers in the range of a and b
*/

class Solution {

    // Building the segment tree
    void segmentTree(int left, int right, int[] tree, int i, int[] arr) {
        if (left == right) {
            tree[i] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        segmentTree(left, mid, tree, 2 * i + 1, arr);
        segmentTree(mid + 1, right, tree, 2 * i + 2, arr);

        tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
    }

    // Querying the segment tree
    int query(int start, int end, int i, int left, int right, int[] tree) {
        // If out of range
        if (left > end || right < start) return 0; // won't contribute to the sum

        // If entirely in the range
        if (left >= start && right <= end) return tree[i]; // current node will contribute to the sum

        int mid = (left + right) / 2;
        return (
            query(start, end, 2 * i + 1, left, mid, tree) +
            query(start, end, 2 * i + 2, mid + 1, right, tree)
        );
    }

    // Method to handle multiple queries and return their sums
    int[] querySum(int n, int[] arr, int q, int[] queries) {
        int[] tree = new int[4 * n];
        segmentTree(0, n - 1, tree, 0, arr);
        int[] result = new int[q / 2];
        for (int i = 0; i < q; i += 2) {
            int start = queries[i] - 1;
            int end = queries[i + 1] - 1;
            result[i / 2] = query(start, end, 0, 0, n - 1, tree);
        }
        return result;
    }
}
