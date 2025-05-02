/*Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.
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
public class Distance {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        int l = cin.nextInt();
        int r = cin.nextInt();
        Node root = construct(levelorder);
        // Node LCA = lca(root,l,r);
        // System.out.println(helper(LCA,l,r));
        System.out.println(lcaDistance(root,l,r,0));
        cin.close();
    }
    static int lcaDistance(Node root, int l ,int r, int depth){
        if(l==r){
            return 0;
        }
        if(root==null){
            return -1;
        }
        if(root.val==l || root.val==r){
            return depth;
        }
        int left = lcaDistance(root.left, l,r,depth+1);
        int right = lcaDistance(root.right,l,r,depth+1);
        if(left!=-1 && right!=-1){
            return (left+right-2*depth);
        }
        return (left!=-1)?left : right;
    }
    static int helper(Node LCA, int l , int r){
        int dist [] = new int[]{0,0};

        dist[0] = dfs(LCA,l,0);
        dist[1] = dfs(LCA,r,0);
        return dist[0] + dist[1];
        
    }
    static int dfs(Node root,int target, int depth){
       if(root==null){
        return -1;
       }
       if(root.val==target){
            return depth;
       }
       return Math.max(dfs(root.left, target, depth+1),dfs(root.right, target, depth+1));

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
    static Node lca(Node root ,int l ,int r){
        if(root==null){
            return null;
        }
        if(root.val==l || root.val==r){
            return root;
        }
        Node left = lca(root.left, l, r);
        Node right = lca(root.right,l,r);
        if(left!=null && right!=null){
            return root;
        }
        return (right==null)? left:right;
    }
}
