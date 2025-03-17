/*Imagine you are an explorer who stumbles upon two ancient treasure chests. 
However, these aren’t ordinary chests—the coins inside are arranged in a mysterious, 
branching order. In each chest, the coins are hidden in secret compartments where 
lower valued coins are tucked away on one side and higher valued coins are placed 
on the opposite side. To unlock the legendary vault of riches, you must select one 
coin from the first chest and one from the second chest such that their magical 
values add up to a secret key number. 
Your challange is to return true if you can unlock the legendary vault of riches, 
otherwise false.

Example 1
----------
Input=
2 1 4
1 0 3
5

Output=
true

Chest A:
    2
   / \
  1   4

Chest B:
    1
   / \
  0   3

Explanation:Choosing the coin with value 2 from Chest A and the coin with value 3 
from Chest B adds up to 5, unlocking the vault

Example 2:
----------
Input=
0 -10 10
5 1 7 0 2
18

Output=
false

Chest A:
    0
   / \
-10   10

Chest B:
      5
     / \
    1   7
   / \
  0   2

Explanation: No combination of one coin from Chest A and one coin from Chest B 
sums to 18, so the vault remains sealed.
 */
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
    //write your code here
        if(root1==null || root2==null){
            return false;
        }
        if(root1.val+root2.val==target){
            return true;
        }
        
        // if((root1.val+root2.val)>target){
        //         return twoSumBSTs(root1.left,root2,target) ||
        //          twoSumBSTs(root1,root2.left,target);
        // }
        // else{

        //     return twoSumBSTs(root1.right,root2,target) ||
        //          twoSumBSTs(root1,root2.right,target);
        // }
        if(search(root2,target-root1.val)) return true;
        return  twoSumBSTs(root1.left,root2,target) ||
                 twoSumBSTs(root1.right,root2,target);
    
    }
    static boolean search(TreeNode root2, int t){
        if(root2==null){
            return false;
        }
        
        if(root2.val==t){
            return true;
        }
        
        if(root2.val<t){
            return search(root2.right,t);
        }
        return search(root2.left,t);
    }
}


public class TwoSumBST {
    public static void main(String[] args) {
        Scanner cin  = new Scanner(System.in);
        String inp1[] = cin.nextLine().split(" ");
        String inp2[] = cin.nextLine().split(" ");
        int target = cin.nextInt();
        int levelorder1[] = new int[inp1.length];
        for (int i = 0; i < inp1.length; i++) {
            levelorder1[i] = Integer.parseInt(inp1[i]);
        }
        int levelorder2[] = new int[inp2.length];
        for (int i = 0; i < inp2.length; i++) {
            levelorder2[i] = Integer.parseInt(inp2[i]);
        }
        TreeNode root1 = construct(levelorder1);
        TreeNode root2 = construct(levelorder2);
        Solution mysol = new Solution();
        System.out.println(mysol.twoSumBSTs(root1, root2, target));
        cin.close();

    }
    static TreeNode construct(int[] levelorder) {
        int n = levelorder.length;
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int ind = 1;
        while ((ind<n) && !q.isEmpty()) {

            TreeNode temp = q.poll();
            if (levelorder[ind] != -1) {
                temp.left = new TreeNode(levelorder[ind]);
                q.add(temp.left);
            }
            ind++;
            if(ind < n && levelorder[ind]!=-1){
                temp.right = new TreeNode(levelorder[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }
    
}