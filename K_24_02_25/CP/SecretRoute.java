/*Question: Longest Secret Route in a Binary Tree

Balbir Singh has discovered a secret network represented as a binary tree.
Each node of the tree represents a checkpoint in the network.
Balbir's task is to find the longest path between any two checkpoints in the network.

The longest path may or may not pass through the root of the tree.
Help Balbir determine the length of this longest path, called the Longest Secret Route.
Input Format:

    A space-separated list of integers representing the level order traversal of the binary tree.
    -1 represents a null node (no child).

Output Format:

    Print a single integer representing the length of the longest path between any two nodes in the tree.

Constraints:

    The binary tree contains at least one node.
    Node values are integers.

Example Input-1:

1 2 3 4 5 -1 -1

Example Output-1:

3

Explanation:
The longest path is between nodes 4 and 5, passing through 2. The path length is 3.
Example Input-2:

1 2 3 -1 -1 4 5

Example Output-2:

3

Explanation:
The longest path is between nodes 4 and 5, passing through 3. The path length is 3.
Example Input-3:

1 -1 2 3 -1 -1 4

Example Output-3:

3

Explanation:
The longest path is between nodes 3 and 4, passing through 2. The path length is 3.
Test Cases:

Test Case 1:

Input:
1 2 -1 3 -1 4 -1

Output:
3

Test Case 2:

Input:
1 -1 2 -1 3 -1 4

Output:
3

Test Case 3:

Input:
1 2 3 -1 -1 4 -1

Output:
3

Test Case 4:

Input:
5 -1 -1

Output:
0

Test Case 5:

Input:
1 2 3 4 5 6 7

Output:
 */
import java.util.*;


public class SecretRoute {

    public static void main(String[] args) {
        Scanner cin  = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        int res [] = new int[]{0};
        res[0] = longestSecretRoute(root,res);
        System.out.print(res[0]);
        cin.close();
    }
    static int longestSecretRoute(Node root, int res[]){
        if(root==null){
            return 0;
        }
        int left = longestSecretRoute(root.left, res);
        int right = longestSecretRoute(root.right, res);
        res[0] = Math.max(left + right, res[0]);
        return Math.max(left, right) + 1; 
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
}
