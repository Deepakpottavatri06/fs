def countNumbersWithUniqueDigits(n):
        if(n==0): return 1
        res = 0
        for i in range(1,n+1):
            if(i==1):
                res+=10
            else:
                temp = 9
                count = 9
                for j in range(1,i):
                    temp *= count
                    count-=1
                res += temp
        return res

print(countNumbersWithUniqueDigits(int(input())))