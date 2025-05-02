package F_13_02_25.CP;
/*Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7
4 5 2 6 7 3 1

Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3
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

public class PrePost {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        String preorderString[] = cin.nextLine().split(" ");
        String postorderString[] = cin.nextLine().split(" ");
        int preorder[] = new int[preorderString.length];
        int postorder[] = new int[postorderString.length];
        for (int i = 0; i < preorder.length; i++) {
            preorder[i] = Integer.parseInt(preorderString[i]);
            postorder[i] = Integer.parseInt(postorderString[i]);
        }
        int preInd[] = new int[] { 0 };
        int n = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        Node root = construct(preorder, map, preInd, 0, n - 1);
        levelOrderTraversal(root);
        cin.close();
    }

    static Node construct(int[] preorder, Map<Integer, Integer> map, int preInd[], int l, int r) {
        if (l > r || preInd[0]>=preorder.length){
            return null;
        }

        Node root = new Node(preorder[preInd[0]++]);
        if(preInd[0]==preorder.length){
            return root;
        }
        int ind = map.get(preorder[preInd[0]]);
        if (ind <= r) {
            root.left = construct(preorder, map, preInd, l, ind);
            root.right = construct(preorder, map, preInd, ind + 1, r-1);
        }
        return root;
    }

    static void levelOrderTraversal(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> t = new ArrayList<>(); 
            for (int i = 0; i < n; i++) {
                Node temp = q.poll();
                // System.out.print(temp.val + " ");
                t.add(temp.val);
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            res.add(t);
            // System.out.println();
        }
        System.out.println(res);

    }
}
