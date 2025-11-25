'''/*
Madhu is given strings, Word W and Pattern P.
W contains only lowercase letters, P contains lowercase letters and $, *.

In the pattern:
'$' indicates any single lowercase letter.
'*' indicates zero more number of lowercase letters.

Madhu's task is to find out, whether Pattern is matching the complete Word or not.
If pattern match the complete word, print true.
Otherwise false.

Input Format:
-------------
Two strings, Word and Pattern

Output Format:
--------------
Print the boolean result


Sample Input-1:
---------------
aa *

Sample Output-1:
----------------
true


Sample Input-2:
---------------
cb $a

Sample Output-2:
----------------
false


Sample Input-3:
---------------
abcde a*d*

Sample Output-3:
----------------
true


Sample Input-4:
---------------
acdcb a*c$b

Sample Output-4:
----------------
false

*/'''

s , t = input().split()
m ,n = len(s),len(t)

def dfs(ind1,ind2):
    if ind1==m and ind2==n:
        return True
    elif ind1>=m or ind2>=n:
        return False
    if t[ind2]=='*':
        for i in range(ind1+1,m+1):
            if(dfs(i,ind2+1)):
                return True
        return False
    elif t[ind2]=='$' or s[ind1]==t[ind2]:
        return dfs(ind1+1,ind2+1)
    else: #s[ind1]!=t[ind2]:
        return False
print(dfs(0,0))
    
    