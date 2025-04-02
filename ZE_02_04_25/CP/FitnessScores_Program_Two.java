import java.util.*;

class SegmentTree{
    int n ;
    int tree [];
    SegmentTree(int arr []){
        n = arr.length;
        tree = new int[n*4];
        build(arr,0,0,n-1);
    }
    void build(int arr[], int v, int l, int r){
        if(l==r){
            tree[v] = arr[l];
        }
        else{
            int mid = (l+r)/2;
            build(arr,v*2 + 1,l,mid);
            build(arr,v*2 + 2,mid+1,r);
            tree[v] = Math.max(tree[v*2 + 1],tree[v*2 + 2]);
        }
    }
    void update(int v, int l, int r ,int pos , int new_val){
        if(l==r){
            tree[v] = new_val;
        }
        else{
            int mid = (l+r)/2;
            if(pos<=mid){
                update(v*2 + 1,l,mid,pos,new_val);
            }
            else{
                update(v*2 + 2,mid+1,r,pos,new_val);
            }
            tree[v] = Math.max( tree[v*2 + 1] , tree[v*2 + 2]);
        }
    }
    int max(int v, int treeL, int treeR , int l , int r){
        if(l>r){
            return Integer.MIN_VALUE;
        }
        if(l==treeL && r==treeR){
            return tree[v];
        }
        int mid = (treeL + treeR)/2;
        return Math.max(max(v*2 +1 ,treeL,mid,l,Math.min(r,mid)),max(v*2 + 2,mid+1,treeR,Math.max(l,mid+1),r));
    }
    
}

public class FitnessScores_Program_Two{
    public static void main(String [] args){
         Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int q = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        cin.nextLine();
        int query [][] = new int[q][3];
        for(int i = 0; i < q ; i++){
            query[i] = new int[]{cin.nextInt(),cin.nextInt(),cin.nextInt()};
        }
        
        solve(n,q,arr,query);
        cin.close();
    }
    static void solve(int n, int q, int arr [], int query [][]){
        SegmentTree tree = new SegmentTree(arr);

        // System.out.println("Here");
        for(int i = 0; i < q; i++){
            if(query[i][0]==1){
                System.out.println(tree.max(0,0,tree.n-1,query[i][1],query[i][2]));
            }
            else{
                tree.update(0,0,tree.n-1,query[i][1],query[i][2]);
            }
        }
        
    }
}
