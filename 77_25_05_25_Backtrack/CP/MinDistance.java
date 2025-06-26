/*Sreedhar is a farmer, and he started harvesting the watermelon crop, 
the crop grown very well. There are several watermelons in the crop. 
Sreedhar started picking up the watermelons one by one.
After each pick, he keeps the watermelon in a truck placed inside the crop.

The crop is in the from of 2D grid of size m*n.
You will be given the positions of the truck, Sreedhar's, and the watermelons.  
Positions are represented by the cells in the 2D grid. 

Your task is to find the minimum distance for Sreedhar to collect 
all the watermelons and put them inside the truck one by one. 

Sreedhar can only take at most one watermelon at one time 
and can move in four directions - up, down, left and right, to the adjacent cell. 
The distance is represented by the number of moves.

Input Format:
-------------
Line-1: Two space separated integers m and n, size of crop. 
Line-2: Two space separated integers, position of the truck. 
Line-3: Two space separated integers, position of Sreedhar.
Line-4: An integer W, number of watermelons in the crop.
Next W lines: Two space separated integers, positions of watermelon. 

Output Format:
--------------
An integer, minimum distance covered by Sreedhar to pickup all the watermelons


Sample Input-1:
---------------
5 7		
2 2		//Truck Position
4 4		//Sreedhar Position
2		//Number of watermelons
3 0		//Watermelon positions
2 5

5 7
2 2
4 4
2
3 0
2 5

Sample Output-1:
----------------
12
 */

import java.util.Scanner;

public class MinDistance {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int m = cin.nextInt();
            int n = cin.nextInt();
            int truck[] = new int[] { cin.nextInt(), cin.nextInt() };
            int sree[] = new int[] { cin.nextInt(), cin.nextInt() };
            int nMelons = cin.nextInt();
            int grid[][] = new int[nMelons][2];
            for (int i = 0; i < nMelons; i++) {
                grid[i] = new int[] { cin.nextInt(), cin.nextInt() };
            }
            System.out.println(find(grid, truck, sree));
            cin.close();
        }
    }

    static int find(int grid[][], int truck[], int sree[]) {
        int TotalDist = 0;
        int minAdjust = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            int toSree = Math.abs(grid[i][0] - sree[0]) + Math.abs(grid[i][1] - sree[1]);
            int toTruck = Math.abs(grid[i][0] - truck[0]) + Math.abs(grid[i][1] - truck[1]);
            minAdjust = Math.min(minAdjust,toSree-toTruck);
        }
        for (int[] grid1 : grid) {
            int diff = Math.abs(grid1[0] - truck[0]) + Math.abs(grid1[1] - truck[1]);
            TotalDist += 2 * diff;
        }
        return TotalDist + minAdjust;

        
    }
}
