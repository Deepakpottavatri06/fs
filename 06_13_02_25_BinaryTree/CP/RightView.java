package F_13_02_25.CP;
/*Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 2 4 3 2

Sample Output-2:
----------------
[3, 4, 2]
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

public class RightView {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        List<Integer> res = new ArrayList<>();
        rightSideView(root, 0, res);
        System.out.println(res);
        cin.close();
    }

    static Node construct(int[] levelorder) {
        int n = levelorder.length;
        Node root = new Node(levelorder[0]);
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int ind = 1;
        while ((ind < n) && !q.isEmpty()) {

            Node temp = q.poll();
            if (levelorder[ind] != -1) {
                temp.left = new Node(levelorder[ind]);
                q.add(temp.left);
            }
            ind++;
            if (ind < n && levelorder[ind] != -1) {
                temp.right = new Node(levelorder[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }

    static void rightSideView(Node root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        // System.out.print(root.val+" ");
        if (res.size() == depth) {
            res.add(root.val);
        }
        rightSideView(root.right, depth + 1, res);
        rightSideView(root.left, depth + 1, res);
    }
}
