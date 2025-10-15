n = int(input())
arr = list(map(int,input().split(" ")))

s1,s2 = [0,0] , [sum(arr[0::2]),sum(arr[1::2])]
count = 0
for i in range(len(arr)):
    s2[i%2]-=arr[i]
    count += (s1[0]+s2[1]==s1[1]+s2[0])
    s1[i%2]+=arr[i]
print(count)