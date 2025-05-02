
/*Construct Tree from the given Level-order and In-order.
Compute the Depth and Sum of the Deepest nodes in the Binary tree

Input Format:
-------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the level-order traversal.

Output Format:
--------------
Print two values:
->The maximum number of levels.
->The sum of all node values at the deepest level.

Example:
-------------
Input:
11
7 8 4 2 5 9 11 10 1 6 3
1 2 3 4 5 6 7 9 8 10 11

Output:
6 11

Explanation:
The binary tree structure:
           1
         /   \
       2       3
      / \     /
     4   5   6
    /     \   
   7       9
    \       \
     8      10
            /
          11
Maximum Depth: 6
nodes at the Deepest Level (6): 11
Sum of nodes at Level 6: 11
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

public class InLevel {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        Map<Integer,Integer> map = new HashMap<>();
        int inorder[] = new int[n];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = cin.nextInt();
        }

        // List<Integer> levelorder = new ArrayList<>();
        int levelorder [] = new int[n];
        for (int i = 0; i < n; i++) {
            // levelorder.add(cin.nextInt());
            levelorder[i] = cin.nextInt();
        }
        for (int i = 0; i < levelorder.length; i++) {
            map.put(levelorder[i],i);
        }
        Node root = construct(inorder,map,0,n-1);
        compute(root);
        // levelOrderTraversal(root);
        cin.close();
    }

    static Node construct(int [] inorder , Map<Integer,Integer> map , int l , int r){
        if(l>r) return null;
        int rootInd = findMinInd(map,inorder,l,r);
        Node root = new Node(inorder[rootInd]);

        root.right = construct(inorder, map, l, rootInd-1);
        root.left = construct(inorder, map, rootInd+1,r);
        return root;
    }
    static int findMinInd(Map<Integer,Integer> map , int inorder [], int l , int r){
        int min = Integer.MAX_VALUE;
        int ind = l;
        for (int i = l; i <= r; i++) {
            if(map.get(inorder[i])<min){
                min = map.get(inorder[i]);
                ind = i;
            }
        }
        return ind;
    }
    

    static void levelOrderTraversal(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node temp = q.poll();
                System.out.print(temp.val + " ");
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            
            System.out.println();
        }
    }

    static void compute(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        int sum = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            sum = 0;
            for (int i = 0; i < n; i++) {
                Node temp = q.poll();
                sum += temp.val;
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            level++;
        }
        System.out.println(level + " " + sum);
    }
}


/*
 {4, 2, 5, 1, 6, 3, 7};
 {1, 3, 2, 7, 6, 5, 4};
 */