'''Write a python program to find the nth prime number. 
The value of n should be input by the user.

Sample Input:
---------------
5

Sample Output:
-----------------
11
'''
import math

def isprime(n : int)-> bool:
    if(n<=1):
        return False
    elif(n==2):
        return True
    for i in range(2,int(math.sqrt(n))+1):
        if(n%i==0):
            return False
    return True


n : int = int(input())
curr : int = 2
count : int = 0
while count < n:
    if isprime(curr):
        count+=1
    curr+=1
print(curr-1)
