

import java.util.*;

/*The Indian Army has established multiple military camps at strategic locations 
along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
a hierarchical structure, with a main base camp acting as the root of the network.

To fortify national security, the Government of India has planned to deploy 
a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
boundary of the network.

Structure of Military Camps:
    - Each military camp is uniquely identified by an integer ID.
    - A camp can have at most two direct connections:
        - Left connection → Represents a subordinate camp on the left.
        - Right connection → Represents a subordinate camp on the right.
    - If a military camp does not exist at a specific position, it is 
      represented by -1
	
Mission: Deploying the S.H.I.E.L.D.
Your task is to determine the list of military camp IDs forming the outer 
boundary of the military network. This boundary must be traversed in 
anti-clockwise order, starting from the main base camp (root).

The boundary consists of:
1. The main base camp (root).
2. The left boundary:
    - Starts from the root’s left child and follows the leftmost path downwards.
    - If a camp has a left connection, follow it.
    - If no left connection exists but a right connection does, follow the right connection.
    - The leftmost leaf camp is NOT included in this boundary.
3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
4. The right boundary (in reverse order):
    - Starts from the root’s right child and follows the rightmost path downwards.
    - If a camp has a right connection, follow it.
    - If no right connection exists but a left connection does, follow the left connection.
    - The rightmost leaf camp is NOT included in this boundary.


Input Format:
-------------
space separated integers, military-camp IDs.

Output Format:
--------------
Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


Sample Input-1:
---------------
5 2 4 7 9 8 1

Sample Output-1:
----------------
[5, 2, 7, 9, 8, 1, 4]


Sample Input-2:
---------------
11 2 13 4 25 6 -1 -1 -1 7 18 9 10

Sample Output-2:
----------------
[11, 2, 4, 7, 18, 9, 10, 6, 13]


Sample Input-3:
---------------
1 2 3 -1 -1 -1 5 -1 6 7

Sample Output-3:
----------------
[1, 2, 7, 6, 5, 3]
 */


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

public class Boundary {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        List<Integer> res = new ArrayList<>();
        findBoundary(root,res);
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
    static void findBoundary(Node root, List<Integer> res){
        if(root==null){
            return ;
        }
        res.add(root.val);
        leftBoundary(root.left,res);
        leavesBoundary(root,res);
        rightBoundary(root.right,res);
    }
    static void leftBoundary(Node root,List<Integer> res){
        if(root==null || (root.left==null && root.right==null) ){
            return;
        }
        res.add(root.val);
        if(root.left!=null){
            leftBoundary(root.left, res);
        }
        else if(root.right!=null){
            leftBoundary(root.right, res);
        }
    }
    static void leavesBoundary(Node root, List<Integer> res){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            res.add(root.val);
        }
        leavesBoundary(root.left, res);
        leavesBoundary(root.right, res);
    }
    static void rightBoundary(Node root, List<Integer> res){
        if(root==null || (root.left==null && root.right==null)){
            return ;
        }
        if(root.right!=null){
            rightBoundary(root.right, res);
        }
        else {
            rightBoundary(root.left, res);
        }
        res.add(root.val);
    }
}
