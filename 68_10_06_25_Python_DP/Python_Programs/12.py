'''

Write a program that finds the longest substring that reads the same forwards 
and backwards.

Input: 
------
babad

Output: 
-------
bab or aba

'''

inp = input()
n = len(inp)
max_len = 0
l_palinfrom = ""
for i in range(len(inp)):
    l : int = i
    r : int = i
    while(l>=0 and r<n):
        if(inp[l]!=inp[r]):
            break
        r+=1
        l-=1
        
    if(max_len<len(inp[l+1:r])):
        max_len = len(inp[l+1:r])
        l_palindrom = inp[l+1:r]
    l = i
    r = i +1
    while(l>=0 and r<n):
        if(inp[l]!=inp[r]):
            break
        r+=1
        l-=1
    if(max_len<len(inp[l+1:r])):
        max_len = len(inp[l+1:r])
        l_palindrom = inp[l+1:r]
    
print(l_palindrom)