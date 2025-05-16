import java.util.*;

public class SubSeq{
    public static void main(String [] a){
        Scanner cin = new Scanner(System.in);
        String org = cin.next();
        String sub = cin.next();
        int p = cin.nextInt();
        int ind [] = new int[p];
        for(int i = 0; i < p ; i++){
            ind[i] = cin.nextInt();
        }
        System.out.println(findMax(ind,org,sub,p));
        cin.close();
    }
    static int findMax(int ind[], String org, String sub, int p){
        StringBuilder s = new StringBuilder();
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for(int i:ind){
            set.add(i);
            s = new StringBuilder();
            for(int j = 0;j <org.length();j++){
                if(!set.contains(j)){
                    s.append(org.charAt(j));
                }
            }
            
            if(!isSub(sub,s.toString())){
                return max;
            }
            max++;
            
        }
        return max;
    }
    static boolean isSub(String target, String str){
        
        int i = 0;
        int j = 0;
        while(j<str.length() && i < target.length()){
            if(target.charAt(i)==str.charAt(j)){
                i++;
            }
            if(i==target.length()){
                break;
            }
            j++;
        }
        return i==target.length();
    }
}