/*Two brothers want to play a game, 
The rules of the game are: one player gives two sorted lists of 
numerical elements and a number (sum). 
The opponent has to find the closest pair of elements 
to the given sum.
-> pair consists of elements from each list

Please help those brothers to develop a program, that takes 
two sorted lists as input and return a pair as output.

Input Format:
-------------
size of list_1
list_1 values
size of list_2
list_2 values
closest number

Output Format:
--------------
comma-separated pair

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32
Sample Output-1
---------------
1, 30

Sample Input-2
---------------
3
2 4 6
4
5 7 11 13
15
sample output-2
---------------
2, 13
 */
import java.util.*;

public class Pairs{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n1 = cin.nextInt();
        int arr1[] = new int[n1];
        for(int i = 0; i < n1; i++){
            arr1[i] = cin.nextInt();
        }
        int n2 = cin.nextInt();
        int arr2 [] = new int[n2];
        for( int i = 0; i < n2; i++){
            arr2[i] = cin.nextInt();
        }
        int sum = cin.nextInt();
        System.out.println(findPair(arr1,arr2,n1,n2,sum));
        cin.close();
    }
    static String findPair(int arr1[], int arr2 [], int n, int m,int target){
        int i = 0;
        int j = m-1;
        int pair[] = new int[2];
        while(i<n && j>=0){
            if(Math.abs(arr1[i]+arr2[j] - target) < Math.abs(pair[0]+pair[1] - target)){
                System.out.println("new pair :"+arr1[i]+" "+arr2[j]);
                pair[0] = arr1[i];
                pair[1] = arr2[j];
            }
            if(arr1[i]+arr2[j] > target){
                j--;
            }
            else{
                i++;
            }
        }
        
        return pair[0]+", "+pair[1];
    }
    
}