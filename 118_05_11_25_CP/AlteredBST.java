import java.util.*;

/*
for your reference
class BinaryTreeNode{
	public int data; 
	public BinaryTreeNode left, right; 
	public BinaryTreeNode(int data){
		this.data = data; 
		left = null; 
		right = null; 
	}
}
*/
class Solution {
    int totalSum = 0;
    private void sum(BinaryTreeNode root){
        if(root==null){
            return;
        }
        sum(root.left);
        totalSum+=root.data;
        sum(root.right);
    }
    public BinaryTreeNode alterTree(BinaryTreeNode root) {
        // implement this method
        sum(root);
        // System.out.println(totalSum);
        return alter(root);
    }
    private BinaryTreeNode alter(BinaryTreeNode root) {
        // implement this method
        if(root==null){
            return null;
        }
        root.left = alter(root.left);
        int temp = root.data;
        totalSum -=root.data;
        root.data = totalSum+temp;
        root.right = alter(root.right);
        return root;
    }
    

	public void inOrder(BinaryTreeNode root){
        // implement this method
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
	}
}
