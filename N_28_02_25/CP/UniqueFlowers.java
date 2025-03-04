import java.util.*;

public class UniqueFlowers{
    public static void main(String [] args){
        Scanner cin  = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        int k = cin.nextInt();
        System.out.println(findUniqueFlowers(n,arr,k));
        cin.close();
    }
    static List<Integer> findUniqueFlowers(int n ,int arr[], int k){
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < k; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        res.add(map.size());
        for(int i = k ; i < n ; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
            map.put(arr[i-k],map.getOrDefault(arr[i-k],0)-1);
            if(map.get(arr[i-k])==0){
                map.remove(arr[i-k]);
            }
            res.add(map.size());
        }
        return res;
    }
}