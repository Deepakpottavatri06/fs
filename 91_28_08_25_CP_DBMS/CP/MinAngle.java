/*A wall clock is a complete whole circle and cover 360°.
You are given the time as HH:MM.
Your task is to return the angle between the Hours hand and Minutes hand
of the clock and the angle should not be reflex angle.

Input Format:
-------------
A string time, HH:MM

Output Format:
--------------
Print a double result, within 10^-5 of the original value.


Sample Input-1:
---------------
04:30

Sample Output-1:
----------------
45.00000


Sample Input-2:
---------------
06:15

Sample Output-2:
----------------
97.50000

 */
import java.util.*;

public class MinAngle{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp [] = cin.next().split(":");
            int hour = Integer.parseInt(inp[0]);
            int min = Integer.parseInt(inp[1]);
            System.out.printf("%.5f",find(hour,min));
        }
    }
    static double find(int hour, int min){
        /* hour hand -> 30 deg/ hour and 0.5 deg/min
           min hand -> 6 deg/min
         */
        double hAng = (hour*30) + (min*0.5);
        double mAng = (min*6);
        double res = Math.abs(hAng-mAng);
        if(res>180){
            return 360-res;
        }
        return res;
    }
}