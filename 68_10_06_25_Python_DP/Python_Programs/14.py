'''

Write a program that reads a sentence, count and display the total number of 
vowels and consonants.

Input: 
------
Hello World

Output:
-------
Vowels: 3, Consonants: 7

'''
vowels = ['a','e','i','o','u','A','E','I','O','U']

count = 0;

arr = input().split()
consonants = 0
for i in arr:
    v_count = 0
    for v in vowels:
        v_count+= i.count(v)
    consonants += len(i) - v_count
    count += v_count
        
print(f"Vowels: {count}, Consonants: {consonants}")
            