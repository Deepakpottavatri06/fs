import java.util.*;

public class SymmetricDesign{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        System.out.println(findSym(inp));
        cin.close();
    }
    static boolean findSym(String inp){
        char arr [] = inp.toCharArray();
        int odd = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(char c : arr){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> e : map.entrySet()){
            if(e.getValue()%2!=0){
                odd++;
            }
            
        }
        return odd<=1;
    }
}