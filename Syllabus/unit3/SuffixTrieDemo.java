import java.util.Scanner;

public class SuffixTrieDemo {

    // Trie node class
    static class TrieNode {
        static final int ALPHABET_SIZE = 26;
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        public TrieNode() {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }

        // Recursive method to insert a suffix
        void insertSuffix(String s) {
            if (s.length() == 0) return;
            int index = s.charAt(0) - 'a';
            if (children[index] == null) {
                children[index] = new TrieNode();
            }
            children[index].insertSuffix(s.substring(1));
        }
    }

    // Suffix Trie class
    static class SuffixTrie {
        TrieNode root;

        public SuffixTrie(String str) {
            root = new TrieNode();
            for (int i = 0; i < str.length(); i++) {
                root.insertSuffix(str.substring(i));
            }
        }

        // Count all nodes in the Trie
        private int countNodes(TrieNode node) {
            if (node == null) return 0;
            int count = 0;
            for (TrieNode child : node.children) {
                if (child != null) {
                    count += countNodes(child);
                }
            }
            return 1 + count;
        }

        public int countDistinctSubstrings() {
            return countNodes(root);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a lowercase string: ");
        String input = sc.nextLine();

        SuffixTrie trie = new SuffixTrie(input);
        int distinctSubstrings = trie.countDistinctSubstrings() - 1; // exclude empty string
        System.out.println("Total distinct substrings: " + distinctSubstrings);
    }
}
