/*Imagine you're a botanist managing a long row of garden plots. Each plot is 
planted with a flower that has a unique color code represented by an integer. 
You are given an array garden of length n, where each element denotes the color 
of the flower in that plot, and an integer k which indicates the number of 
consecutive plots you will examine at a time. Your objective is to determine 
how many different flower colors appear in every contiguous block of k plots.

Return an array result where result[i] is the count of unique flower colors in 
the section of the garden from plot i to plot i + k - 1.

Example 1:

Input: 
7				\\n
1 2 3 2 2 1 3	\\array garden
3				\\k

Output: 
[3, 2, 2, 2, 3]

Explanation:

For plots 0 to 2: [1,2,3] → 3 unique colors
For plots 1 to 3: [2,3,2] → 2 unique colors
For plots 2 to 4: [3,2,2] → 2 unique colors
For plots 3 to 5: [2,2,1] → 2 unique colors
For plots 4 to 6: [2,1,3] → 3 unique colors
Example 2:

Input: 
7
1 1 1 1 2 3 4
4

Output: 
[1, 2, 3, 4]

Explanation:
For plots 0 to 3: [1,1,1,1] → 1 unique color
For plots 1 to 4: [1,1,1,2] → 2 unique colors
For plots 2 to 5: [1,1,2,3] → 3 unique colors
For plots 3 to 6: [1,2,3,4] → 4 unique colors

Constraints:
• 1 <= k <= garden.length <= 10^5
• 1 <= garden[i] <= 10^5 */
import java.util.*;

public class UniqueFlowers{
    public static void main(String [] args){
        Scanner cin  = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        int k = cin.nextInt();
        System.out.println(findUniqueFlowers(n,arr,k));
        cin.close();
    }
    static List<Integer> findUniqueFlowers(int n ,int arr[], int k){
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < k; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        res.add(map.size());
        for(int i = k ; i < n ; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
            map.put(arr[i-k],map.getOrDefault(arr[i-k],0)-1);
            if(map.get(arr[i-k])==0){
                map.remove(arr[i-k]);
            }
            res.add(map.size());
        }
        return res;
    }
}