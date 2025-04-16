/*Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false */

import java.util.*;


public class SquareWall{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0;i < n ; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(check(n, arr));
        cin.close();
    }
    static boolean check(int n , int arr[]){
        int sum = 0;
        for(int i : arr){
            sum+=i;
        }
        if(sum%4!=0){
            return false;
        }
        boolean [] pos = new boolean[n];
        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i < n; i++){
            if(pos[i]) continue;
            pos[i] = true;
            curr.add(arr[i]);
            if(!btrack(arr,i+1,arr[i],sum/4,res,curr,pos)){
                pos[i] = false;
            }
            curr.remove(curr.size()-1);
        }
        
        System.out.println(res);
        return false;
        
    }
    static boolean btrack(int arr[], int ind, int sum ,int reqSum, List<List<Integer>> res, List<Integer> curr, boolean pos[]){
        System.out.println(sum);
        System.out.println(curr);
        if(sum == reqSum){
            res.add(new ArrayList<>(curr));
            return true;
        }
        if((sum > reqSum) || (ind >= arr.length)){
            return false;
        }
        int n = arr.length;
        for(int i = ind; i < n; i++){
            if(pos[i]) continue;
            pos[i] = true;
            curr.add(arr[i]);
            if(btrack(arr,i+1,sum + arr[i],reqSum,res,curr,pos)){
                curr.remove(curr.size()-1);
                return true;
            }
            pos[i] = false;
            curr.remove(curr.size()-1);
        }
        
        return true;
        
    }
        
} 