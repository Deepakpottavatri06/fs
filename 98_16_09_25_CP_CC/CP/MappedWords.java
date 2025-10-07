/*Mr Ajay Sharma is working woth words.
He found that few words in the langugage have same meaning.
Such words are given as a list of pairs as mappedpairs[],
where mappedpairs[i] = [word1, word2] indicates that word1 and word2 are 
the words with same meaning.

He is given phrase, and he wants to apply all the mappedpairs[] on the phrase.

Your task is to help Mr.Ajay Sharma to find and return all possible 
Mapped Phrases generated after applying all the mapped words,
and print them in lexicographical order.


Input Format:
-------------
Line-1: An integer N, number of mapped pairs.
Next N lines: Two space separated words, mappedpair[].
Last Line: A line of words, the phrase.

Output Format:
--------------
Print the list of mapped phrases in sorted order.


Sample Input-1:
---------------
3
hi hello
sweet sugar
candy chocolate
hi sam! ram has a sugar candy

Sample Output-1:
----------------
[hello sam! he has sugar candy, hello sam! he has sugar chocolate, 
hello sam! he has sweet candy, hello sam! he has sweet chocolate, 
hi sam! he has sugar candy, hi sam! he has sugar chocolate, 
hi sam! he has sweet candy, hi sam! he has sweet chocolate]



Sample Input-2:
---------------
2
hi hey
hey hello
hi how are you

Sample Output-2:
----------------
[hello how are you, hey how are you, hi how are you]


 */
import java.util.*;

class UF{
    Map<String,String> parent = new HashMap<>();
    String find(String x){
        parent.putIfAbsent(x,x);
        if(parent.get(x).equals(x)){
           return x; 
        }
        String p = find(parent.get(x));
        parent.put(x,p);
        return p;
    }
    void union(String x, String y){
        String rx = find(x), ry = find(y);
        if(!rx.equals(ry)){
            parent.put(rx,ry);
        }
    }
}
public class MappedWords{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        cin.nextLine();
        String arr[][] = new String[n][2];
        for(int i = 0; i < n; i++){
            String temp[] = cin.nextLine().split(" ");
            arr[i][0] = temp[0];
            arr[i][1] = temp[1];
        }
        String sentence = cin.nextLine();
        System.out.println(findS(sentence,arr,n));
        cin.close();
    }
    
    static List<String> findS(String sentence, String arr[][], int n){
        String words[] = sentence.split(" ");
        UF uf = new UF();
        for(String t[]: arr){
            uf.union(t[0],t[1]);
        }
        Map<String,SortedSet<String>> groups = new HashMap<>();
        for(String k: uf.parent.keySet()){
            String root = uf.find(k);
            groups.computeIfAbsent(root,(m)-> new TreeSet()).add(k);
        }
        List<List<String>> optionForWord = new ArrayList<>();
        for(String w:words){
            List<String> options = new ArrayList<>();
            if(uf.parent.containsKey(w)){
                String root = uf.find(w);
                for(String child:groups.get(root)){
                    options.add(child);
                }
            }
            else{
                options.add(w);
            }
            optionForWord.add(options);
        }
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(result,0,sb,optionForWord);
        Collections.sort(result);
        return result;
    }
    static void backtrack(List<String> result, int ind, StringBuilder sb, List<List<String>> options){
        if(ind==options.size()){
            result.add(sb.toString().trim());
            return;
        }
        int len = sb.length();
        for(String t:options.get(ind)){
            if(sb.length()>0) sb.append(' ');
            sb.append(t);
            backtrack(result,ind+1,sb,options);
            sb.setLength(len);
        }
        
    }
    
}