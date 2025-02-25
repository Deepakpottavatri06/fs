
/*Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- The string always starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Example 1:
Input: 4(2(3)(1))(6(5))
Output: [4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Example 2:
Input: 4(2(3)(1))(6(5)(7))
Output: [4, 2, 6, 3, 1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \   / \
    3   1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.
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

public class SpyNetwork {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.nextLine();
        System.out.println(levelOrder(inp));
        cin.close();
    }

    static List<Integer> levelOrder(String inp) {
        List<Integer> levelorderList = new ArrayList<>();
        int ind[] = new int[] { 0 };
        // Node root = buildTree(inp, ind);
        Node root = buildTreeStack(inp, ind);
        preOrder(root);
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            levelorderList.add(temp.val);
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return levelorderList;
    }

    static Node buildTree(String inp, int ind[]) {
        // System.out.println("Ind :"+ind[0]);
        if (ind[0] >= inp.length()) {
            return null;
        }
        int num = 0;
        int sign = 1;
        if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '-') {
            sign = -1;
            ind[0]++;
        }
        while ((ind[0] < inp.length()) && Character.isDigit(inp.charAt(ind[0]))) {
            num = num * 10 + Integer.parseInt(inp.charAt(ind[0]) + "");
            ind[0]++;
        }
        num = num * sign;

        Node root = new Node(num);

        if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == ')') {
            ind[0]++;
            return root;
        }
        if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '(') {
            ind[0]++;
            root.left = buildTree(inp, ind);
        }
        if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '(') {
            ind[0]++;
            root.right = buildTree(inp, ind);
        }
        ind[0]++;
        return root;
    }

    static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    static Node buildTreeStack(String inp, int ind[]) {
        int num = 0;
        int sign = 1;
        if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '-') {
            sign = -1;
            ind[0]++;
        }
        while ((ind[0] < inp.length()) && Character.isDigit(inp.charAt(ind[0]))) {
            num = num * 10 + Integer.parseInt(inp.charAt(ind[0]) + "");
            ind[0]++;
        }
        num = num * sign;
        Node root = new Node(num);
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while ((ind[0]<inp.length()) && !stack.isEmpty()) {
            if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '(') {
                 num = 0;
                 sign = 1;
                ind[0]++;
                if ((ind[0] < inp.length()) && inp.charAt(ind[0]) == '-') {
                    sign = -1;
                    ind[0]++;
                }
                while ((ind[0] < inp.length()) && Character.isDigit(inp.charAt(ind[0]))) {
                    num = num * 10 + Integer.parseInt(inp.charAt(ind[0]) + "");
                    ind[0]++;
                }
                num = num*sign;
                stack.push(new Node(num));
            }
            else if((ind[0] < inp.length()) && inp.charAt(ind[0]) == ')') {
                Node child = stack.pop();
                if(stack.peek().left!=null){
                    stack.peek().right = child;
                }
                else{
                    stack.peek().left = child;
                }
                ind[0]++;
            }
        }
        return root;
    }

}