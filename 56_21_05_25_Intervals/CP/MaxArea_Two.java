/*Indus Infra Ltd purchased a land of size L * W acres, for their upcoming venture.
The land is divided into rectangular plots, using fences. They have kept some 
H horizontal fences as hfences[] and V vertical fences as vfences[] on the land,
where hfence[i] is the distance from the top of the land to the i-th horizontal
fence and, vfence[j] is the distance from the top of the land to the j-th 
vertical fence. Each 1*1 cell is one acre plot.

Mr.RGV wants to purchase the biggest plot available to build a Guest-house.
Your task is to help Mr.RGV to find the biggest plot vailable after the fences 
are setup in the venture.
NOTE: The answer can be a large number, return the modulo of 10^9 + 7.

Input Format:
-------------
Line-1: 4 space separated integers, L,W,H and V
Line-2: H space separated integers, hfence[] in the range [0, L]
Line-3: V space sepaarted integers, vfence[] in the range [0, W]

Output Format:
--------------
Print an integer result, the area of biggest plot.


Sample Input-1:
---------------
5 6 2 2
2 3
2 5

Sample Output-1:
----------------
6


Sample Input-2:
---------------
5 6 1 1
3
4

Sample Output-2:
----------------
12
 */
import java.util.*;

public class MaxArea_Two{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int l = cin.nextInt();
            int w = cin.nextInt();
            int h = cin.nextInt();
            int v = cin.nextInt();
            int hF[] = new int [h+1];
            int vF[] = new int[v+1];
            for(int i = 0; i < h; i++){
                hF[i] = cin.nextInt();
            }
            hF[h] = l;
            for(int i = 0; i < v; i++){
                vF[i] = cin.nextInt();
            }
            vF[v] = w;
            System.out.println(find(hF,vF,h,v));
            cin.close();
        }
    }
    static int find(int hF[],int vF[], int h, int v){
        int prevH = 0;
        int maxArea;
        Arrays.sort(hF);
        Arrays.sort(vF);
        // O(h*v)
        // for(int i = 0; i < h+1; i++){
        //     int prevV = 0;
        //     for(int j = 0; j < v+1; j++ ){
        //         int area = ((hF[i]-prevH)*(vF[j]-prevV))%(1000000007);
        //         maxArea = Math.max(maxArea,area);
        //         prevV = vF[j];
        //     }
        //     prevH = hF[i];
        // }
        
        //O(min(h,v))
        int maxlen = 0;
        int maxbre = 0;
        for(int i = 0;i < h+1; i++){
            maxlen = Math.max(hF[i]-prevH,maxlen);
            prevH = hF[i];
        }
        int prevV = 0;
        for(int i = 0; i < v+1; i++){
            maxbre = Math.max(vF[i]-prevV,maxbre);
            prevV = vF[i];
        }
        maxArea = maxlen*maxbre %(1000000007);
        return maxArea;
    }
}