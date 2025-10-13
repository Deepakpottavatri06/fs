import java.util.*;

public class ShootBalloons{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < inp.length ; i++){
            arr.add(Integer.parseInt(inp[i]));
        }
        System.out.println(memoRecur(arr));
        cin.close();
    }
    static int memoRecur(List<Integer> list){
        int n = list.size();
        int memo[][] = new int[n+2][n+2];
        int arr [] = new int[n+2];
        for(int i = 1; i<n+1;i++){
            arr[i] = list.get(i-1);
        }
        arr[0] = arr[n+1] = 1;
        for(int i[]: memo){
            Arrays.fill(i,-1);
        }
        return recur(memo,arr,0,n+1);
    }
    static int recur(int memo[][], int arr[], int l, int r ){
        if(l+1==r){
            return 0;
        }
        if(memo[l][r]!=-1){
            return memo[l][r];
        }
        int best = 0;
        for(int i= l+1; i < r; i++){
            int sum = recur(memo,arr,l,i) + arr[l]*arr[i]*arr[r] + recur(memo,arr,i,r);
            best = Math.max(best,sum);
        }
        memo[l][r] = best;
        return best;
        
    }
}