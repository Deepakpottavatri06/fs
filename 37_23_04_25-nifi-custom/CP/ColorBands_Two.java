import java.util.*;


public class ColorBands_Two{
    static Map<String,Integer> map;
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        map = new HashMap<>();
        System.out.println(find(n,k,0,1,0));
        cin.close();
    }
    static int find(int totalN, int totalK, int prev , int ind, int count){
        if(ind>totalN){
            // count++;
            return 1;
        }
        String t = prev+"-"+ind+"-"+count;
        if(map.containsKey(t)){
            return map.get(t);
        }
        int possible = 0;
        for(int i = 1; i<=totalK ; i++){
            if(i==prev && count==2){
                continue;      
            }
            else if(i==prev && count==1){
                possible += (find(totalN,totalK,i,ind+1,2));  
            }
            else{
                possible += (find(totalN,totalK,i,ind+1,1)); 
            }
        }
        map.put(t,possible);
        return possible;
        
    }
    
}


