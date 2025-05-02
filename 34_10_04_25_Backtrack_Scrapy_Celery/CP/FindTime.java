/*Imagine a unique conveyor belt in a sushi restaurant, which serves 
exactly 26 distinct dishes labeled by 26 unique lowercase English letters 
('a' to 'z'). The dishes are arranged linearly along the conveyor belt, indexed 
from 0 to 25 according to the given order. Initially, a robotic serving arm is 
positioned at index 0.

Whenever a customer orders a specific dish, the robotic arm moves from its current 
position to the position of the desired dish along the belt. The robotic arm takes 
exactly one second per unit distance to move along the conveyor belt (the time 
taken from index i to index j is |i - j| seconds).

Given the conveyor belt layout (order of dishes) and a customer's order represented 
as a word (sequence of dishes), write a code to calculate the total time the robotic 
serving arm will take to serve the customer's entire order.


Input Format:
-------------
Line-1: A String, belt layout.
Line-2: A String, word: customer's order.

Output Format:
--------------
An integer T, time to serve.


Sample Output-1:
----------------
abcdefghijklmnopqrstuvwxyz
code

Sample Output-1:
----------------
26 */

import java.util.*;

public class FindTime{
    public static void main(String [] ar){
        Scanner cin = new Scanner(System.in);
        String inp = cin.nextLine();
        Map<Character,Integer> map = new HashMap<>();
        int l = 0;
        for(char c: inp.toCharArray()){
            map.put(c,l);
            l++;
        }
        String word = cin.nextLine();
        System.out.println(find(word,map));
        
        cin.close();
    }
    static int find(String word,Map<Character,Integer> map){
        int time = 0;
        int currentPos = 0;
        for(char c: word.toCharArray()){
            time += Math.abs(currentPos - map.get(c));
            currentPos = map.get(c);
        }
        return time;
    }
}