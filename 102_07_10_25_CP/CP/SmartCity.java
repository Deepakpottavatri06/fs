/*A city’s Smart Energy Controller must decide which power-saving plans to 
activate in a single day. Each plan connects to certain zones of the city 
and provides a specific energy-saving benefit (in kWh).

However, each zone can only participate in one plan at a time (to avoid power overlap).

You need to determine the maximum total energy saving that can be achieved by 
activating a valid combination of plans without any zone being used more than once.

Input Format
------------
Line-1: Two space separated integers N M: Total number of city zones, number of plans.
Next M lines: space separated integers of each plan, 
      where first integer is Number of Zones in the plan, followed by zones, and
      the last integer is energy-saving benefit.

Output Format
-------------
Return a single integer — the maximum achievable total energy saving (kWh).


Sample Input:
-------------
4 4
2 1 2 100
2 2 3 200
2 3 4 150
2 1 4 120

Sample Output:
--------------
320

Explanation:
------------
We can activate Plan-2 (zones 2,3) and Plan-4 (zones 1,4).
They do not overlap and give 200 + 120 = 320 kWh savings.
Activating any other combination gives less total saving.
 */
import java.util.*;

public class SmartCity {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int plan[][] = new int[m][];
        int benefit[] = new int[m];
        for(int i = 0; i < m; i++){
            int len = sc.nextInt();
            plan[i] = new int[len];
            for(int j = 0; j < len; j++){
                plan[i][j] = sc.nextInt();
            }
            benefit[i] = sc.nextInt();
        }
        System.out.println(find(plan,benefit,n,m));
        sc.close();
    }
    static int find(int plan[][], int benefit[], int n, int m){
        boolean used [] = new boolean[n+1];
        return recur(plan,0,benefit,used);
    }
    static int recur(int plan[][], int ind, int benefit[], boolean used[]){
        if(ind>=plan.length){
            return 0;
        }
        
        int skip = recur(plan,ind+1,benefit,used);
        int take = 0;
        if(canTake(used,plan[ind])){
            mark(used,plan[ind],true);
            take = benefit[ind] + recur(plan,ind+1,benefit,used);
            mark(used,plan[ind],false);
        }
        
        return Math.max(skip,take);
    }
    static boolean canTake(boolean used[],int plan[]){
        for(int i: plan){
            if(used[i]) return false;
        }
        return true;
    }
    static void mark(boolean used[], int plan[], boolean flag){
        for(int i: plan){
            used[i] = flag;
        }
    }
}