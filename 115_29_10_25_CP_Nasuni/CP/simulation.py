'''

In a room there are 8 boxes in a row and each box is either locked or unlocked.
In each step, status of the boxes, modifies according to the following rules:
	- if the adjacent boxes of the box[i] (i.e., box[i-1] and box[i+1] ) are 
	  either both locked or both unlocked, then the box[i] becomes locked.
	- Otherwise box[i] is unlocked.
	- The first and the last boxes in the row can't have two adjacent boxes.

You are given the initial status of 8-boxes status[] array and an integer S, 
consist of either 0 or 1. where 1 indiactes locked status, 
0 indiactes unlocked status, and S is the number of steps.

Your task is to find and return the final status of the boxes after S steps,
either locked or unlocked.


Input Format:
-------------
Line-1: Eight space separated integers, initial status of the 8-boxes, 0 or 1 only
Line-2: An integer S, numebr of steps.

Output Format:
--------------
Print the list of integers, final status of the 8-boxes.


Sample Input-1:
---------------
1 1 0 0 1 0 0 1
6

Sample Output-1:
----------------
[0, 1, 0, 1, 1, 1, 1, 0]

Explanation:
------------
Initial status is Step-0:
Step-0: [1, 1, 0, 0, 1, 0, 0, 1]
Step-1: [0, 0, 0, 0, 1, 0, 0, 0]
Step-2: [0, 1, 1, 0, 1, 0, 1, 0]
Step-3: [0, 0, 0, 1, 1, 1, 1, 0]
Step-4: [0, 1, 0, 0, 1, 1, 0, 0]
Step-5: [0, 1, 0, 0, 0, 0, 0, 0]
Step-6: [0, 1, 0, 1, 1, 1, 1, 0]
Final status is Step-6.


Sample Input-2:
---------------
1 0 0 1 0 1 1 0
8

Sample Output-2:
----------------
[0, 0, 0, 1, 1, 0, 0, 0]

'''
boxes = list(map(int,input().split()))
res = boxes.copy()
s = int(input())
n = len(boxes)
seen = {}
states = []
for j in range(s):
    state = tuple(boxes)
    if state in seen:
        cycle_start = seen[state]
        cycle_len = j - cycle_start
        final = (s-cycle_start) % cycle_len
        print(list(seen[cycle_start+final]))
        break

    seen[state] = j
    states.append(state)
    res = [0]*n
    for i in range(1,n-1):
        if (boxes[i-1]+boxes[i+1] in [2,0]):
            res[i] = 1
        else:
            res[i] = 0
    # print(j+1,res)
    
    boxes = res
else:
    print(res)
    