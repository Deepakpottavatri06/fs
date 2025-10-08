/*Ananth interested in creating the acronyms for any word. The definition
of acronym is another word with a concatenation of its first letter,
the number of letters between the first and last letter, and its last letter. 
 
If a word has only two characters, then it is an acronym of itself.

Examples:
    - Acronym of 'fog' is f1g'.
    - Acronym of 'another' is 'a5r'.
    - Acronym of 'ab' is 'ab'.

You are given a list of vocabulary, and another list of words.
Your task is to check,each word with the vocabulary and
return "true" if atleast one of the following rules satisfied:
1. acronym of the word should not match with any of the acronyms of vocabulary
2. if acronym of the word matches with any of the acronyms of vocabulary
'the word' and matching words in vocabulary should be same.

Otherwise, return 'false'.

Input Format:
-------------
Line-1: Space separated strings, vocabulary[] 
Line-2: Space separated strings, words[] 

Output Format:
--------------
Print N boolean values, where N = words.length 


Sample Input-1:
---------------
cool bell cool coir move more mike
cool char move 

Sample Output-1:
----------------
true false false
 */

 import java.util.*;

public class Acronym{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String vocab [] = cin.nextLine().split(" ");
        String words [] = cin.nextLine().split(" ");
        find(vocab,words);
        cin.close();
    }
    static void find(String vocab[], String words[]){
        Map<String,Set<String>> acronym = new HashMap<>();
        
        for(String i: vocab){
            String acrn = (i.length()<=2)? i: i.charAt(0)+""+(i.length()-2)+i.charAt(i.length()-1);
            acronym.putIfAbsent(acrn,new HashSet<>());
            acronym.get(acrn).add(i);
        }
        // System.out.println(vocabs+" "+acronym);
        for(String i:words){
            String acrn = (i.length()<=2)? i: i.charAt(0)+""+(i.length()-2)+i.charAt(i.length()-1);
            if(!acronym.containsKey(acrn)){
                System.out.print(true);
            }
            else if(acronym.get(acrn).size()==1 && acronym.get(acrn).contains(i)){
                System.out.print(true);
            }
            else{
                System.out.print(false);
            }
            System.out.print(" ");
            
        }
        
    }
}