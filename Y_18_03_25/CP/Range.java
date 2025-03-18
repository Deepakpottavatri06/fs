import java.util.*;
class Node{
    int val;
    Node left;
    Node right;
    Node(int v){
        this.val = v;
        left = null;
        right = null;
    }
}
public class Range{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String arc [] = cin.nextLine().split(" ");
        String que [] = cin.nextLine().split(" ");
        int archive [] = new int[arc.length];
        for(int i = 0; i < arc.length ; i++){
            archive[i] = Integer.parseInt(arc[i]);
        }
        int query [] = new int[que.length];
        for(int i = 0; i < que.length; i++ ){
            query[i] = Integer.parseInt(que[i]);
        }
        Node root = buildBST(archive,arc.length);
        System.out.println(findRanges(root,query));
        cin.close();
    }
    static String findRanges(Node root, int query []){
        // List<List<Integer>> res = new ArrayList<>();
        int res[][] = new int[query.length][2];
        
        for(int i = 0; i < query.length ; i++){
            int temp [] = new int[]{-1,-1};
            dfs(root,query[i],temp);
            res[i] = temp;
        }
        return Arrays.deepToString(res);
    }
    static void dfs(Node root, int q, int res[]){
        if(root==null){
            return;
        }
        if(root.val==q){
            res[0] = q;
            res[1] = q;
            return ;
        }
        if(root.val>q){
            // res[1] = (res[1]==-1)? root.val : Math.min(res[1],root.val);
            res[1] = root.val;
            dfs(root.left,q,res);
        }
        else{
            // res[0] = (res[0]==-1)? root.val : Math.max(res[0],root.val);
            res[0] = root.val;
            dfs(root.right,q,res);
        }
    }
    static Node buildBST(int arr[], int n){
        Node root = null;
        
        for(int i = 0; i < n; i++){
            if(root==null){
                root = new Node(arr[i]);
                continue;
            }
            Queue<Node> q = new ArrayDeque<>();
            q.add(root);
            // System.out.println(arr[i]);
            while(!q.isEmpty()){
                Node temp = q.poll();
                if(temp.val<arr[i]){
                    if(temp.right==null){
                        Node newNode = new Node(arr[i]);
                        temp.right = newNode;
                        break;
                    }
                    else{
                        q.add(temp.right);
                    }
                }
                else if(temp.val>arr[i] ){
                    if(temp.left==null){
                        Node newNode = new Node(arr[i]);
                        temp.left = newNode;
                        break;
                    }
                    else{
                        q.add(temp.left);
                    }
                }
                
            }
        }
        
        return root;
        
    }
}