/*Ramesh and Suresh are best friends. 
They decided to play a word game.

The game rules are as follows:
	- The game board shows a word W consist of two characters only A and B.
	- You are allowed to replace a pair of neighbour letters AA with BB in W.
	- Finally, The one who failed to replace the letters will lose the game.

You can assume that Ramesh will start playing the game always.

Your task is to determine if Ramesh can guarantee a win.
Print 'true', if Ramesh guarantee a win, otherwise, print 'false'.

Input Format:
-------------
A string W, word.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
AAABAAAA

Sample Output-1:
----------------
true

Explanation:
------------
Given word is AAABAAAA 
Ramesh -> AAABBBAA 
Now whatever the pair Suresh replaced with BB, 
one more replace is possible for Ramesh
So, there is a guarantee for Ramesh to win.

Sample Input-2:
---------------
AAAAAA

Sample Output-2:
----------------
true


Sample Input-3:
---------------
AAABAAA

Sample Output-3:
----------------
false
 */
import java.util.*;

public class ReplaceAA_One{
    public static void main(String []args){
        Scanner cin = new Scanner(System.in);
        String w = cin.nextLine();
        System.out.println(find(w));
        cin.close();
    }
    static boolean find(String w){
        int player = 1; // 1 - ramesh
        StringBuilder st = new StringBuilder(w);
        int depth = 1;
        return btrack(st,player,depth);
    }
    
    static boolean btrack(StringBuilder st, int player,int depth){
        
        // int replaced = 0;
        int n = st.length();
        // System.out.println("The player: "+player+" string : "+st+" , depth : " + depth);
        for(int i = 0;i < n-1 ; i++){
            if(st.charAt(i)=='A' && st.charAt(i+1)=='A'){
                
                st.setCharAt(i,'B');
                st.setCharAt(i+1,'B');
                boolean value = !btrack(st,1-player,depth+1);
                
                st.setCharAt(i,'A');
                st.setCharAt(i+1,'A');
                if(value){
                    return true;
                }
                // replaced = 1;
            }
        }
        
        return false;
    }

    static boolean btrackMyVersion(StringBuilder st, int player,int depth){
        
        int replaced = 0;
        int n = st.length();
        // System.out.println("The player: "+player+" string : "+st+" , depth : " + depth);
        for(int i = 0;i < n-1 ; i++){
            if(st.charAt(i)=='A' && st.charAt(i+1)=='A'){
                
                st.setCharAt(i,'B');
                st.setCharAt(i+1,'B');
               
                if(btrack(st,1-player,depth+1)){
                    return true;
                }
                st.setCharAt(i,'A');
                st.setCharAt(i+1,'A');
                replaced = 1;
            }
        }
        
        return (player==0 && replaced==0);
    }
}
