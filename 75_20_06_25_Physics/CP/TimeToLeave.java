import java.util.*;

public class TimeToLeave{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int l = cin.nextInt();
        int pos [] = new int[n];
        int vel[] = new int[n];
        for(int i = 0; i < n ; i++){
            pos[i] = cin.nextInt();
        }
        for(int i = 0; i < n ; i++){
            vel[i] = cin.nextInt();
        }
        System.out.println(find(pos,vel,n,l));
        cin.close();
    }
    static int find(int pos[], int vel[], int n, int l){
        int maxTime = 0;
        for(int i = 0; i < n; i++){
            int dis = 0;
            int speed = 0;
            if(vel[i]<0){
                dis = pos[i];
            }
            else{
                dis = l-pos[i];
            }
            speed = Math.abs(vel[i]);
            maxTime = Math.max(dis/speed,maxTime);
        }
        return maxTime;
    }
    
}