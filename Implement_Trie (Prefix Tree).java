class Trie {

    static class TrieNode {

        boolean isEnd;
        TrieNode[] childNodes;

        TrieNode() {
            isEnd = false;
            childNodes = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (crawler.childNodes[ch - 'a'] == null) {
                crawler.childNodes[ch - 'a'] = new TrieNode();
            }
            crawler = crawler.childNodes[ch - 'a'];
        }
        crawler.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (crawler.childNodes[ch - 'a'] == null) {
                return false;
            }
            crawler = crawler.childNodes[ch - 'a'];
        }
        return crawler != null && crawler.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode crawler = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (crawler.childNodes[ch - 'a'] == null) {
                return false;
            }
            crawler = crawler.childNodes[ch - 'a'];
        }
        return true;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
