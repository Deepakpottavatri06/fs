/*You write a love letter to your friend. However, before your friend can read it, 
someone else takes it and rotates the characters of each word from left to right 
K times.

Your task is to determine how many of the words still remain the same even after 
this rotation.

Input Format:
-------------
input1: A string containing words separated by spaces.
input2: An integer K indicating the number of right rotations for each word.

Output Format:
--------------
An integer representing the number of words that remain unchanged after K right 
rotations.


Sample Input: 
-------------
ramram santan nnnn
3

Sample Output:
--------------
2


Sample Input: 
-------------
adasda
3

Sample Output:
--------------
0 */
import java.util.Scanner;


public class Rotation{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp [] = cin.nextLine().split(" ");
            int k = cin.nextInt();
            System.out.println(find(inp,k));
        }
    }
    static int find(String inp[], int k){
        int count = 0;
        for(String word: inp){
            int rotations = k%word.length();
            StringBuilder sb = new StringBuilder(word.substring(rotations, word.length()));
            sb.append(word.substring(0,rotations));
            // while(rotations>0){
            //     char f = sb.charAt(0);
            //     sb.deleteCharAt(0);
            //     sb.append(f);
            //     rotations--;
            // }
            if(word.equals(sb.toString())){
                count++;
            }
        }
        return count;
    }
}