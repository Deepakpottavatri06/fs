import java.util.*;

public class IndexPairs{

    public static void main(String[] args) {
        String text = "ababa";
        String[] words = {"aba", "ab"};

        Solution sol = new Solution();
        int[][] result = sol.indexPairs(text, words);

        System.out.println("Index Pairs:");
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }
}

class Solution {
    public int[][] indexPairs(String text, String[] words) {
        Trie root = new Trie();

        // Build the Trie from the words list
        for (String word : words) {
            Trie node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null)
                    node.children[index] = new Trie();
                node = node.children[index];
            }
            node.isEndOfWord = true;
        }

        List<int[]> result = new ArrayList<>();

        // For each starting index in the text, try to match substrings using the Trie
        for (int i = 0; i < text.length(); i++) {
            Trie node = root;
            int j = i;
            while (j < text.length() && node.children[text.charAt(j) - 'a'] != null) {
                node = node.children[text.charAt(j) - 'a'];
                if (node.isEndOfWord) {
                    result.add(new int[]{i, j});
                }
                j++;
            }
        }

        // Convert list to array
        return result.toArray(new int[0][]);
    }

    // Trie Node definition
    class Trie {
        Trie[] children = new Trie[26];
        boolean isEndOfWord = false;
    }
}
