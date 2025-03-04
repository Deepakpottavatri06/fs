import java.util.*;

public class ValidCode{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.nextLine();
        String abr = cin.nextLine();
        System.out.println(solve(inp,abr));
        cin.close();
    }
    static boolean solve(String inp, String abr){
        int i = 0;
        int j = 0;
        int n = inp.length();
        int m = abr.length();
        while(i<n && j<m){
            if(Character.isDigit(abr.charAt(j))){
                if(abr.charAt(j)=='0'){
                    return false;
                }
                int sum = 0;
                while((j<m) && Character.isDigit(abr.charAt(j))){
                    sum = sum*10 + Integer.parseInt(abr.charAt(j)+"");
                    j++;
                }
                i+=sum;
                if(i>n){
                    return false;
                }
            }
            else{
                if(inp.charAt(i)!=abr.charAt(j)){
                    return false;
                }
                i++;
                j++;
            }
            // i += sum;
            // if(j==m && i==n){
            //     return true;
            // }
            // if((i<n || i>n) && j==m){
            //     return false;
            // }
            // if(i<n && inp.charAt(i)!=abr.charAt(j)){
            //     return false;
            // }
            // if(j<m && i>=n){
            //     return false;
            // }
            // i++;
            // j++;
        }
        return i==n && j==m;
    }
}