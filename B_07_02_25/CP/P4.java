/*In a software development company, a team works on various projects over n weeks. 
The team completes a certain number of tasks tasks[i] each week and dedicates 
hours[i] hours of work. Given an integer k, for every consecutive sequence of 
k weeks (tasks[i], tasks[i+1], ..., tasks[i+k-1] and 
hours[i], hours[i+1], ..., hours[i+k-1] for all 0 <= i <= n-k), 
they evaluate T, the total number of tasks completed during that sequence 
of k weeks, and E, the total hours of work during that sequence of k weeks:

a) If T < lower and E >= work_goal, the team performed very poorly and loses 2 points
b) If T < lower and E < work_goal, the team performed poorly and loses 1 point.
c) If T >= upper and E >= work_goal, the team performed well and gains 1 point.
d) If T >= upper and E < work_goal, the team performed exceptionally well and gains 2 points.
e) Otherwise, the team's performance is normal and there is no change in points.

Initially, the team starts with zero points. Return the total number of points 
the team has after working for n weeks. Note that the total points can be negative.

Example 1
Input:
n = 5
tasks = [10, 20, 30, 40, 50]
hours = [30, 20, 10, 30, 40]
k = 2
lower = 35
upper = 70
work_goal = 45

Output: 1
Explanation:
For [10, 20] and [30, 20]:
T = 30 < lower and E = 50 >= work_goal, the team performed very poorly and loses 2 points.

For [20, 30] and [20, 10]:
T = 50 >= lower and T <= upper and E = 30 < work_goal, no change in points.

For [30, 40] and [10, 30]:
T = 70 = upper and E = 40 < work_goal, the team performed exceptionally well and 
gains 2 points.

For [40, 50] and [30, 40]:
T = 90 > upper and E = 70 >= work_goal, the team performed well and gains 1 point.

Therefore, the team gains 1 point (0 - 2 + 2 + 1 = 1).

Sample Input=
4	//n
5 8 10 15
25 30 20 25
3	//k
25	//lower
40	//upper
60	//work_goal
Sample Output=
-2 */

import java.util.*;
public class P4{
    public static void main(String [] args){
        Scanner cin  = new Scanner(System.in);
        int n = cin.nextInt();
        int tasks [] = new int[n];
        int hours [] = new int[n];
        for(int i =0 ; i < n ; i++){
            tasks[i] = cin.nextInt();
        }
        for(int i =0 ; i < n ; i++){
            hours[i] = cin.nextInt();
        }
        int k = cin.nextInt();
        int l = cin.nextInt();
        int u = cin.nextInt();
        int work_goal = cin.nextInt();
        System.out.println(solve(n,tasks,hours,k,l,u,work_goal));
        cin.close();
    }
    static int solve(int n, int tasks[], int hours[],int k,int lower, int upper,int work_goal){
        int t = 0;
        int e = 0;
        int score = 0;
        for(int i = 0; i < k ; i++){
            t+=tasks[i];
            e+=hours[i];
        }
        if(t < lower && e >= work_goal){
            score-=2;
        }
        else if (t < lower && e < work_goal) score--;
        else if ( t >= upper && e >= work_goal) score++;
        else if(t >= upper && e < work_goal) score+=2;
        int ind = 0;
        for(int i = k; i < n; i++){
            t +=tasks[i];
            t-=tasks[ind];
            e+=hours[i];
            e-=hours[ind++];
         if(t < lower && e >= work_goal){
            score-=2;
        }
        else if (t < lower && e < work_goal) score--;
        else if ( t >= upper && e >= work_goal) score++;
        else if(t >= upper && e < work_goal) score+=2;
        }
        return score;
    }
}