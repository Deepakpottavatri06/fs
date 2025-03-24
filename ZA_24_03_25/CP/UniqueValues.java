import java.util.*;

public class UniqueValues{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(find(arr));
        cin.close();
    }
    static String find(int arr[]){
        Set<Integer>set = new HashSet<>();
        for(int i :arr){
            if(set.contains(i)){
               set.remove(i); 
            }
            else{
                set.add(i);
            }
        }
        
        return set.toString();
    }
}