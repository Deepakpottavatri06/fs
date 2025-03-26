/*Imagine you are the chief curator at a futuristic museum. The museum consists of
N exhibition halls arranged in a row, and each hall consists of certain number 
of rare artifacts. 
Your job is to keep track of the total number of artifacts on display and to 
manage special events that temporarily increase the artifact counts in a block 
of halls.

You need to support two types of operations:
1. Count Artifacts: Calculate the total number of artifacts displayed in the 
   exhibition halls between indices start and end (inclusive).  
2. Host Special Exhibition: For a given range of halls (from start to end), 
   increase the number of artifacts in each hall by a specified amount. 
   This simulates a special exhibition event that attracts additional artifacts.

Input Format:
-------------
Line 1: Two integers N and Q, where N is the number of exhibition halls and Q is the number of operations.
Line 2: N space-separated integers representing the initial artifact counts in each hall.
Next Q Lines: Each line contains a query in one of the following formats:
  - For a Count Artifacts query:  
    1 start end
  - For a Host Special Exhibition event:  
    2 start end increment

Output Format:
--------------
For every Count Artifacts query (operation 1), output an integer representing the total number of artifacts in the specified range.

Example
-------
Input:
8 5
1 2 13 4 25 16 17 8
1 2 6
1 0 7
2 2 4 3
2 5 7 2
1 2 7

Output:
75
86
98


Explanation:
- The initial artifact counts in the halls are: [1, 2, 13, 4, 25, 16, 17, 8].
- Query 1: 1 2 6 → Sum halls 2 to 6: 13 + 4 + 25 + 16 + 17 = 75.
- Query 2: 1 0 7 → Sum halls 0 to 7: 1 + 2 + 13 + 4 + 25 + 16 + 17 + 8 = 86.
- Query 3: 2 2 4 3 → A special exhibition increases artifact counts in halls 
  2, 3, and 4 by 3. New counts become: [1, 2, 16, 7, 28, 16, 17, 8].
- Query 4: 2 5 7 2 → Another event boosts halls 5, 6, and 7 by 2. New counts 
  become: [1, 2, 16, 7, 28, 18, 19, 10].
- Query 5: 1 2 7 → Sum halls 2 to 7: 16 + 7 + 28 + 18 + 19 + 10 = 98.


Constraints
- 1 ≤ N ≤ 3×10^4  
- -100 ≤ artifact count in each hall ≤ 100  
- 0 ≤ start ≤ end < N  
- -100 ≤ increment ≤ 100  
- At most 3×10^4 operations will be performed. */
import java.util.*;

class FenwickTrees{
    int bit [];
    int len ;
    FenwickTrees(int n){
        this.bit = new int[n+1];
        this.len = n;
    }
    
    void update(int i, int val){
        while(i<=len){
            bit[i]+=val;
            i += (i & -i);
        }
    }
    
    int prefixSum(int i){
        int val = 0;
        while(i>0){
            val +=bit[i];
            i -=(i & -i);
        }
        return val;
    }
    
    int rangeSum(int left , int right){
        return prefixSum(right) - prefixSum(left-1);
    }
    
    void updateTill(int i, int val, int end){
        for(int j = i; j <= end; j++){
            int ind = j;
            while(ind<=len) {
            bit[ind] += val;
            ind += (ind & -ind);
        }
        }
    }
    void initialize(int [] arr){
        for(int i = 1; i < len+1; i++){
            bit[i] = arr[i-1];
        }
    }
}

public class FuturisticMuseum{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int q = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        // cin.nextLine();
        int query [][] = new int[q][];
        for(int i = 0; i < q ; i++){
            // String temp [] = cin.nextLine().split(" ");
            // query[i] = new int[temp.length];
            // for( int j = 0; j < temp.length ; j++){
            //     query[i][j] = Integer.parseInt(temp[j]);
            // }
            int first = cin.nextInt();
            if(first==1){
                query[i] = new int[]{first,cin.nextInt(),cin.nextInt()};
            }
            else{
                 query[i] = new int[]{first,cin.nextInt(),cin.nextInt(),cin.nextInt()};
            }
            // query[i] = new int[]{cin.nextInt(),cin.nextInt(),cin.nextInt()};
        }
        // System.out.println(Arrays.deepToString(query));
        solve(n,q,arr,query);
        cin.close();
    }
    static void solve(int n , int q, int arr [], int query [][]){
        FenwickTrees binaryIT = new FenwickTrees(n);
        for(int i = 0; i < n ; i++){
            binaryIT.update(i+1,arr[i]);
        }
        
        // System.out.println(q);
        // binaryIT.initialize(arr);
        // System.out.println(Arrays.toString(binaryIT.bit));
        for(int i = 0; i < q; i++){
            if(query[i][0]==1){
                System.out.println(binaryIT.rangeSum(query[i][1]+1,query[i][2]+1));
            }
            else{
                // System.out.println("update");
                binaryIT.updateTill(query[i][1]+1,query[i][3],query[i][2]+1);
                    //   System.out.println(Arrays.toString(binaryIT.bit));
            }
        }
    }
}