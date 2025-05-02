/*You are working in a genetics laboratory where you are tasked with correcting 
DNA sequences. Each DNA strand is represented as a sequence of characters 
'A', 'C', 'G', and 'T'.
Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
must be modified to match a targetDNA sequence exactly.

You can perform the following mutation operations:
- Insert a nucleotide (A, C, G, or T) into the DNA strand.
- Delete a nucleotide from the DNA strand.
- Replace a nucleotide with another one.

Each operation counts as one step.

Your task is to find the minimum number of mutation operations needed to 
transform the originalDNA into the targetDNA.

Input format:
-------------
2 space seperated strings, originalDNA and targetDNA

Output format:
--------------
An integer, the minimum number of mutation operations


Example 1:
-----------
Input:
ACGT AGT

Output:
1

Explanation:
Delete 'C': "ACGT" → "AGT"
Only 1 mutation is needed.

Example 2:
----------
Input:
GATTAC GCATGCU

Output:
4

Explanation:
- Replace 'A' with 'C': "GATTAC" → "GCTTAC"
- Replace 'T' with 'A': "GCTTAC" → "GCATAC"
- Replace 'A' with 'G': "GCATAC" → "GCATGC"
- Insert 'U' at the end: "GCATGC" → "GCATGCU"

Thus, 4 mutations are needed.
 */
import java.util.*;

public class MinMutations_Two{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        String target = cin.next();
        int dp [][] = new int[inp.length()+1][target.length()+1];
        for(int i = 0; i < inp.length()+1; i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(findMin(inp,target,0,0,dp));
        cin.close();
    }
    static int findMin(String inp, String target, int i, int j, int dp[][]){
        if(i==inp.length()){
            return target.length()-j;
        }
        if(j==target.length()){
            return inp.length() - i;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        // System.out.println("Values : "+inp.charAt(i) + " "+target.charAt(j));
        if(inp.charAt(i)==target.charAt(j)){
            return dp[i][j] =  findMin(inp,target,i+1,j+1,dp);
        }
        
            // System.out.println("Added one");
        int insert = findMin(inp,target,i,j+1,dp); // insert
        int delete = findMin(inp,target,i+1,j,dp); // delete
        int replace = findMin(inp,target,i+1,j+1,dp); // replace
        int val =  1 + Math.min(Math.min(insert,delete),replace);
        
        return dp[i][j] = val;
    }
}