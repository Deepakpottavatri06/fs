import java.util.*;


class Node{
    int val ;
    Node left;
    Node right;
    Node(int val){
        this.val  = val;
        this.left = null;
        this.right = null;
    }
}
public class InPreSpiralOrder {
    static int preorderInd = 0;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        preorderInd = 0;
        int inorder [] = new int[n];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = cin.nextInt();
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        int preorder []  = new int[n];
        for (int i = 0; i < preorder.length; i++) {
            preorder[i] = cin.nextInt();
        }
        int l = cin.nextInt();
        int r = cin.nextInt();
        Node root = constructTree(map,inorder,preorder,0,n-1);
        solveQueries(l,r,root);
        cin.close();

    }
    public static Node constructTree(Map<Integer,Integer> map, int [] inorder, int [] preorder,int left, int right){
        if(left>right) return null;

        Node root = new Node(preorder[preorderInd++]);

        root.left = constructTree(map, inorder, preorder, left, map.get(root.val)-1);
        root.right = constructTree(map,inorder, preorder,map.get(root.val)+1 , right);
        return root;
    }
    static void solveQueries(int l , int r , Node root){
        List<List<Integer>> totalValues = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node temp = queue.poll();
                t.add(temp.val);
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
            totalValues.add(t);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = l; i < r+1; i++) {
            
            if(i%2==0){
                res.addAll(totalValues.get(i-1).reversed());
            }
            else{
                res.addAll(totalValues.get(i-1));
            }
        }
        System.out.println(res);
    }

}
