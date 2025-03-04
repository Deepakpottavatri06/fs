import java.util.*;

public class PaperRefill{
    public static void main(String [] args){
        Scanner cin  =  new Scanner(System.in);
        int n = cin.nextInt();
        int t = cin.nextInt();
        int v = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(paperRefill(arr,n,t,v));
        cin.close();
    }
    static int paperRefill(int arr [], int n , int t, int v){
        int i =0;
        int j = n-1;
        int count = 0;
        int tCount = t;
        int vCount = v;
        while(i<=j){
            if(i==j){
                if(tCount >= vCount){
                    if(tCount<arr[i]){
                        count++;
                    }
                }
                else{
                    if(vCount<arr[i]){
                        count++;
                    }
                }
            }
            else{
                if(tCount<arr[i]){
                    count++;
                    tCount = t;
                }

                tCount-=arr[i];
                
                if(vCount<arr[j]){
                    count++;
                    vCount = v;
                }
                vCount-=arr[j];
            }
            i++;
            j--;
        }
        
        return count;
    }
}