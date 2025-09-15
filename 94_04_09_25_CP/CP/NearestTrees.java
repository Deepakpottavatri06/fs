import java.util.*;

public class NearestTrees{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        Queue<int[]> q = new PriorityQueue<>((a,b)->{
            int aDist = a[0]*a[0] + a[1]*a[1];
            int bDist = b[0]*b[0] + b[1]*b[1];
            return aDist - bDist;
        });
        for(int i = 0; i < n; i++){
            q.add(new int[]{cin.nextInt(),cin.nextInt()});
        }
        while(k>0){
            System.out.print(Arrays.toString(q.poll())+" ");
            k--;
        }
        cin.close();
    }
}