import java.util.*;

public class UniqueValues{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(findXor(arr));
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
    static String findXor(int arr[]){
        int xor = 0;
        for(int i : arr){
            xor^=i;
        }
        // System.out.println(xor);
        // System.out.println(Integer.toBinaryString(xor));
        // System.out.println(Integer.toBinaryString(-xor));

        int diff = xor & (-xor);
        // System.out.println(Integer.toBinaryString(diff));
        int a = 0;
        int b = 0;
        for(int i:arr){
            if((i & (diff)) == 0){
                a^=i;
            }
            else{
                b^=i;
            }
        }
        return "["+a+","+b+"]";
    }
}