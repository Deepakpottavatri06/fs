/*// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 

// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014

// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6. */
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