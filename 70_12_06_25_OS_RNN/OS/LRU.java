import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


public class LRU{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            cin.nextLine();
            String inp [] = cin.nextLine().split(" ");
            lru(n,inp);
        }
    }
    static void lru(int n, String inp []){

        Set<String> cache = new LinkedHashSet<>();
        int misses = 0;
        int len = inp.length;
        for(int i =0; i< len; i++){
            if(cache.contains(inp[i])){
                cache.remove(inp[i]);
                cache.add(inp[i]);
            }
            else{
                misses++;
                if(cache.size()==n){
                    cache.remove(cache.iterator().next());
                }
                cache.add(inp[i]);
            }
        }
        
        System.out.println("Total Page Faults: "+misses);
        System.out.print("Final cache: ");

        System.out.print(cache);
        
    }
}