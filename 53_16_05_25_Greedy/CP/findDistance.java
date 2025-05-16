/*There are N employees from 3 different companies in a row, emps[], the employees 
are identified using company IDs as 1,2,3. A Courier Boy need to deliver 
P parcels to these 3 companies. The parcel details are given as parcels[],
where parcel[i]=[Ci,CIDi], i-th parcel, 'Ci' is Courier Boy's current position, 
and  'CIDi' is company's ID, he/she need to deliver parcel[i] from Ci position 
to the nearest employee belongs to companay ID equals to CIDi in the row.

You are given emps[] and parcels[] information,
Your task is to help the courier boy to find the distance between him to 
the nearest employee in that row to deliver the parcel.
If there is no solution found, return -1.

Input Format:
-------------
Line-1: Two space separated integers, N and P
Line-2: N space separated integers, only 1, 2 & 3.
Next P lines: Two space separated integers, position Ci and Company ID

Output Format:
--------------
Print the space separated integers, distance between boy and the employee for 
all the parcels.


Sample Input-1:
---------------
6 2
1 1 2 2 3 3
1 3
2 2

Sample Output-1:
----------------
3 0 

Sample Input-2:
---------------
5 2
1 2 3 2 1
2 1
1 1

Sample Output-2:
----------------
2 1 

 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class findDistance{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int p = cin.nextInt();
            List<List<Integer>> emps = new ArrayList<>();
            for(int i : new int[]{0,1,2,3}){
                emps.add(new ArrayList<>());
            }
            
            for(int i = 0 ; i < n ; i++){
                int cID = cin.nextInt();
                emps.get(cID).add(i);
            }
            
            int parcel[][] = new int[p][2];
            for(int i = 0; i < p; i++){
                parcel[i][0] = cin.nextInt();
                parcel[i][1] = cin.nextInt();
            }
            findDistances(emps,parcel,p);
           
        }
    }
    static void findDistances(List<List<Integer>> emps, int[][]parcel, int p){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < p; i++){
            int pos = parcel[i][0];
            int diff = Integer.MAX_VALUE;
            for(int j : emps.get(parcel[i][1])){
                diff = Math.min(Math.abs(pos-j),diff);
            }
            if(diff==Integer.MAX_VALUE){
                diff = -1;
            }
            res.append(diff).append(" ");
        }
        System.out.println(res);
    }
}