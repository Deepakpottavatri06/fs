m,n = list(map(int,input().split()))
mat = []
for i in range(m):
    mat.append(list(map(int,input().split())))
req_set = set([i for i in range(1,10)])
count = 0
for i in range(m-2):
    for j in range(n-2):
        block = [mat[i + r][j + c] for r in range(3) for c in range(3)]
        if set(block) != req_set:
            continue
        row_sum = [sum(mat[i+r][j+c] for c in range(3)) for r in range(3)]
        col_sum = [sum(mat[i+r][j+c] for r in range(3)) for c in range(3)]
        diag_sum = sum(mat[i+d][j+d] for d in range(3))
        anti_diag_sum = mat[i][j+2] + mat[i+1][j+1]+ mat[i+2][j]
        
        total_sums = row_sum+col_sum + [diag_sum,anti_diag_sum]
        # print(total_sums)
        if(len(set(total_sums))==1):
            count+=1
print(count)