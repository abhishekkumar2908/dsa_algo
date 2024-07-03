import java.util.ArrayList;
import java.util.List;

class TrieStructure {

    private int m, n;
    private List<String> result;
    private int[][] dirs = new int[][] {
        { 1, 0 },
        { -1, 0 },
        { 0, 1 },
        { 0, -1 },
    };

    public List<String> findWords(char[][] board, String[] words) {
        // inserting all the words into the trie (root)
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(word, root);
        }

        m = board.length;
        n = board[0].length;
        result = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = board[r][c];
                if (root.children[ch - 'a'] != null) {
                    search(board, r, c, root);
                }
            }
        }

        return result;
    }

    private void search(char[][] board, int r, int c, TrieNode root) {
        if (r < 0 || r >= m || c < 0 || c >= n) return;

        char ch = board[r][c];
        if (ch == '$' || root.children[ch - 'a'] == null) return;

        root = root.children[ch - 'a'];

        if (root.isEndOfWord) {
            result.add(root.word);
            root.isEndOfWord = false; // Avoid duplicate entries
        }

        board[r][c] = '$';
        for (int[] dir : dirs) {
            search(board, r + dir[0], c + dir[1], root);
        }
        board[r][c] = ch;
    }

    static class TrieNode {

        TrieNode[] children;
        boolean isEndOfWord;
        String word;

        TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
            word = "";
        }
    }

    private TrieNode getNode() {
        return new TrieNode();
    }

    private void insert(String word, TrieNode root) {
        TrieNode crawler = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (crawler.children[index] == null) {
                crawler.children[index] = getNode();
            }
            crawler = crawler.children[index];
        }
        crawler.isEndOfWord = true;
        crawler.word = word;
    }
}
