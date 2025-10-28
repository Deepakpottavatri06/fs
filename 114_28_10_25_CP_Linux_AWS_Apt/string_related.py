'''
Reena has to sent the mails regularly.
She used emphasize any word W based on a given set of words[].
Two emphasize the words she used enclose them with <i> and </i> tag.
The emphasize procedure of the sub-words of W is as follows:
	
	- If two sub-words are intersected with each other, 
	  enclose them with one <i></i> tag.
	
	- If two sub-words are enclosed with <i></i> tag and neighbours also,
	  then merge the sub-words as one and enclose with one <i> </i> tag.

You will be given the word W, and set of words[] to emphasize.
Your task is to help Reena to emphasize the word W.
and print it.

Input Format:
-------------
Line-1: A string W, the word.
Line-2: space separated strings, set of words[].

Output Format:
--------------
Print the string, the emphasized string.

Sample Input-1:
---------------
kmitngit123
it 123

Sample Output-1:
----------------
km<i>it</i>ng<i>it123</i>


Sample Input-2:
---------------
qwertykeypad
qwerty key pad

Sample Output-2:
----------------
<i>qwertykeypad</i>


'''
s = input()
words = list(input().split())
n = len(s)
mask = [False]*n

for w in words:
    start = s.find(w)
    while start!=-1:
        for i in range(start,len(w)+start):
            mask[i] = True
        start = s.find(w,start+1)
res = []
i = 0
while i < n:
    if mask[i]:
        res.append("<i>")
        while i < n and mask[i]:
            res.append(s[i])
            i += 1
        res.append("</i>")
    else:
        res.append(s[i])
        i += 1

print("".join(res))
    

