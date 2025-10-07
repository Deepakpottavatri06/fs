/*In a team of N players, each player's jersey has a number printed on it.
Jersey numbers may be repeated.

You will be given the list of jersey numbers of N players. 
You need to find out  number of subgroups of jersey numbers 
that are in strictly ascending order.

A subgroup is defined as the continuous list of jersey numbers.

Input Format:
-------------
Line-1: An integer N, number of players
Line-2: N space separated integers, jersey numbers

Output Format:
--------------
Print a long value as the result.


Sample Input-1:
---------------
6
2 4 6 5 5 7

Sample Output-1:
----------------
10

Explanation:
------------
The strictly ascending subgroups are the following:
- Subgroups of length 1: [2], [4], [6], [5], [5], [7].
- Subgroups of length 2: [2,4], [4,6], [5,7].
- Subgroups of length 3: [2,4,6].
The total number of subgroups is 6 + 3 + 1 = 10.


Sample Input-2:
---------------
5
2 4 6 8 10

Sample Output-2:
----------------
15
 */
import java.util.*;

public class JerseyNumber{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(find(arr,n));
        cin.close();
    }
    static int find(int arr[], int n){
        int l = 0,r = 0;
        int count = 0;
        while(r<n){
            count+= (r-l+1);
            if(r<n-1 && arr[r+1]<=arr[r]){
                l = r+1;
            }
            r++;
        }
        return count;
    }
}