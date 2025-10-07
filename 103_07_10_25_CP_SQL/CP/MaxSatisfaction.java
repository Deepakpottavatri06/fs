/* The university’s digital library is designing an AI-powered Reading Planner.
Each book belongs to a certain category and offers a satisfaction score (based 
on reader feedback) and requires a certain reading time (in hours).

A reader wants to create a personalized reading plan that:
    - Maximizes total satisfaction.
    - Does not exceed the total available reading time T.   
    - Does not include duplicate books (some books appear in multiple categories).

Each category offers one or more books with satisfaction and reading time values.

Input Format:
-------------
N M T
<for M categories:>
bCount bookID_1 time_1 score_1 bookID_2 time_2 score_2 ...

where:
N = number of unique books in the library
M = number of categories
T = total time available to read (like a knapsack limit)

Output Format:
--------------
Maximum total satisfaction achievable without exceeding total time T.


Sample Input:
-------------
8 4 10
2 1 2 50 2 3 60
2 3 4 80 4 5 90
3 5 3 70 6 4 85 7 2 40
2 1 5 100 8 3 60

Sample Output:
--------------
215

Explanation:
------------

CombinationBooks    Total_TimeTotal_Score        Valid?
--------------------------------------------------------------
(2, 4, 7)3 + 5 + 210        60 + 90 + 40 = 190✅
(2, 4, 8)3 + 5 + 311        60 + 90 + 60 = 210❌ (too long)
(2, 6, 7)3 + 4 + 29        60 + 85 + 40 = 185✅
(4, 7, 8)5 + 2 + 310        90 + 40 + 60 = 190✅
(1, 3, 6)2 + 4 + 410        50 + 80 + 85 = 215  ✅(Accepted )
(1, 3, 8)2 + 4 + 39        50 + 80 + 60 = 190  ✅
*/

import java.util.*;

class Book{
    int bookId;
    int time;
    int score;
    Book(int id, int t, int s){
        this.bookId = id;
        this.time = t;
        this.score = s;
    }
}

public class MaxSatisfaction {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        List<List<Book>> categories = new ArrayList<>();
        
        for(int i = 0; i < m; i++){
            List<Book> temp = new ArrayList<>();
            int len = sc.nextInt();
            for(int j = 0; j < len; j++){
                temp.add(new Book(sc.nextInt(),sc.nextInt(),sc.nextInt()));
            }
            categories.add(temp);
        }
        System.out.println(find(categories,n,m,t));
        sc.close();
    }
    static int find(List<List<Book>> categories, int n, int m, int t){
        boolean used [] = new boolean[n+1];
        return recur(categories,0,0,t,used);
    }
    static int recur( List<List<Book>> categories,int ind, int currTime, int t, boolean used[]){
        if(ind>=categories.size()){
            return 0;
        }
        
        int skip = recur(categories,ind+1,currTime,t,used);
        int take = 0;
        for(Book b : categories.get(ind)){
            if(currTime + b.time <= t && !used[b.bookId]){
                used[b.bookId] = true;
                take = Math.max(take,b.score + recur(categories,ind+1,currTime + b.time,t,used));
                used[b.bookId] = false;
            }
        }
        
        return Math.max(skip,take);
    }
} 
