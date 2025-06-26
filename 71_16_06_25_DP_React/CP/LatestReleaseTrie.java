import java.util.Scanner;

class TrieNode{
    TrieNode children [];
    boolean end;
    TrieNode(){
        children = new TrieNode[10];
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
        for(int i = 0; i < str.length(); i+=2){
            char c = str.charAt(i);
            int ind = c-'0';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }

        node.end = true;
    }
    String find(){
        TrieNode curr = root;
        String res = "";
        StringBuilder str = new StringBuilder();
        boolean valid = false;
        while(curr!=null){
            boolean check = false;
            for(int i = 9; i >=0; i--){
                if(curr.children[i]!=null){
                    if(i==0 && valid) break;
                    check = true;
                    valid = false; // found a non zero, so check for path
                    str.append(i).append("-");
                    
                    curr = curr.children[i];
                    break;
                }
            }
            if(!check) break;
            if(curr.end){
                res = str.substring(0,str.length()-1);
                System.out.println("res : "+res);
                valid = true;
            }
        }

        return res;
    }
    
}

public class LatestReleaseTrie {

    public static void main(String[] args) {
         try (Scanner sc = new Scanner(System.in)) {
            String[] input = sc.nextLine().split(" ");
            System.out.println(getRecentRelease(input));
        }
    }

    static String getRecentRelease(String inp[]){
        Trie t = new Trie();
        for(String i : inp){
            t.insert(i);
        }
        return t.find();
    }
}