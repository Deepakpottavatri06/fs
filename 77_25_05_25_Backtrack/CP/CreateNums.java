/*Basava is interested in playing with digits.
He wants to create a set of integers of length N, using the digits[0-9].
The rules to create the integers are as follows:
	- digits in each integer are like d0,d1,d2...dn-1
	- and |d0-d1| = |d1-d2| = |d2-d3| ... |dn-2 - dn-1|= D

Basava is given two integers N and D, where N is length of the integer and 
D is the difference. Can you help Basava, to create such list of integers 
and print all the possible integers in ascending order


Note:
-----
Integers with leading 0's are not allowed


Input Format:
-------------
Two space separated integers N and K.

Output Format:
--------------
Print all the possible integers in ascending order.


Sample Input-1:
---------------
3 5

Sample Output-1:
----------------
[161, 272, 383, 494, 505, 616, 727, 838, 949]


Sample Input-2:
---------------
2 3

Sample Output-2:
----------------
[14, 25, 30, 36, 41, 47, 52, 58, 63, 69, 74, 85, 96]
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateNums{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int len = cin.nextInt();
        int diff = cin.nextInt();
        
        System.out.println(find(len,diff));
        cin.close();
    }
    static List<String> find(int len, int diff){
        List<String> res = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        for(int i = 1; i<=9 ; i++){
            num.append(i);
            backtrack(len,diff,i,num, res);
            num.deleteCharAt(0);
        }
        return res;
    }
    static void backtrack(int len, int diff, int prev, StringBuilder num, List<String> res ){
        if(num.length()==len){
            res.add(num.toString());
            return;
        }
        
        for(int i = 0; i < 10; i++){
            if(Math.abs(prev-i)==diff){
                num.append(i);
                backtrack(len,diff,i,num, res);
                num.deleteCharAt(num.length()-1);
            }
        }
        
    }
}