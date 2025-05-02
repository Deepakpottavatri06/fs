/*Coach Arjun is training a cricket team and is experimenting with a new fitness 
evaluation drill. He provided the fitness scores of N players in the team. As 
part of the drill, he asked the team manager to keep track and perform these 
two specific operations on the players' fitness scores:

1. bestFitness(start, end) - Report the maximum fitness score among the players 
   whose jersey numbers lie between the positions start and end inclusive.
2. improveFitness(index, newScore) - Update the fitness score of the player at 
   the specific index position with a new fitness score newScore.

Your task is to efficiently handle these requests by using a Segment Tree data structure.

Input Format:  
-------------
Line-1: Two integers N and Q, representing the number of players and the total 
        number of queries respectively.
Line-2: N space-separated integers representing the initial fitness scores of 
        the players.
The next Q lines: Each line contains three integers: 
    - First integer (option) specifies the type of query:
      - If option = 1, the next two integers (start, end) represent the range 
        of jersey numbers (inclusive) for which to report the maximum fitness.
      - If option = 2, the next two integers (index, newScore) represent the 
        player's index to update and their new fitness score.

Output Format:  
--------------
- Output an integer value for every bestFitness query, representing the maximum 
  fitness score within the specified range.

Sample Input:  
-------------

8 5
1 2 13 4 25 16 17 28
1 2 6        //bestFitness
1 0 7        //bestFitness
2 2 18       //improveFitness
2 4 36       //improveFitness
1 2 7        //bestFitness


Sample Output:  
--------------
25
28
36
 */
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
    int max(int v, int treeL, int treeR , int rangeL , int rangeR){
        if(rangeL>rangeR){
            return Integer.MIN_VALUE;
        }
        if(rangeL==treeL && rangeR==treeR){
            return tree[v];
        }
        int mid = (treeL + treeR)/2;
        return Math.max(max(v*2 +1 ,treeL,mid,rangeL,Math.min(rangeR,mid)),max(v*2 + 2,mid+1,treeR,Math.max(rangeL,mid+1),rangeR));
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
