// Bunny is playing with binary strings. He wants to break
// a binary string B into 3 parts, of length atleast '1',
// when we merge the 3 parts will result the string B.

// Your task is to find the count the various forms to break 
// the Binary String B into 3 parts, where each part should 
// contain equal number of 1's.


// Input Format:
// -------------
// A string S.

// Output Format:
// --------------
// Print an integer, count the various forms to break.


// Sample Input-1:
// ---------------
// 01010010

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// There are six forms to break S into 3 parts 
// where each part contain the equal number of  1's.
// 01 | 01 | 0010
// 01 | 010 | 010
// 01 | 0100 | 10
// 010 | 1 | 0010
// 010 | 10 | 010
// 010 | 100 | 10


// Sample Input-2:
// ---------------
// 010010

// Sample Output-2:
// ----------------
// 0

import java.util.*;

public class SplitString{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        int n = inp.length();
        int totalOnes = countOnes(inp);
        int totalGroups = 0;
        for(int i = 1; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                String one = inp.substring(0,i);
                String two = inp.substring(i,j);
                int partOne = countOnes(one);
                int partTwo = countOnes(two);
                int partThree = totalOnes - (partOne + partTwo);
                if(partOne==partTwo && partTwo==partThree){
                    totalGroups++;
                }
            }
        }
        System.out.println(totalGroups);
        cin.close();
    }
    static int countOnes(String s){
        int count = 0;
    for (char c : s.toCharArray()) {
        if (c == '1') count++;
    }
    return count;
    }
}