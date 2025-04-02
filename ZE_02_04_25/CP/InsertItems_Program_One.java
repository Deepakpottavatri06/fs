import java.util.*;

public class InsertItems_Program_One{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k  = cin.nextInt();
        int items [] = new int[n];
        int rooms[] = new int[k];
        for(int i = 0; i < n ;i++){
            items[i] = cin.nextInt();
        }
        for(int i = 0; i < k ; i++){
            rooms[i] = cin.nextInt();
        }
        // System.out.println(maxItems(n,k,items,rooms));
        System.out.println(maxItems_SinglePass(n,k,items,rooms));
        cin.close();
    }
    static int maxItems(int n , int k , int items [], int rooms[]){
        Arrays.sort(items);
        boolean itemInRoom [] = new boolean[k];
        int count = 0;
        for(int i = 0; i < n ; i++){
            int index = -1;
            for(int j = 0; j < k ; j++ ){
                if(!itemInRoom[j] && rooms[j]>=items[i]){
                    index = j;
                }
                else{
                    break;
                }
            }
            if(index!=-1){
                itemInRoom[index] = true;
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
    //O(nlogn)
    static int maxItems_SinglePass(int n , int k , int items [], int rooms[]){
        Arrays.sort(items);
        for(int i =1 ; i< k ; i++){
            rooms[i] = Math.min(rooms[i],rooms[i-1]);
        }
        
        int leftItem = 0;
        int rightRoom = k-1;
        int count = 0;
        while(leftItem<n && rightRoom >= 0){
            if(rooms[rightRoom]>=items[leftItem]){
                leftItem++;
                count++;   
            }
            rightRoom--;
        }
        return count;
    }
}