import java.util.*;


public class SecretRoute {

    public static void main(String[] args) {
        Scanner cin  = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        int levelorder[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            levelorder[i] = Integer.parseInt(inp[i]);
        }
        Node root = construct(levelorder);
        int res [] = new int[]{0};
        res[0] = longestSecretRoute(root,res);
        System.out.print(res[0]);
        cin.close();
    }
    static int longestSecretRoute(Node root, int res[]){
        if(root==null){
            return 0;
        }
        int left = longestSecretRoute(root.left, res);
        int right = longestSecretRoute(root.right, res);
        res[0] = Math.max(left + right, res[0]);
        return Math.max(left, right) + 1; 
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
