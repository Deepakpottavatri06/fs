'''There are S counters[] in a Restaurant and T orders[] made by customers,
counters[i] is the capacity of the i-th counter, 
and orders[k] is the time required to prepare the k-th order, in minutes.
Both the counters[] and orders[] are 0-indexed arrays.

Orders are assigned to the counters using an order queue. 
Initially, all counters are available, and the queue is empty.

At k-th minute, the k-th order is entered into the queue
(starting with the 0th order being entered at 0th minute). 

As long as the counters are available and the queue is not empty, 
the first order in the queue will be assigned to an available counter 
with the smallest capacity, and in case of a tie, it is assigned to
an available counter with the smallest index.

If there are no counters available and the queue is not empty, 
wait until a counter becomes available and immediately assign the next order.
If multiple counters become available at the same time, then multiple orders
from the queue will be assigned in order of entrance, following the capacity 
and index priorities above.

A counter that is assigned an order k at minute M will be free again 
at (M + orers[k]) minute.

Your Order is to build an array result[] of length T, where result[k] is
the index of the counter the k-th order will be assigned to, and print it.


Input Format:
-------------
Line-1: Two space separated integers, values of S and T.
Line-2: S space separated integers, 
Line-3: T space separated integers, 

Output Format:
--------------
Print T space separated integers, the final result.


Sample Input-1:
---------------
3 7
8 8 6
1 2 2 3 2 1 2

Sample Output-1:
----------------
2 2 0 2 0 1 2

Explanation: Events in chronological order go as follows:
- At minute-0, order-0 is entered and prepared using counter-2 until minute-1.

- At minute-1, counter-2 becomes available. order-1 is entered and prepared
  using counter-2 until minute-3.

- At minute-2, order-2 is entered and prepared using counter-0 until minute-4.

- At minute-3, counter-2 becomes available. order-3 is entered and prepared 
  using counter-2 until minute-6.

- At minute-4, counter-0 becomes available. order-4 is entered and prepared
  using counter-0 until minute-6.

- At minute-5, order-5 is entered and prepared using counter-1 until minute-6.

- At minute-6, all counters are available. order-6 is entered and prepared 
  using counter-2 until minute-8.

Sample Input-2:
---------------
4 8
8 2 6 4
1 2 3 4 5 6 7 8

Sample Output-2:
----------------
1 1 3 1 2 3 0 1
'''
import heapq
s,t = map(int,input().split())
counters = list(map(int,input().split()))
orders = list(map(int,input().split()))

available = [(counters[i],i) for i in range(s)]
heapq.heapify(available)
queue = []
ind = 0
min = 0
res = []
while ind<t:
    if not available:
        min = max(queue[0][0],min)
    while len(queue)!=0 and queue[0][0]<=min:
        count : tuple = heapq.heappop(queue)
        heapq.heappush(available,count[1])
        # print("released from queue : ",count)
    while (ind<t) and len(available)!=0 and min>=ind:
        counter = heapq.heappop(available)
        # print(counter)
        heapq.heappush(queue,(min+orders[ind],counter))
        res.append(counter[1])
        ind+=1
    min+=1
    
print(*res)

        
'''
import heapq
from collections import deque

s,t = map(int,input().split())
counters = list(map(int,input().split()))
orders = list(map(int,input().split()))

available = [(counters[i], i) for i in range(s)]
heapq.heapify(available)

busy = []  # (free_time, capacity, index)
wait = deque()  # waiting orders

res = [0] * t
time = 0
ind = 0

while ind < t :

    # Add arriving orders
    if ind < t and time >= ind:
        wait.append(ind)
        ind += 1

    # Free busy counters whose time is up
    while busy and busy[0][0] <= time:
        free_time, cap, idx = heapq.heappop(busy)
        heapq.heappush(available, (cap, idx))

    # Assign while both order & counter exist
    while wait and available:
        oid = wait.popleft()
        cap, idx = heapq.heappop(available)
        res[oid] = idx
        finish = time + orders[oid]
        heapq.heappush(busy, (finish, cap, idx))

    # Advance time
    if wait and not available:
        time = busy[0][0]
    else:
        time += 1

print(*res)

'''