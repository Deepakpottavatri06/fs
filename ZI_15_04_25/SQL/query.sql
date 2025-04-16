/* 36: Write a SQL query to find all employees who do not receive a commission.

Expected Output Columns:
------------------------
+-------+-------+------+
| ename | empno | comm |
+-------+-------+------+

*/
USE test;

select ename , empno , comm from emp where comm = 0 or comm is null;


/* 37: Write a SQL query to count the number of employees who have a manager.

Expected Output Columns:
------------------------
+------------------------+
| employees_with_manager |
+------------------------+

*/
USE test;

select count(ename) as  employees_with_manager from emp where mgr is not null;


/*38: Write a SQL query to find the difference between the highest and second highest salary.

Expected Output Columns:
------------------------
+-------------------+
| salary_difference |
+-------------------+

*/
USE test;

select (select max(sal) from emp) - (select sal from emp order by sal desc limit 1 offset 1) as salary_difference;


/*39: Write a SQL query to calculate the total salary and total commission for all employees.

Expected Output Columns:
------------------------
+--------------+------------------+
| Total Salary | Total Commission |
+--------------+------------------+

*/
USE test;

select sum(sal) as "Total Salary", sum(comm) as "Total Commission" from emp;

/* 40: Write a SQL query to calculate the average salary and average commission of employees.

Expected Output Columns:
------------------------
+----------------+--------------------+
| Average Salary | Average Commission |
+----------------+--------------------+

*/
USE test;

select avg(sal) as "Average Salary" , avg(comm) as "Average Commission" from emp;


/*41: Write a SQL query to calculate the average salary of employees with a commission.

Expected Output Columns:
------------------------
+----------------------+
| avg_salary_with_comm |
+----------------------+

*/
USE test;

select avg(sal) as avg_salary_with_comm from emp where comm is not null;


/* 42: Write a SQL query to determine the minimum commission value, excluding NULLs.

Expected Output Columns:
------------------------
+----------------+
| min_commission |
+----------------+

*/
USE test;

select min(comm) as min_commission from emp ;


/* 43: Write a SQL query to find the total commission paid to employees hired after 1995.

Expected Output Columns:
------------------------
+----------------------+
| total_comm_post_1995 |
+----------------------+

*/
USE test;

select sum(comm) as total_comm_post_1995 from emp where year(hiredate) > 1995;


/* 44: Write a SQL query to find the maximum hire date (latest hire) in the emp table.

Expected Output Columns:
------------------------
+-------------+
| latest_hire |
+-------------+

*/
USE test;

select hiredate as latest_hire from emp order by hiredate desc limit 1;

/* 45: Write a SQL query to find the average commission for salesmen, excluding NULLs.

Expected Output Columns:
------------------------
+-------------------+
| avg_salesman_comm |
+-------------------+

*/
USE test;

select avg(comm) as avg_salesman_comm from emp where job = 'SALESMAN' ;


/* 46: Write a SQL query to determine the minimum salary for employees hired in the 1990s.

Expected Output Columns:
------------------------
+----------------+
| min_salary_90s |
+----------------+

*/
USE test;

select min(sal) as min_salary_90s from emp where year(hiredate) >= 1990 and year(hiredate) < 2000 ;


/*47: Write a SQL query to find the total salary of employees whose names start with 'K'.

Expected Output Columns:
------------------------
+----------------+
| total_salary_k |
+----------------+

*/
USE test;

select sum(sal) as total_salary_k from emp where ename like "K%";


/* 48:Write a SQL query to count the number of employees hired in each year.

Expected Output Columns:
------------------------
+-----------+----------------+
| hire_year | hires_per_year |
+-----------+----------------+

*/
USE test;

select year(hiredate) as hire_year, count(empno) as hires_per_year from emp group by year(hiredate);


/*49: Write a SQL query to sum the commissions for employees with salaries below 1500.

Expected Output Columns:
------------------------
+-----------------------+
| total_comm_low_salary |
+-----------------------+

*/
USE test;


select sum(comm) as total_comm_low_salary from emp where sal < 1500;



/*50: List employees who do not receive a commission but earn more than 2500.

Expected Output Columns:
------------------------
+-------+---------+------+
| ename | sal     | comm |
+-------+---------+------+

*/
USE test;


select ename, sal , comm from emp where sal > 2500 and comm is null;