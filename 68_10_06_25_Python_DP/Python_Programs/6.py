'''

Write a Python code to return the length of longest sub-string without repeating 
characters

Sample Input: 
-------------
abcabcbb

Sample Output: 
--------------
3

'''

freq = set()
string = input()
n = len(string)

i = 0
j = 0
max_size : int = 0
while(j < n):
    if(string[j] in freq):
        freq.remove(string[i])
        i+=1
    freq.add(string[j])
    j+=1
    max_size = max(max_size,len(freq))
    
print(max_size)
    