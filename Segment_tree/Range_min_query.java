/*
    To build segment tree(for storing min elements within the range) and range minimum query function, the function will take four arguments.
    First argument will be the segment tree that has been constructed. Second will be size of the array.
    Third and fourth will be the range of query a and b.
    The function returns minimum of the element in the array from index range a and b.
*/
class Solution {

    void segmentTree(int left, int right, int[] tree, int i, int[] arr) {
        if (left == right) {
            tree[i] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        segmentTree(left, mid, tree, 2 * i + 1, arr);
        segmentTree(mid + 1, right, tree, 2 * i + 2, arr);

        tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
    }

    int query(int i, int left, int right, int start, int end, int[] tree) {
        if (left < start || right > end) return Integer.MAX_VALUE;
        if (left >= start && right <= end) return tree[i];

        int mid = (left + right) / 2;

        return Math.min(
            query(2 * i + 1, left, mid, start, end, tree),
            query(2 * i + 2, mid + 1, right, start, end, tree)
        );
    }
}
