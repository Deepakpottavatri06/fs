
import java.util.Arrays;
import java.util.Scanner;

/*Mr Suresh is working with the plain text P, a list of words w[], 
He is converting P into C [the cipher text], C is valid cipher of P, 
if the following rules are followed:
	- The cipher-text C is a string ends with '$' character.
	- Every word, w[i] in w[], should be a substring of C, and 
	the substring should have $ at the end of it.

Your task is to help Mr Suresh to find the shortest Cipher C,  
and return its length.

Input Format:
-------------
Single line of space separated words, w[].

Output Format:
--------------
Print an integer result, the length of the shortest cipher.


Sample Input-1:
---------------
kmit it ngit

Sample Output-1:
----------------
10

Explanation:
------------
A valid cipher C is "kmit$ngit$".
w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
w[1] = "it", the substring of C, and the '$' is the end character after "it"
w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


Sample Input-2:
---------------
ace

Sample Output-2:
----------------
4

Explanation:
------------
A valid cipher C is "ace$".
w[0] = "ace", the substring of C, and the '$' is the end character after "ace"

 */

class TrieNode{
	boolean end;
	TrieNode child [];
	TrieNode(){
		end = false;
		child = new TrieNode[26];
	}	
}
class Trie{
	public TrieNode root;
	Trie(){
		root = new TrieNode();
	}

	boolean insertReversed(String word){
		
		TrieNode curr = root;
		boolean isnew = false;
		for(int i = 0; i < word.length() ; i++){
			char c = word.charAt(i);
			if(curr.child[c-'a']==null){
				isnew = true;
				curr.child[c-'a'] = new TrieNode();
			}
			curr = curr.child[c-'a'];
		}
		curr.end = true;
		return isnew;
	}
	

}
public class Shortest_Cipher {
    public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		String inp [] = cin.nextLine().split(" ");
		System.out.println(find(inp));
		cin.close();
	}
	static int find(String inp []){
		Arrays.sort(inp,(a,b)->b.length()-a.length());
		int shortestCipherLen = 0;
		Trie t = new Trie();
		for(String i : inp){
			boolean isnew = t.insertReversed(new StringBuilder(i).reverse().toString());
			if(isnew){
				shortestCipherLen += (i.length()) + 1;
			}
		}
		return shortestCipherLen;
	}
}
