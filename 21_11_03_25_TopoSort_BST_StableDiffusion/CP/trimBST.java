/*Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low, high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
   /
  1
 */
import java.util.*;


class Node{
    int val;
    Node left;
    Node right;
    Node(int v){
        this.val = v;
        left = null;
        right = null;
    }
}
public class trimBST{
    static void levelOrderTraversal(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        // List<List<Integer>> res = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            // List<Integer> t = new ArrayList<>(); 
            for (int i = 0; i < n; i++) {
                Node temp = q.poll();
                System.out.print(temp.val + " ");
                // t.add(temp.val);
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            // res.add(t);
            // System.out.print(t+" ");
        }
        // System.out.println(res);

    }
    public static void main(String [] args){
        Scanner cin =  new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int range [] = new int[]{cin.nextInt(), cin.nextInt()};
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length;  i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        Node root = buildBST(arr,inp.length);
        root = removeManuscripts(root,range);
        levelOrderTraversal(root);
        cin.close();
        
    }
    static Node removeManuscripts(Node root, int range[]){
        if(root==null){
            return null;
        }
        
        if(root.val<range[0]){
            return removeManuscripts(root.right,range);
        }
        
        if(root.val>range[1]){
            return removeManuscripts(root.left,range);
        }
        root.left = removeManuscripts(root.left,range);
        root.right = removeManuscripts(root.right,range);
        return root;
        
        
    }
    static Node buildBST(int arr[], int n){
        Node root = null;
        
        for(int i = 0; i < n; i++){
            if(root==null){
                root = new Node(arr[i]);
                continue;
            }
            Queue<Node> q = new ArrayDeque<>();
            q.add(root);
            // System.out.println(arr[i]);
            while(!q.isEmpty()){
                Node temp = q.poll();
                if(temp.val<arr[i]){
                    if(temp.right==null){
                        Node newNode = new Node(arr[i]);
                        temp.right = newNode;
                        break;
                    }
                    else{
                        q.add(temp.right);
                    }
                }
                else if(temp.val>arr[i] ){
                    if(temp.left==null){
                        Node newNode = new Node(arr[i]);
                        temp.left = newNode;
                        break;
                    }
                    else{
                        q.add(temp.left);
                    }
                }
                
            }
        }
        
        return root;
        
    }
}