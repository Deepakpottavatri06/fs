/*Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100
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
public class MaxProduct {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        int max [] = new int[]{Integer.MIN_VALUE};
        canBesplit(root,0,max);
        System.out.println(max[0]);
        cin.close();
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
    static void canBesplit(Node root, int sum,int max[]){
        if(root==null){
            return ;
        }
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        // System.out.println("The parent node :"+root.val + " "+"the left :"+left + " The right :"+right+" The Sum: "+sum);
        max[0] = Math.max(Math.max((left+root.val+sum)*right,(right+root.val+sum)*left), max[0]);
        canBesplit(root.left,root.val+right+sum, max);
        canBesplit(root.right, root.val+left+sum, max);

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
