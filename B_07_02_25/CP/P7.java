// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7

import java.util.*;

class Node{
    int val ;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        left = null;
        right = null;
    }
}

public class P7{
    
    public static void main(String  [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int n = inp.length;
        
        int arr [] = new int [n];
        for(int i = 0; i < n ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        Node root = new Node(arr[0]);
        constructTree(root,arr,n);
        List<Integer> res= new ArrayList<>();
        levelOrder(root,res);
        for(int i : res){
            System.out.print(i+" ");
        }
        cin.close();
    }
    static void constructTree(Node root, int arr [], int n){
        if(root==null){
            return ;
        }
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int ind = 1;
        while(ind<n && !q.isEmpty()){
            Node temp = q.poll();
            if(arr[ind]!=-1){
                Node left = new Node(arr[ind]);
                temp.left = left;
                q.add(left);
            }
            ind++;
            if((ind<n) && arr[ind]!=-1){
                Node right = new Node(arr[ind]);
                temp.right = right;
                q.add(right);
            }
            ind++;
        }
    }
    static void levelOrder(Node root, List<Integer> res){
        if(root==null){
            return ;
        }
        
        levelOrder(root.left,res);
        res.add(root.val);
        levelOrder(root.right,res);
    }
}