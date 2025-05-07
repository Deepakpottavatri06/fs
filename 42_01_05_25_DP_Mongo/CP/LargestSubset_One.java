/*You are managing a fleet of exploratory spacecraft, each carrying containers 
composed of two types of critical resources: 
fuel units (represented by '0') and oxygen tanks (represented by '1'). 
You're given a list 'containers', where each container is represented by a 
binary string indicating its resource composition, 
along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
'oxygenLimit' (maximum allowed oxygen tanks).

Your goal is to select the largest possible subset of containers such that the 
total number of fuel units does not exceed 'fuelLimit' and the total number of 
oxygen tanks does not exceed 'oxygenLimit'.

Input format:
-------------
Line 1: Space seperated strings, represents the container strings
Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

Output format:
--------------
An integer, largest possible subset of containers.


Example 1:
----------
Input=
10 0001 111001 1 0
5 3

Output=
4

Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
- Total fuel units = 5 (within limit)
- Total oxygen tanks = 3 (within limit)
Container "111001" cannot be included as it exceeds the oxygen tank limit.


Example 2:
----------
Input=
10 0 1
1 1

Output=
2

Explanation: The largest subset meeting the constraints is {"0", "1"}.
- Total fuel units = 1
- Total oxygen tanks = 1


Constraints:
- 1 <= containers.length <= 600
- 1 <= containers[i].length <= 100
- 'containers[i]' consists only of digits '0' and '1'.
- 1 <= fuelLimit, oxygenLimit <= 100
 */
import java.util.*;

public class LargestSubset_One{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp []  = cin.nextLine().split(" ");
        int fuelLimit = cin.nextInt();
        int oxygenLimit = cin.nextInt();
        int arr[][] = new int[inp.length][2];
        for(int i = 0; i < inp.length; i++){
            int countZero = 0;
            for(char c : inp[i].toCharArray()){
                if(c=='0'){
                    countZero++;
                }
            }
            arr[i] = new int[]{countZero,inp[i].length()-countZero};
        }
        int dp[][][] = new int[arr.length][fuelLimit+1][oxygenLimit+1];
        for(int i = 0; i <  arr.length ; i++){
            for(int j = 0 ; j < fuelLimit+1; j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        System.out.println(find(arr,0,fuelLimit,oxygenLimit,dp));
        cin.close();
    }
    static int find(int arr[][],int ind, int fuelLimit , int oxygenLimit,int dp[][][]){
        if(ind==arr.length){
            return 0;
        }
        if(dp[ind][fuelLimit][oxygenLimit]!=-1){
            return dp[ind][fuelLimit][oxygenLimit];
        }
        if(arr[ind][0]<=fuelLimit && arr[ind][1]<=oxygenLimit){
            return dp[ind][fuelLimit][oxygenLimit] = Math.max(1 + find(arr,ind+1,fuelLimit-arr[ind][0],oxygenLimit-arr[ind][1],dp),
            find(arr,ind+1,fuelLimit,oxygenLimit,dp));
        }
        return dp[ind][fuelLimit][oxygenLimit] = find(arr,ind+1,fuelLimit,oxygenLimit,dp);
    }
}