/*Liam is a textile artist creating a patchwork quilt. He has X types of uniquely 
sized fabric patches. Each patch has a specific number of design squares on it. 
Liam wants to create a perfect square pattern on the quilt using these patches. 
He can use any number of patches of each type.

However, there are some square counts that he cannot achieve with these patches. 
Your task is to help Liam figure out the maximum number of design squares that 
cannot be formed using any combination of the available patch types.

Note: The number of design squares on any two different patches is co-prime.

Input Format:
-------------
input1: An integer X representing the number of different patch types available.
input2: An integer array representing the number of design squares each patch type contains.

Output Fromat:
--------------
Return an integer value representing the maximum number of design squares that 
cannot be created on the quilt using the given patch types.

Sample Input:
-------------
2
3 5

Sample Output:
--------------
7

Explanation:
------------
Using patches with 3 and 5 design squares, the largest number of design squares 
that cannot be formed by any combination is 7.


Sample Input:
-------------
4
3 7 17 29

Sample Output:
--------------
11
 */
import java.util.Arrays;
import java.util.Scanner;

public class MaxSquareDesign{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(find(arr));
       cin.close();
    }
    static int find(int arr []){
        int max = Arrays.stream(arr).max().getAsInt();
        boolean reach [] = new boolean[max*max];
        int limit = max*max;
        reach[0] = true;
        for(int i = 0; i < limit ; i++){
            if(reach[i]){
                for(int p : arr){
                    if(p + i < limit){
                        reach[i+p] = true;
                    }
                }
            }
        }
        
        for(int i = limit - 1; i >=0; i--){
            if(!reach[i]){
                return i;
            }
        }
        return -1;
        
    }
}