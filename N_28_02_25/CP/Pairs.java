import java.util.*;

public class Pairs{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n1 = cin.nextInt();
        int arr1[] = new int[n1];
        for(int i = 0; i < n1; i++){
            arr1[i] = cin.nextInt();
        }
        int n2 = cin.nextInt();
        int arr2 [] = new int[n2];
        for( int i = 0; i < n2; i++){
            arr2[i] = cin.nextInt();
        }
        int sum = cin.nextInt();
        System.out.println(findPair(arr1,arr2,n1,n2,sum));
        cin.close();
    }
    static String findPair(int arr1[], int arr2 [], int n, int m,int target){
        int i = 0;
        int j = m-1;
        int pair[] = new int[2];
        while(i<n && j>=0){
            if(Math.abs(arr1[i]+arr2[j] - target) < Math.abs(pair[0]+pair[1] - target)){
                System.out.println("new pair :"+arr1[i]+" "+arr2[j]);
                pair[0] = arr1[i];
                pair[1] = arr2[j];
            }
            if(arr1[i]+arr2[j] > target){
                j--;
            }
            else{
                i++;
            }
        }
        
        return pair[0]+", "+pair[1];
    }
    
}