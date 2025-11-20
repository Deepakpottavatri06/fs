'''A gaming site stores the reward points each player earned in a day as an array 
of integers. A reward is called “special” if its value is a positive multiple 
of 99.

Write a program to compute the total of the three highest special rewards.

    - If there are 3 or more special rewards, add the largest three.
    - If there are 1 or 2 special rewards, add all of them.
    - If there are no special rewards, the total should be 0.

Input format
------------
First line: an integer N – number of rewards.\
Second line: N space-separated integers – the reward points.

Output format
-------------
Print a single integer – the total of up to the three highest special rewards.

Example 1
Input
-----
5
99 198 297 298 345


Output
------
594

Example 2
Input
-----
6
0 1 2 4 98 450

Output
------
0'''
n = int(input())
arr = list(map(int,input().split()))

special =[0,0,0]
for i in arr:
    if i%99==0:
        if special[0]<i:
            temp = special[0]
            temp2 = special[1]
            special[0] = i
            special[1] = temp
            special[2] = temp2
        elif special[1] < i:
            temp2 = special[1]
            special[1] = i
            special[2] = temp2
        elif special[2] < i:
            special[2] = i
print(sum(special))