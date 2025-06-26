/*Radhika is leading a disaster response mission and must divide her team’s 
resources into two operational units: Unit Alpha and Unit Beta.

Each resource is represented by a positive integer denoting its capacity score, 
given in the array resources[].

To ensure both units are equally capable, Radhika must split the resources 
into two non-empty ordered groups such that:
	- Every resource is assigned to exactly one unit
	- The sum of capacity scores in both units is at least k

A split is called a valid deployment plan (or great partition) if both groups have 
a total capacity ≥ k. Since the number of deployment plans can be huge, return 
the number of distinct valid deployment plans modulo 10⁹ + 7.

Two plans are considered distinct if at least one resource is assigned to a different unit in each plan.

Input Format
------------
First line: An integer k — the minimum required capacity per unit.
Second line: An integer n — the number of resources.
Third line: n space-separated integers representing resource capacities.

Output Format
---------------
A single integer: the number of valid deployment plans modulo 10⁹ + 7

Constraints
-----------
1 ≤ n, k ≤ 1000
1 ≤ resources[i] ≤ 10

Sample Input
--------------
4
4
1 2 3 4

Sample Output
---------------
6

Explanation
------------
These are the valid deployment plans (where each group has sum ≥ 4):
[1, 2, 3] and [4]
[1, 3] and [2, 4]
[1, 4] and [2, 3]
[2, 4] and [1, 3]
[2, 3] and [1, 4]
[4] and [1, 2, 3]


Sample Input
--------------
4
4
1 2 1 2

Sample Output
---------------
0
 */
import java.util.*;

public class TwoSplit{
    static public Map<String,Integer> map;
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int k = cin.nextInt();
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(find(arr,n,k));
        cin.close();
    }
    static int find(int arr[], int n, int k){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        map = new HashMap<>();
        
        return recur(arr,0,k,sum,0)% 1000000007;
    }
    static int recur(int arr[], int ind, int k, int sum,int curr){
        if(ind==arr.length){
            return (sum-curr>=k && curr>=k)? 1 :0;
        }
        // System.out.println("val : "+arr[ind]+" curr :"+curr);
        String key = ind+"-"+curr;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int take = 0;
        int nottake = 0;
        
        take = recur(arr,ind+1,k,sum,curr+arr[ind]);
        nottake = recur(arr,ind+1,k,sum,curr);
        map.put(key,(take+nottake)%1000000007);
        return (take + nottake)%1000000007;
        
    }
}