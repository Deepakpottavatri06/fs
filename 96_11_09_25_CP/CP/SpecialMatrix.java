/*
Venkatesh is a maths teacher.
He is teaching matrices concept to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the number of special matrices 
in the given matrix A[m][n].

A special matrix has following property:
	- The size of matrix should be 3*3,
	- The sum of elements in each row, each column and 
	  the two diagonals are equal.
	- The 3*3 matrix should contains all the numbers from 1 to 9.
	
Your task is to help the students to find the number of speical matrices
in the given matrix.

Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, number of the special matrices.


Sample Input-1:
---------------
4 5
6 8 1 6 8
7 3 5 7 3
2 4 9 2 4
6 8 1 6 8

Sample Output-1:
----------------
1

Explanation:
------------
The special square is:
8 1 6
3 5 7
4 9 2


Sample Input-2:
---------------
3 5
2 7 6 7 2
9 5 1 5 9
4 3 8 3 4

Sample Output-2:
----------------
2

Explanation:
------------
The special squares are:
2 7 6
9 5 1
4 3 8
-----
6 7 2
1 5 9
8 3 4


 */
import java.util.*;

public class SpecialMatrix{
    public static void main(String [] args){
        try (Scanner cin = new Scanner(System.in)) {
            int m = cin.nextInt();
            int n = cin.nextInt();
            int arr [][] = new int[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    arr[i][j] = cin.nextInt();
                }
            }
            find(arr,m,n);
        }
    }
    public static void find(int [][] arr, int m, int n){
        if(m<=2) {
            System.out.println(0);
            return;
        }
        int count = 0;
        for(int i = 0; i <=m-3; i++){
            for(int j = 0; j <=n-3; j++){
                boolean t = specialCheck(arr, i, j);
                if(t){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    static boolean specialCheck(int arr[][], int i, int j){

        Set<Integer> unique = new HashSet<>();
        boolean flag = false;
        int sum = 0;
        for(int k = j; k < j+3 ; k++){
            for(int l = i; l < i+3; l++){
                unique.add(arr[l][k]);
                if(!flag) sum+=arr[l][k];
            }
            flag = true;
        }
        if(unique.size()!=9){
            return false;
        }
        
        for(int k = j; k < j+3 ; k++){
            int sumCheck = 0;
            for(int l = i; l<i+3; l++){
                sumCheck+=arr[l][k];
            }
            if(sumCheck!=sum) return false;
            
        }
        for(int l = i; l<i+3; l++){
            int sumCheck = 0;
            for(int k = j; k < j+3 ; k++){
                sumCheck+=arr[l][k];
            }
            if(sumCheck!=sum) return false;
        }
        int diag = arr[i][j] + arr[i+1][j+1] + arr[i+2][j+2];
        if(diag!=sum) return false;
        diag = 0;
        diag = arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j];
        return diag==sum;
    }
}