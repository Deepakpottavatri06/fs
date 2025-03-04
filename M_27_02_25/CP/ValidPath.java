/*Imagine you are navigating a maze where each path you take has a section with a 
code. The maze is structured as a series of interconnected rooms, 
represented as a tree structure. Each room in the maze has a code (integral value)
associated with it, and you are trying to check if a given sequence of code 
matches a valid path from the entrance to an exit. 

You are provide with the maze structure, where you have to build the maze and then,
you are provided with a series of space seperated digits, representing a journey 
starting from the entrance and passing through the rooms along the way. 
The task is to verify whether the path corresponding to this array of codes 
exists in the maze.

Example 1:
Input:
0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
0 1 0 1                         //path to verify

Output: true

Explanation:
               0
             /   \
            1     0
           / \    /
          0   1  0
           \  / \
            1 0  0

The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


Example 2:
Input:
1 2 3
1 2 3

output: false

Explanation:
The proposed path 1 → 2 → 3 does not exist in the maze, 
so it cannot be a valid path.
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

public class ValidPath{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        String path[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        
        System.out.println(findPath(root,path));
        cin.close();
    }
    static boolean findPath(Node root, String path[]){
        int p [] = new int[path.length];
        for(int i = 0;i<path.length; i++){
            p[i] = Integer.parseInt(path[i]);
        }
        return dfs(root,p,0);
    } 
    static boolean dfs(Node root, int p[], int ind){
        if(ind>=p.length){
            return true;
        }
        if(root==null){
            return false;
        }
        if(root.val!=p[ind]){
            return false;
        }
        // System.out.println("ind : "+p[ind]+" "+root.val);
        boolean left = dfs(root.left,p,ind+1);
        if(left){
            return left;
        }
        boolean right = dfs(root.right,p,ind+1);
        return right;
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