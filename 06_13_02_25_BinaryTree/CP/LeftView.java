/*A software development company is designing a smart home automation 
system that uses sensor networks to monitor and control different devices 
in a house. The sensors are organized in a hierarchical structure, where each 
sensor node has a unique ID and can have up to two child nodes (left and right).

The company wants to analyze the left-most sensors in the system to determine
which ones are critical for detecting environmental changes. The hierarchy of 
the sensors is provided as a level-order input, where missing sensors are 
represented as -1.

Your task is to build the sensor network as a binary tree and then determine 
the left-most sensor IDs at each level.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the left-most sensor IDs at each level


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 2, 4]



Sample Input-2:
---------------
3 2 4 1 5

Sample Output-2:
----------------
[3, 2, 1]

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

public class LeftView {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        List<Integer> res = new ArrayList<>();
        leftSideView(root,0,res);
        System.out.println(res);
        cin.close();
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
    static void leftSideView(Node root,int depth ,List<Integer> res){
        if(root==null){
            return;
        }
        // System.out.print(root.val+" ");
        if(res.size()==depth){
            res.add(root.val);
        }

            leftSideView(root.left,depth+1,res);
            leftSideView(root.right,depth+1,res);
        
    }
}
