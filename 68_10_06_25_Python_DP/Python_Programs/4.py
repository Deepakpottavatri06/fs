'''Write a python program to convert a decimal number to binary using both
1. Recursive method
2. Iterative method

Implement the methods in Solution class and return the binary number.

Sample Input:
---------------
10

Sample Output:
------------------
Binary (Recursive): 1010
Binary (Iterative): 1010
'''
class Solution:
    def __init__(self, num: int):
        self.num = num
    
    def iterative(self):
        res = ""
        n  = self.num
        while(n>=2):
            res += str((n%2))
            n = n//2
        res+=str(n)
        return res[::-1]
    def recursive(self,curr):
        if curr<2:
            return curr
        
        return  str(self.recursive(curr//2)) + str(curr%2)
        
        
        
obj = Solution(int(input()))

print(f"Binary (Recursive): {obj.iterative()}")
print(f"Binary (Iterative): {obj.recursive(obj.num)}")
