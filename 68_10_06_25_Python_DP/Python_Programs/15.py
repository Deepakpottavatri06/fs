'''

Write a python program that reads two timestamps (yyyy-MM-dd HH:mm:ss format) and
display the time elapsed between them in minutes and seconds.

Input: 
------
2025-06-04 10:30:00
2025-06-04 11:15:40

Output: 
-------
Elapsed: 45 minutes 40 seconds

'''

from datetime import datetime

t1 = input()
t2 = input()

t1 = datetime.strptime(t1,"%Y-%m-%d %H:%M:%S")
t2 = datetime.strptime(t2,"%Y-%m-%d %H:%M:%S")

elapsed = t2 - t1
t_secs = int(elapsed.total_seconds())

min = t_secs//60
secs = t_secs%60
print(f"Elapsed: {min} minutes {secs} seconds")