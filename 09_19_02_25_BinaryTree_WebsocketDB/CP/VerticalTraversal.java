/*
Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]
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

class Pair{
    Node root;
    int col;
    Pair(Node r, int c){
        root = r;
        col = c;
    }
}
public class VerticalTraversal {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);   
        System.out.println(vtraversal(root));
        cin.close();
    }
    static List<List<Integer>> vtraversal(Node root){
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        // inorder(root,map,0);
        inorderBfs(root,map);
        for(Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }
    static void inorderBfs(Node root,Map<Integer,List<Integer>> map){
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root,0));
        while (!q.isEmpty()) {
            Pair temp  = q.poll();
            if(map.containsKey(temp.col)){
                map.get(temp.col).add(temp.root.val);
            }
            else{
                map.put(temp.col, new ArrayList<>());
                map.get(temp.col).add(temp.root.val);
            }
            if(temp.root.left!=null){
                q.add(new Pair(temp.root.left,temp.col-1));
            }
            if(temp.root.right!=null){
                q.add(new Pair(temp.root.right, temp.col+1));
            }

        }
    }
    static void inorder(Node root,Map<Integer,List<Integer>> map,int col){
       if(root==null){
         return;
       }
       if(map.containsKey(col)){
            map.get(col).add(root.val);
       }
       else{
            map.put(col, new ArrayList<>());
            map.get(col).add(root.val);
       }

       inorder(root.left, map, col-1);
       inorder(root.right, map, col+1);

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
