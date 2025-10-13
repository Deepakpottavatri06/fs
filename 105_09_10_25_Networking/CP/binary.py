# Mounika is creating the binary strings using P 1's and Q 0's.
# A binary string contains only 0's and 1's.
# She has given a list of binary strings binStr[] to be formed.

# Her task is to find, the maximum number of binary strings can be formed, 
# with given P 1's and Q 0's only. She cannot use any more 1's or 0's.

# Input Format:
# -------------
# Line-1 -> A single line of space separated binary strings, binStr[].
# Line-2 -> Two integers P and Q, P number of 1's and Q number of 0's


# Output Format:
# --------------
# Print an integer as output, maximum number of binary strings can be formed.


# Sample Input-1:
# ---------------
# 10 0001 111001 1 0
# 3 5

# Sample Output-1:
# ----------------
# 4

# Explanation:
# ------------
# She can form 4 strings by the using of 3 1's and 5 0's
# strings are 10, 0001, 1, 0.


# Sample Input-2:
# ---------------
# 10 1 0
# 1 1

# Sample Output-2:
# ----------------
# 2

# Explanation:
# ------------
# She can form 2 strings by the using of 1 1's and 1 0's
# strings are 1, 0.

x : list = list(input().split(" "));
p,q = list(map(int,input().split(" ")));

ones : list = [x[i].count('1') for i in range(len(x))]
zeroes : list = [x[i].count('0') for i in range(len(x))]

dp : dict = {}

def recur(ones: list,zeroes : list,ind : int,p: int,q : int)-> int:
    if(ind==len(ones)):
        return 0
    key : str = str(ind)+"-"+str(p)+"-"+str(q)
    if key in dp.keys():
        return dp[key]
    take : int = 0
    if(p>=ones[ind] and q>=zeroes[ind]):
        take = 1 + recur(ones,zeroes,ind+1,p-ones[ind],q-zeroes[ind])
    skip = recur(ones,zeroes,ind+1,p,q)
    dp[key] = max(skip,take)
    return dp[key]
print(recur(ones,zeroes,0,p,q))
        
    


