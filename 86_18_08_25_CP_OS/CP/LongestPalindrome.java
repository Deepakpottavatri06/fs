import java.util.*;

public class LongestPalindrome{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        System.out.println(find(inp));
        cin.close();
    }
    static int find(String inp[]){
        int res = 0;
        Map<String,Integer> map = new HashMap<>();
        for(String s: inp){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        boolean center = false;
        
        for(String key: new ArrayList<>(map.keySet())){
            String rev = new StringBuilder(key).reverse().toString();
            if(key.equals(rev)){
                int count = (map.get(key)/2);
                res += (count)*4;
                if(map.get(key)%2!=0){
                    center = true;
                }
            }
            else if(key.compareTo(rev)<0 && map.containsKey(rev)){
                res += 4*Math.min(map.get(key),map.get(rev));
            }
        }
        if(center){
            res+=2;
        }
        
        
        return res;
    }
    

}