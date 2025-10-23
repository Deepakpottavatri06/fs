'''
In a hierarchical research institute, every scientist reports to a supervising 
scientist. Given two scientists, find the nearest common supervisor (LCS) who 
oversees both (directly or indirectly).

                Director
                /      \
         Dr.Alpha       Dr.Delta
         /     \          /     \
   Dr.Beta   Dr.Gamma  Dr.Epsilon  Dr.Zeta

Input Format
------------
You’ll be given:
    - A set of parent–child relationships defining the hierarchy.
    - Two scientist names whose nearest common supervisor needs to be found.

Output Format:
--------------
A string, Name of LCS

Sample Input:
-------------
hierarchy = {
    "Director": None,
    "Dr.Alpha": "Director",
    "Dr.Delta": "Director",
    "Dr.Beta": "Dr.Alpha",
    "Dr.Gamma": "Dr.Alpha",
    "Dr.Epsilon": "Dr.Delta",
    "Dr.Zeta": "Dr.Delta"
}
query = ("Dr.Beta", "Dr.Gamma")

Sample Output:
--------------
Dr.Alpha

'''

import json
s = input()
binary_relation = eval(s)
query = eval(input())

# print(s)
# print(query)

class Node:
    def __init__(self,value):
        self.value = value
        self.left = None
        self.right = None
    
root = None
nodes = {}
for child ,parent in binary_relation.items():
    childNode = None
    if child not in nodes:
        childNode = Node(child)
        nodes[child] = childNode
    else: 
        childNode = nodes[child]
    
    if parent is None:
        root = childNode
    elif parent in nodes:
        parentNode = nodes[parent]
        if parentNode.left is None:
            parentNode.left = childNode
        elif parentNode.right is None:
            parentNode.right = childNode
            
    
def findLCA(root,p,q):
    if root is None:
        return None
    
    if (root.value == p or root.value == q):
        return root
    left = findLCA(root.left,p,q)
    right = findLCA(root.right,p,q)
    
    if(left is not None and right is not None):
        return root
    elif(left is None):
        return right
    return left

print(findLCA(root,query[0],query[1]).value)  # type: ignore
        
