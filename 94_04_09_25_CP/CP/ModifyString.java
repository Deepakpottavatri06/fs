/*Somesh is given a task to you on Strings.
You have given a string S ([a-z]), and an integer array Arr[]
Now your task is to modify 'S' in such way:
replace the 'i+1' characters in the string, with next i-th character 
in alphabetic order(cyclic).

For example, if S="art", Arr[]=[2,3,5] is 3, 
i=0, modify('a') = 'c' , S="crt".
i=1, modify('c') = 'f', modify('r') = 'u', S="fut".
i=2, modify('f') = 'k', modify('u') = 'z', modify('t') = 'y', S="kzy"
Finally modified string is kzy. 

Note: if arr[i]=3 modify('z') ='c'

Return the final modified string after all such modifications to S are applied.

Input Format:
-------------
Line-1 -> A String S, length of S is L
Line-2 -> L space separated integers.

Output Format:
--------------
Print modifed String.


Sample Input-1:
---------------
adbp
1 2 3 4

Sample Output-1:
----------------
kmit
 */
import java.util.Scanner;

public class ModifyString{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp = cin.nextLine();
            int arr [] = new int[inp.length()];
            for(int i = 0; i < inp.length(); i++){
                arr[i] = cin.nextInt();
            }
            
            System.out.println(find(arr,inp));
        }
    }
    static String find(int arr[], String inp){
        int n = inp.length();
        char temp[] = inp.toCharArray();
        int revPrefix [] = new int[n];
        revPrefix[n-1] = arr[n-1];
        for(int i = n-2; i>=0; i--){
            revPrefix[i] = revPrefix[i+1] + arr[i]; 
        }
        for(int i = 0; i < n; i++){
            temp[i] = (char)('a' + (temp[i]-'a'+revPrefix[i])%26);
        }
        return new String(temp);
    }
}