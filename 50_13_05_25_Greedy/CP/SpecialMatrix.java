/*Venkatadri is a maths teacher.
He is teaching matrices to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the special matrix, 
in the iven matrix A[m][n].
A special matrix has following property:
	- The sum of elements in each row, each column and the two diagonals are equal.
	- Every 1*1 matrix is called as a special matrix.
	- The size of the special matrix should be a square, i.e., P*P.

Your task is to help the students to find the speical matrix  with max size P.


Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, maximum size P of the special matrix.


Sample Input-1:
---------------
5 5
7 8 3 5 6
3 5 1 6 7
3 5 4 3 1
6 2 7 3 2
5 4 7 6 2

Sample Output-1:
----------------
3

Explanation:
------------
The special square is:
5 1 6
5 4 3
2 7 3


Sample Input-2:
---------------
4 4
7 8 3 5
3 2 1 6
3 2 3 3
6 2 3 3

Sample Output-2:
----------------
2

Explanation:
------------
The special square is:
3 3
3 3
 */
import java.util.Scanner;

public class SpecialMatrix{
    public static void main(String [] a){
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        int arr[][] = new int[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                arr[i][j] = cin.nextInt();
            }
        }
        
        System.out.println(findMatrix(arr,m,n));
        cin.close();
    }
    static int findMatrix(int arr[][], int m , int n){
        int maxSize = Math.min(m,n);
        for(int size = maxSize; size>=0; size-- ){
            for(int i = 0; i < m - size +1 ; i++){
                for(int j = 0; j < n - size + 1; j++){
                    if(check(arr,i,j,size)){
                        return size;
                    }
                }
            }
        }   
        return -1;
    }
    static boolean check(int arr[][],int r , int c, int size){
        int sum = 0;
        for(int i = r; i < r + size ; i++ ){
            sum += arr[i][c];
        }
        
        for(int i = r; i < r + size ;i++ ){
            int s = 0;
            for(int j = c; j < c+ size ; j++){
                s +=arr[i][j]; 
            }
            if(s!=sum){
                return false;
            }
        }
        for(int j = c; j < c+size ; j++){
            int s = 0;
            for(int i = r; i < r + size ; i++){
                s+=arr[i][j];
            }
            if(s!=sum){
                return false;
            }
        }
        
        int s = 0;
        for(int i = r, j = c; i < r + size && j < c + size ; i++, j++){
            s+=arr[i][j];
        }
        if(s!=sum){
            return false;
        }
        s = 0;
        for(int i = r , j =c+size-1; i < r + size && j >=0 ; i++,j-- ){
            s+=arr[i][j];
        }
        
        return s==sum;
    }
}