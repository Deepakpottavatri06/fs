/*Imagine you are designing a grand castle where each room holds a specific amount 
of treasure. The castle is built in a binary layout, meaning every room may lead 
to two adjacent wings—a left wing and a right wing. 

An "organized section" of the castle follows this rule: for any given room, 
every room in its left wing contains a treasure value that is strictly less 
than the current room’s value, and every room in its right wing contains a 
value that is strictly greater. Additionally, each wing must itself be organized
according to the same rule.

Your challenge is to determine the maximum total treasure (i.e., the sum of 
treasure values) that can be gathered from any such organized section of the castle.

Example 1:
input=
1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
output=
20

Castle:
          1
        /   \
       4     3
      / \   / \
     2   4 2   5
              / \
             4   6

Explanation: The best organized section starts at the room with a treasure value 
of 3, yielding a total treasure of 20.

Example 2:
input=
4 3 -1 1 2
output=
2

Castle:
    4
   /
  3
 / \
1   2

Explanation: The optimal organized section is just the single room with a 
treasure value of 2.

Example 3:
input=
-4 -2 -5
output=
0

Castle:
   -4
  /  \
-2   -5
 
Explanation: Since all rooms contain negative treasure values, no beneficial 
organized section exists, so the maximum total treasure is 0.

Constraints:

- The number of rooms in the castle ranges from 1 to 40,000.
- Each room’s treasure value is an integer between -40,000 and 40,000.
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

public class MaxTotalTreasure{
    public static void main(String [] args){
        Scanner cin  = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        int max [] = new int[]{0};
        findMax(root,max);
        System.out.println(max[0]);
        cin.close();
    }
    static int findMax(Node root, int max[]){
        if(root==null){
            return 0;
        }
        
        int l = findMax(root.left,max);
        int r = findMax(root.right,max);
        
        if(l==-1 || r==-1){
            return -1;
        }
        if(root.left!=null && root.val<=root.left.val){
            return -1;
        }
        
        if(root.right!=null && root.val>=root.right.val ){
            return -1;
        }
        
        int sum = l + r + root.val;
        max[0] = Math.max(max[0],sum);
        return sum;
        
        
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