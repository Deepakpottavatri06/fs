/*There are a series of boxes and each box is labelled with a number.
Your task is to find the subsets of boxes such that addition of all 
the numbers written on each box makes an odd number.

Return only the number of such subsets exist.
Since the answer can be very large, return it modulo 10^9 + 7.

Input Format:
-------------
Line-1: an integer n represents the number of boxes
Line-2: n space seperated integers represents the numbers on the boxes.

Output Format:
--------------
return an integer represents number of subsets.

Sample Input-1:
---------------
3
1 5 7

Sample Output-1:
----------------
4

Explanation:
------------
subsets with odd sum are [1],[5],[7],[1,5,7].

Sample Input-2:
---------------
4
2 3 4 5

Sample Output-2:
----------------
6

Explanation:
-------------
subsets with odd sum are [3],[5],[2,3],[3,4],[4,5],[2,3,4].

 */
import java.util.*;

public class Subsets{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i< n; i++){
            arr[i] = cin.nextInt();
        }
        int odd = 0;
        int even = 1;
        int prefix = 0;
        int res= 0;
        for(int i: arr){
            prefix +=i;
            if(prefix%2==0){
                res += odd;
                even++;
            }
            else{
                res += even;
                odd++;
            }
        }
        System.out.println(res);
        
        cin.close();
    }
}