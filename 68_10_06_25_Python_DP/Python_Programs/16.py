'''

Write a python program, for given a birthdate in yyyy-MM-dd format, calculate 
the person’s current age in years, months, and days.

Input:
------
1990-05-25

Output:
-------
Age: 35 years, 0 months, 16 days

'''

from datetime import datetime , date
t = input()

d = datetime.strptime(t,"%Y-%m-%d").date()

today = date.today();
year = today.year - d.year
months = today.month - d.month
days = today.day - d.day
if(months<0):
    months+=12
    year-=1
if(days < 0):
    last_day = (today.replace(day=1) - date.resolution).day
    days = last_day - d.day + today.day
    months-=1
print(f"Age: { year} years, {months} months, {days} days")