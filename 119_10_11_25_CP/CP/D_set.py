from functools import lru_cache
n ,d = list(map(int,input().split()))
arr = list(map(int,input().split()))

@lru_cache
def recur(ind,prev_ind):
    if(ind>=n):
        return 0
    take = 0
    if prev_ind==-1 or arr[ind]-arr[prev_ind]==d:
        take = 1 + recur(ind+1,ind)
    skip = recur(ind+1,prev_ind)
    return max(take,skip)

print(recur(0,-1))

