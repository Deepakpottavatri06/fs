/*You are an architect designing a street with houses represented as a 0-indexed array 
house_heights of n integers, where each element represents the height of a house. 
Additionally, a binary array visibility_mask of length n is provided, where 1 indicates 
a house that contributes to the neighborhood's skyline, and 0 indicates a house that does not.

For any house located at index i, you are tasked with determining the average 
skyline height within a k-radius neighborhood centered at i. The average skyline 
height is the average of all house heights between indices i - k and i + k (inclusive) 
that have a corresponding visibility value of 1 in the visibility_mask. 

If no houses with a visibility of 1 exist in the range, or if the range extends 
beyond the boundaries of the array, the average skyline height for that house is -1.

Return an array skyline_avgs of length n, where skyline_avgs[i] is the average 
skyline height for the neighborhood centered at index i.

Example 1:
Input:
house_heights = [10, 15, 20, 5, 30, 25, 40]
visibility_mask = [1, 0, 1, 1, 0, 1, 1]
k = 2

Output:
skyline_avgs = [-1, -1, 11, 16, 22, -1, -1]


Explanation:
- For index 0, there are less than k houses in the left neighborhood, 
  so skyline_avgs[0] = -1.
- For index 1, there are less than k houses in the left neighborhood, 
  so skyline_avgs[1] = -1.
- For index 2, the neighborhood is [10, 15, 20, 5, 30]. From the visibility_mask, 
  only the houses with heights [10, 20, 5] contribute to the skyline. The average 
  is (10 + 20 + 5) / 3 = 11.
- For index 3, the neighborhood is [15, 20, 5, 30, 25]. From the visibility_mask, 
  only the houses with heights [20, 5, 25] contribute. 
  The average is (20 + 5 + 25) / 3 = 16.
- For index 4, the neighborhood is [20, 5, 30, 25, 40]. From the visibility_mask, 
  only the houses with heights [20, 5, 25, 40] contribute. The average 
  is (20 + 5 + 25 + 40) / 4 = 22.
- For index 5, there are less than k houses in the right neighborhood, 
  so skyline_avgs[5] = -1.
- For index 6, there are less than k houses in the right neighborhood, 
  so skyline_avgs[6] = -1.

Sample Input:
3
50 60 70
1 1 1
1

Sample Output:
[-1, 60, -1]

Constraints:
1. n == house_heights.length == visibility_mask.length
2. 1 <= n <= 10^5
3. 0 <= house_heights[i] <= 10^5
4. visibility_mask[i] is either 0 or 1
5. 0 <= k <= n
 */

import java.util.*;

public class SkylineAverage {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        int arr [] = new int[n];
        int vis [] = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = cin.nextInt();
        }
        for (int i = 0; i < vis.length; i++) {
            vis[i] = cin.nextInt();
        }
        System.out.println(Arrays.toString(solvePrefixSum(n,k,arr,vis)));
        cin.close();
    }
    static int [] solvePrefixSum(int n , int k , int arr [],  int vis[]){
        int res [] = new int[n];
        int preSum [] =  new int[n];
        int preK [] =  new int[n];
        preSum[0] = vis[0]==0? 0:arr[0];
        preK[0] = vis[0]==0? 0:1;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + ((vis[i]==0)?0: arr[i]);
            preK[i] = preK[i-1] + (vis[i]==0?0:1);
        }
        // System.out.println(Arrays.toString(preSum));
        // System.out.println(Arrays.toString(preK));

        // int countK = 0;
        for(int i = 0; i < k ; i++){
            res[i] = -1;
            // countK += (vis[i]==0)? 0 : 1;
        }
        // int ind = 0;
        for(int i = k; i<n-k; i++){
            // System.out.println("preK  l and u : "+((i==k)?0:preK[i-k-1])+" "+(preK[i+k]));
            // System.out.println("preSum  l and u : "+((i==k)?0:preSum[i-k-1])+" "+(preSum[i+k]));
            int divisor = preK[i+k] - ((i==k)?0:preK[i-k-1]);
            int sum = preSum[i+k] - ((i==k)?0:preSum[i-k-1]);
            res[i] = (divisor==0)? -1:sum/divisor;
        }
        for(int i = n-k; i < n ; i++){
            res[i] = -1;
        }
        // System.out.println(Arrays.toString(res));
        return res;
    }
}

/*import java.util.*;

class SkylineAverage{

    static int[] getSkylineAverages(int[] house_heights, int[] visibility_mask, int n, int k){
        int skyline_avgs[] = new int[n];
        Arrays.fill(skyline_avgs, -1);
        int sum = 0;
        int count = 0;

        int l = 0;
        for(int r = 0; r < n; ++r){
            if(visibility_mask[r] == 1){
                sum += house_heights[r];
                count += 1;
            }
            if(r - l == 2 * k){
                if(count != 0){
                    skyline_avgs[r - k] = (int) sum / count;
                }
                if(visibility_mask[l] == 1){
                    sum -= house_heights[l];
                    count -= 1;
                }
                ++l;
            }
        }

        return skyline_avgs;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int house_heights[] = new int[n];
        int visibility_mask[] = new int[n];

        for(int i = 0; i < n; ++i){
            house_heights[i] = sc.nextInt();
        }

        for(int i = 0; i < n; ++i){
            visibility_mask[i] = sc.nextInt();
        }

        int skyline_avgs[] = getSkylineAverages(house_heights, visibility_mask, n, k);
        System.out.println(Arrays.toString(skyline_avgs));
        sc.close();
    }
} */