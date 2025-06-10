'''
Write a python program to read a comma-separated values file and print its 
contents in table format, replacing commas with tabs or spaces.

Input File: 
------
file.csv

Output:
-------
name age
John 20
Jane 25

Explanation:
-------------
File contains:- 

name,age
John,20
Jane,25

'''

file_name = input();

import csv

with open(file_name,"r") as file:
    spamreader = csv.reader(file,delimiter=",",)
    for row in spamreader:
        print(" ".join(row))
    



