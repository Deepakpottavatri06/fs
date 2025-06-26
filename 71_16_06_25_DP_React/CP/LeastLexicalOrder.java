/*Gopal would like to go on Tour, and planned a schedule.
Airport authority has created a new way of issuing tickets.
Gopal purchased a set of airline tickets, 
each ticket contains the 'departure from' and 'arrival to'.

Redesign the Gopal's tour schedule in an order.
Gopal intially must begin his tour from BZA.
Hence the tour schedule's start point should begin with BZA. 

You are given a list of flight tickets which Gopal has purchased.
Your task is to find out and return the tour schedule that has the least 
lexical order. Gopal must use all the tickets and only once.

Note:
------
If there are multiple valid schedules, you should return the schedule 
that has the smallest lexical order when read as a single string. 
For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

All airports are represented by three capital letters.
You may assume all tickets form at least one valid schedule.

Input Format:
-------------
Single Line of tickets separated by comma, and each ticket separated by space, 
Gopal's flight tickets.

Output Format:
--------------
Print the schedule, which is smallest lexical order of tour schedule.


Sample Input-1:
----------------
DEL HYD,BZA DEL,BLR MAA,HYD BLR

Sample Output-1:
--------------------
[BZA, DEL, HYD, BLR, MAA]


Sample Input-2:
------------------
BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

Sample Output-2:
------------------
[BZA, BLR, CCU, BZA, CCU, BLR]
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class LeastLexicalOrder{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp [] =  cin.nextLine().split(",");
            Map<String,List<String>> map = new HashMap<>();
            int edges = 0;
            for(String i: inp){
                String temp [] = i.split(" ");
                map.putIfAbsent(temp[0],new ArrayList<>());
                map.get(temp[0]).add(temp[1]);
                edges++;
            }
            System.out.println(find(map,edges));
        }
    }
    static List<String> find(Map<String,List<String>> map , int edges){
        // System.out.println("edges : "+edges);
        List<String> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        curr.add("BZA");
        dfs(res,curr,map ,"BZA",edges);
        return res;
    }
    static void dfs( List<String> res ,List<String> curr, 
    Map<String,List<String>> map, String node, int n ){
        
        // System.out.println(curr);
        List<String> children = map.getOrDefault(node,new ArrayList<>());
        Collections.sort(children);

        if(n==0){
            res.clear();
            res.addAll(curr);
            return;
        }
        for (int i = 0; i < children.size() ; i++){
           if(res.size()>1) break;
            String child = children.get(i);
            curr.add(child);
            children.remove(i);
            dfs(res,curr,map,child,n-1);
            curr.remove(curr.size()-1);
            children.add(i,child);
        }
        
        
    }
}