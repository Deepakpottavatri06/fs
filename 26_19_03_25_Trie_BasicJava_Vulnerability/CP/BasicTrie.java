import java.util.*;

class TrieNode{
    TrieNode children [];
    boolean end;
    TrieNode(){
        children = new TrieNode[26];
        end = false;
    }
}

class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    void insert(String str){
        TrieNode node = root;
        for(char c: str.toCharArray()){
            int ind = c-'a';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }

        node.end = true;
    }
    boolean search(String str){
        TrieNode node = root;
        for(char c : str.toCharArray()){
            int ind = c - 'a';
            if(node.children[ind]==null){
                return false;
            }
            node = node.children[ind];  
        }
        return node.end;
    }
    
}
public class BasicTrie {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String a = "hello";
        Trie t = new Trie();
        t.insert(a);
        String b = "hello";
        System.out.println(t.search(b));
        cin.close();
    }   
}
