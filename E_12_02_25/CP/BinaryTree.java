/*Given the in-order and post-order traversals of a binary tree, construct 
the original binary tree. For the given Q number of queries, 
each query consists of a lower level and an upper level. 
The output should list the nodes in the order they appear in a level-wise.

Input Format:
-------------
An integer N representing the number nodes.
A space-separated list of N integers representing the similar to in-order traversal.
A space-separated list of N integers representing the similar to post-order traversal.
An integer Q representing the number of queries.
Q pairs of integers, each representing a query in the form:
Lower level (L)
Upper level (U)

Output Format:
For each query, print the nodes in order within the given depth range

Example
Input:
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
2
1 2
2 3
Output:
[1, 2, 3]
[2, 3, 4, 5, 6, 7]

Explanation:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
Query 1 (Levels 1 to 2): 1 2 3
Query 2 (Levels 2 to 3): 2 3 4 5 6 7
 */
import java.util.*;

class Node{
    int val ;
    Node left;
    Node right;
    Node(int val){
        this.val  = val;
        this.left = null;
        this.right = null;
    }
}
public class BinaryTree{
    public static int postorderInd = 0;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        postorderInd = n-1;
        int inorder [] = new int[n];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = cin.nextInt();
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int postorder []= new int[n];
        for (int i = 0; i < postorder.length; i++) {
            postorder[i] = cin.nextInt();
        }
        int q = cin.nextInt();
        int queries  [][] = new int[q][];
        for (int i = 0; i < queries.length; i++) {
            queries[i] = new int[]{ cin.nextInt(),cin.nextInt()};
        }
        
        Node root = constructTree(map,inorder,postorder,0,n-1);
        solveQueries(root,q,queries);
        // inorderTraversal(root);
        cin.close();
    }
    public static Node constructTree(Map<Integer,Integer> map, int [] inorder, int [] postorder,int left, int right){
        if(left>right) return null;

        Node root = new Node(postorder[postorderInd--]);

        root.right = constructTree(map,inorder, postorder,map.get(root.val)+1 , right);
        root.left = constructTree(map, inorder, postorder, left, map.get(root.val)-1);
        return root;
    }
    public static void inorderTraversal(Node root){
        if(root==null){
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val+" ");
        inorderTraversal(root.right);
    }
    public static void solveQueries(Node root,int q, int [][] queries){
        List<List<Integer>> totalValues = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node temp = queue.poll();
                t.add(temp.val);
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
            totalValues.add(t);
        }
        // System.out.println("Total list "+ totalValues);
        for(int i[] : queries){
            List<Integer> queryList = new ArrayList<>();
            for (int j = i[0]; j <= i[1]; j++) {
                queryList.addAll(totalValues.get(j-1));
            }
            System.out.println(queryList);
        }
    }
}