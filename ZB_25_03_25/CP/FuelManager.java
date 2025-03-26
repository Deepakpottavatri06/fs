/*Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
powered by N series of fuel cells arranged in a row, where each fuel cell holds
a specific amount of energy measured in megajoules. Your job is to manage these 
fuel cells by performing two main operations:

Option 1: Calculate the total energy available in a consecutive block of fuel 
          cells between indices start and end (inclusive).
Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
          fuel cell when it's refilled.

Input Format:
-------------
Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
Line-2: N space separated integers.
next Q lines: Three integers option, start/index and end/newEnergy.

Output Format:
--------------
An integer result, for every totalEnergy.


Example 1:
-----------
Input:
8 5
1 2 13 4 25 16 17 8
1 2 6		//totalEnerge
1 0 7		//totalEnerge
2 2 18		//recharge
2 4 17		//recharge
1 2 7		//totalEnerge

Output:
75
86
80


Example 2:
----------
Input:
16 1
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
1 0 15

Output:
136


Constraints

- 1 <= N <= 3*10^4  
- -100 <= fuelCells[i] <= 100  
- 0 <= index < fuelCells.length  
- -100 <= newEnergy <= 100  
- 0 <= start <= end < fuelCells.length  
- At most 3*10^4 calls will be made to recharge and totalEnergy.

*/ 
import java.util.*;


class FenwickTrees {
        int bit [];
        int len;
        FenwickTrees(int n){
            bit = new int[n+1];
            len = n;
        }
        void update(int i, int val){
            while(i<=len){
                bit[i]+=val;
                i+=(i&-i);
            }
        }
        int prefixSum(int i){
            int val = 0;
            while(i>0){
                val +=bit[i];
                i-=(i&-i);
            }
            return val;
        }

        int rangeSum(int l , int r){
            return prefixSum(r) - prefixSum(l-1);

        }
        
    }

public class FuelManager{
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
            // String temp [] = cin.nextLine().split(" ");
            // query[i][0] = Integer.parseInt(temp[0]);
            // query[i][1] = Integer.parseInt(temp[1]);
            // query[i][2] = Integer.parseInt(temp[2]);
            query[i] = new int[]{cin.nextInt(),cin.nextInt(),cin.nextInt()};
        }
        // System.out.println();
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.deepToString(query));
        solve(n,q,arr,query);
        cin.close();
    }
    static void solve(int n, int q, int arr [], int query [][]){
        FenwickTrees binaryIT = new FenwickTrees(n);
        for(int i = 0; i < n ; i++){
            binaryIT.update(i+1,arr[i]);
        }
        // System.out.println("Here");
        for(int i = 0; i < q; i++){
            if(query[i][0]==1){
                System.out.println(binaryIT.rangeSum(query[i][1]+1,query[i][2]+1));
            }
            else{
                binaryIT.update(query[i][1]+1,query[i][2]-binaryIT.rangeSum(query[i][1]+1,query[i][1]+1));
                
            }
        }
        
    }
}