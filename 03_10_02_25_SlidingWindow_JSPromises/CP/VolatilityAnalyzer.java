/*
 You are given an integer array nums and two integers l and r. Your task is to 
analyze the volatility of a sequence of values. The volatility of a sequence is 
defined as the difference between the maximum and minimum values in that sequence.

You need to determine the sequence with the highest volatility among all 
sequences of length between l and r (inclusive).

Return the highest volatility. If no such sequence exists, return -1.

Input Format:
-------------
Line-1: 3 space separated integers, n, l, r
Line-2: n space separated integers, nums[].

Output Format:
-------------
An integer, the highest volatility.


Sample Input-1:
---------------
5 2 3
8 3 1 6 2

Sample Output-1:
----------------
7

Explanation:
------------
The possible sequences of length between l = 2 and r = 3 are:

[8, 3] with a volatility of 8−3=5
[3, 1] with a volatility of 3−1=2
[1, 6] with a volatility of 6−1=5
[8, 3, 1] with a volatility of 8−1=7
The sequence [8, 3, 1] has the highest volatility of 7.

Sample Input-2:
---------------
4 2 4
5 5 5 5

Sample Output-2:
----------------
0

Explanation:
------------
All possible sequences have no volatility as the maximum and minimum values 
are the same, resulting in a difference of 0.
 
 */

 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.Scanner;
import java.util.TreeMap;
 
 public class VolatilityAnalyzer {
 
     public static void main(String[] args) {
         Scanner cin = new Scanner(System.in);
         int n = cin.nextInt();
         int l  =cin.nextInt();
         int r = cin.nextInt();
         int arr [] = new int[n];
         for (int i = 0; i < arr.length; i++) {
             arr[i] = cin.nextInt();
         }
         System.out.println(solve(n,l,r,arr));
         cin.close();
     }
     static int solve(int n , int l , int r ,int arr []){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int max = 0 ;
        for(int i = 0; i < r; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        max = Math.max(max,map.lastKey()-map.firstKey());
        int ind = 0;
        for (int i = r; i < arr.length; i++) {
            map.put(arr[ind], map.getOrDefault(arr[ind], 0)-1);
            if(map.get(arr[ind])==0){
                map.remove(arr[ind]);
            }
            ind++;
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
            max = Math.max(max,map.lastKey()-map.firstKey());
        }
        return max;
     }
 }