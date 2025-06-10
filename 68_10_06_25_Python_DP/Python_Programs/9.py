'''

Write python program to find and return minimum number of denominations – given 
an infinite supply of each denomination of {1, 2, 5, 10, 20, 50, 100, 200,500, 
2000} and a target value N

Sample Input: 
-------------
187

Sample Output: 
--------------
[100, 50, 20, 10, 5, 2]

'''

n = int(input())
deno = [1, 2, 5, 10, 20, 50, 100, 200,500, 2000]

deno = deno[::-1]
res = []

for i in deno:
    if(n>=i):
        res.extend([i]*(n//i))
        n = n%i

print(res)
