/*Balbir Singh is working with networked systems, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
a certain processing load (integer value).

Balbir has been given a critical task: split the network into two balanced 
sub-networks by removing only one connection (edge). The total processing 
load in both resulting sub-networks must be equal after the split.

Network Structure:
- The network of servers follows a binary tree structure.
- Each server is represented by an integer value, indicating its processing load.
- If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
Help Balbir Singh determine if it is possible to split the network into two equal 
processing load sub-networks by removing exactly one connection.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 2 4 3 2 -1 7

Sample Output-2:
----------------
false
 */
import java.util.*;
class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class EqualTree {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        System.out.println(canBesplit(root,0));
        cin.close();
    }
    static boolean canBesplit(Node root, int sum){
        if(root==null){
            return false;
        }
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        // System.out.println("The parent node :"+root.val + " "+"the left :"+left + " The right :"+right+" The Sum: "+sum);
        if(left>right){ 
            if(left == (root.val+right +sum)){
                return true;
            }
            return canBesplit(root.left,root.val+right+sum);
        }
        else if(left<right){
            if(right == (root.val+left + sum)){
                return true;
            }
            return canBesplit(root.right, root.val+left+sum);
        }
        return false;

    }
    static Node construct(int[] levelorder) {
        int n = levelorder.length;
        Node root = new Node(levelorder[0]);
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int ind = 1;
        while ((ind<n) && !q.isEmpty()) {

            Node temp = q.poll();
            if (levelorder[ind] != -1) {
                temp.left = new Node(levelorder[ind]);
                q.add(temp.left);
            }
            ind++;
            if(ind < n && levelorder[ind]!=-1){
                temp.right = new Node(levelorder[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }
    static int sumTree(Node root){
        if(root==null){
            return 0;
        }
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        return root.val + left + right;
    }
}
