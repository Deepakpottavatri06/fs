/*You are developing an application for a garden management system where each tree 
in the garden is represented as a binary tree structure. The system needs to 
allow users to plant new trees in a systematic way, ensuring that each tree is 
filled level by level.

A gardener wants to:
 - Plant trees based on user input.
 - Ensure trees grow in a balanced way by filling nodes level by level.
 - Inspect the garden layout by performing an in-order traversal, which helps 
   analyze the natural arrangement of trees.

Your task is to implement a program that:
    - Accepts a list of N tree species (as integers).
    - Builds a binary tree using level-order insertion.
    - Displays the in-order traversal of the tree.

Input Format:
-------------
- An integer N representing the number of tree plants.
- A space-separated list of N integers representing tree species.

Output Format:
--------------
A list of integers, in-order traversal of tree.


Sample Input:
-------------
7
1 2 3 4 5 6 7

Sample Output:
--------------
4 2 5 1 6 3 7


Explanation:
------------
The tree looks like this:

        1
       / \
      2   3
     / \  / \
    4   5 6  7
The in order is : 4 2 5 1 6 3 7
 */
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

public class P6{
    
    public static void main(String  [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int [n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
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
                Node left = new Node(arr[ind++]);
                temp.left = left;
                q.add(left);
            }
            
            if((ind<n)&&arr[ind]!=1){
                Node right = new Node(arr[ind++]);
                temp.right = right;
                q.add(right);
            }
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