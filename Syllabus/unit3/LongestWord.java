import java.util.*;

// Main class
public class LongestWord {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String dict[] = sc.nextLine().split(" ");
        Solution sol = new Solution();
        System.out.println(sol.longestWord(dict));
    }
}

// Solution class
class Solution {
    Trie root = new Trie();  // root of Trie
    String res = "";         // stores result word

    public String longestWord(String[] words) {
        for (String word : words) {
            addWord(word);
        }
        for (String word : words) {
            searchPrefix(word);
        }
        return res;
    }

    // Add word to Trie
    private void addWord(String word) {
        Trie cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new Trie();
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    // Check if all prefixes of word exist in Trie
    private void searchPrefix(String word) {
        Trie cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.children[c - 'a'];
            if (!cur.isWord) return; // prefix not found
        }
        // Update result if current word is better
        if (word.length() > res.length() ||
            (word.length() == res.length() && word.compareTo(res) < 0)) {
            res = word;
        }
    }
}

// Trie node class
class Trie {
    Trie[] children = new Trie[26];  // for 'a' to 'z'
    boolean isWord = false;          // flag to mark end of word
}
