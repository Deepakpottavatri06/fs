/*31 Find employees who belong to a department with no employees.

Expected Output Columns:
------------------------
+---------+
| dname   |
+---------+

*/
USE test;

select dname from dept where deptno not in (select deptno from emp);


/*32 Find the department that has the most employees.

Expected Output Columns:
------------------------
+--------+----------------+
| deptno | employee_count |
+--------+----------------+

*/
USE test;

select deptno , count(ename) as employee_count from emp
group by deptno order by count(ename) desc limit 1;


/*33 Find the department name where ‘JONES’ works.

Expected Output Columns:
------------------------
+----------+
| dname    |
+----------+

*/
USE test;

select dname from dept where deptno in (select deptno from emp where ename = "JONES");


/*34 Write a SQL query to find employees whose name contains ‘A’.

Expected Output Columns:
------------------------
+--------+-------+
| ename  | empno |
+--------+-------+

*/
USE test;

select ename, empno from emp where ename like "%A%";


/*35 Retrieve employees who have a commission greater than their salary.

Expected Output Columns:
------------------------
+--------+-------+---------+---------+
| ename  | empno | sal     | comm    |
+--------+-------+---------+---------+

*/
USE test;

select ename , empno , sal , comm from emp where comm > sal;