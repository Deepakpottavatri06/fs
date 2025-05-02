/* 21 Find employees who earn more than the average salary of all employees.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;

select ename , sal from emp  where sal> (select avg(sal) from emp);



/*22 Find the name and salary of the highest-paid employee.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;

select ename , sal from emp where sal  = (select max(sal) from emp);


/*23 Find employees who earn more than the highest-paid employee in department 10.

Expected Output Columns:
------------------------
+-------+---------+--------+
| ename | sal     | deptno |
+-------+---------+--------+

*/
USE test;

select ename, sal , deptno from emp where sal > (select max(sal) from emp where deptno = 10);



/*24 Find employees whose salary is higher than the salary of ‘SCOTT’.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;

select ename, sal from emp where sal > (select sal from emp where ename = "SCOTT");


/*25 Find employee who have the same job title as 'FORD'.

Expected Output Columns:
------------------------
+-------+---------+
| ename | job     |
+-------+---------+

*/
USE test;

select ename , job from emp where job in (select job from emp where ename = "FORD");


/* 26 Find departments that have at least one employee earning more than 3000.

Expected Output Columns:
------------------------
+--------+------------+
| deptno | dname      |
+--------+------------+

*/
USE test;

select emp.deptno , dept.dname from emp,dept where emp.deptno = dept.deptno and emp.sal>3000;


/*27 Find employees who were hired before all employees in department 30.

Expected Output Columns:
------------------------
+-------+------------+
| ename | hiredate   |
+-------+------------+

*/
USE test;

select ename , hiredate from emp where hiredate < All(select hiredate from emp where deptno = 30);


/*28 Find employees who belong to departments located in 'Dallas'.

Expected Output Columns:
------------------------
+-------+--------+
| ename | deptno |
+-------+--------+

*/
USE test;

select emp.ename , dept.deptno from emp,dept where emp.deptno = dept.deptno and dept.location = "Dallas";


/* 29 Find the second highest salary from employees.

Expected Output Columns:
------------------------
+-----------------------+
| second_highest_salary |
+-----------------------+

*/
USE test;

select sal as second_highest_salary from emp order by sal desc limit 1 offset 2;

/*30 Find employees who have the same manager as ‘BLAKE’.

Expected Output Columns:
------------------------
+-------+------+
| ename | mgr  |
+-------+------+

*/
USE test;

select ename , mgr from emp where mgr = (select mgr from emp where ename="BLAKE") and ename!="BLAKE";