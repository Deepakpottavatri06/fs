/*You are organizing a grand parade where 'N' marching bands will move in a straight 
line. Each band must wear uniforms of exactly one color chosen from 'K' available 
colors. To keep the parade visually appealing and avoid monotony, 
you must follow this important guideline:

- No more than 'two consecutive bands' can wear 'uniforms of the same color'.

Given the total number of bands N and the number of uniform color choices K, 
determine the total number of valid ways you can assign uniform colors to all 
bands so that the above rule is not violated.

Input Format:
-------------
Two integers N and K.

Output Format:
--------------
Print an integer, Number of ways.

Example 1:  
----------
Input: 
3 2
Output:
6  

Explanation:
------------
Bands	band-1	band-2	band-3
----- 	----- 	----- 	-----
1		c1 		c1		c2
2		c1 		c2 		c1
3		c1 		c2 		c2
4		c2 		c1 		c1
5		c2 		c1 		c2
6		c2 		c2 		c1

Example 2:  
----------
Input: 
1 1
Output: 
1


Constraints:  
- 1 <= n <= 50  
- 1 <= k <= 10^5 
- The result will always be within the range of a 32-bit signed integer.
 */
import java.util.*;


public class ColorBands_Two{
    static Map<String,Integer> map;
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        map = new HashMap<>();
        System.out.println(find(n,k,0,1,0));
        cin.close();
    }
    static int find(int totalN, int totalK, int prev , int ind, int count){
        if(ind>totalN){
            // count++;
            return 1;
        }
        String t = prev+"-"+ind+"-"+count;
        if(map.containsKey(t)){
            return map.get(t);
        }
        int possible = 0;
        for(int i = 1; i<=totalK ; i++){
            if(i==prev && count==2){
                continue;      
            }
            else if(i==prev && count==1){
                possible += (find(totalN,totalK,i,ind+1,2));  
            }
            else{
                possible += (find(totalN,totalK,i,ind+1,1)); 
            }
        }
        map.put(t,possible);
        return possible;
        
    }
    
}


