import java.util.*;


public class Spirit{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int p = cin.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n ; i++){
                arr[i] = cin.nextInt();
            }
   
            System.out.printf("%.5f",find(n,p,arr));
            cin.close();
        }
    }
    static double find(int n, int p, int arr []){
        int max = Integer.MIN_VALUE;
        
        for(int i : arr){
            max = Math.max(max,i);
        }
        double low = 0d;
        double high = max;
        double res = 0d;
        while(high-low > 1e-6){
            double mid = low + (high - low)/2;
            if(check(arr,mid,p)){
                res = mid;
                low = mid;
            }
            else{
                high = mid;
            }
        }
        
        return res;
    }
    static boolean check(int arr[], double target, int p){
        double remSpirit = 0;
        double addSpirit = 0;
        for(int i: arr){
            if(i>target){
                remSpirit += (i-target) - (i - target)*(p/100.00);
            }
            else{
                addSpirit += (target - i);
            }
        }
        return remSpirit>=addSpirit;
    }
}