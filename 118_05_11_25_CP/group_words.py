arr = input().split()
mySet = set()
n = len(arr)
res =[]
def check(s,t):
    if(len(s)!=len(t)):
        return False
    size = len(s)
    diff = (ord(t[0])-ord(s[0])+26)%26
    for i in range(1,size):
        d = (ord(t[i])-ord(s[i])+26)%26 
        if(diff!=d):
            return False
    return True
for i in range(n):
    if(i in mySet):
        continue
    mySet.add(i)
    temp = [arr[i]]
    for j in range(i+1,n):
        if check(arr[i],arr[j]):
            temp.append(arr[j])
            mySet.add(j)
    temp.sort()
    res.append(temp)
print(res)
