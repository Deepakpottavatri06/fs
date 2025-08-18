import java.util.*;

public class DistributeTickets{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(",");
        int ids [] = new int[inp.length];
        for(int i = 0; i < inp.length; i++){
            ids[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(find(ids));
        cin.close();
    }
    static int find(int ids[]){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i: ids){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        
        int res = 0;
        for(int i: new ArrayList<>(map.keySet())){
            if(map.get(i)==1){
                return -1;
            }
            else{
                int total = map.get(i);
                int threeSeaters = total/3;
                int twoSeaters = 0;
                if(total%3==2){
                    twoSeaters = 1;
                }
                else if(total%3==1){
                    twoSeaters = 2;
                    threeSeaters--;
                }
                // System.out.println("The seats alloted for "+i+" is "+(threeSeaters+twoSeaters));
                res += (threeSeaters + twoSeaters);
            }
        }
        return res;
    }
}