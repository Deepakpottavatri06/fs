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
public class UpsideDown {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp []= cin.nextLine().split(" ");
        int levelorder [] = new int[inp.length];
        for (int i = 0; i < levelorder.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
    }
    
}
