/*
Write a SQL query to find the names of departments that have at least one male 
and one female student.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
dept_name                                                                       
Computer Science                                                                
Electronics                                                                     
Mechanical

*/

use sndb;

-- write your query here
select d.dept_name from departments d join students s
on d.dept_id = s.dept_id
group by d.dept_id
having count(distinct s.gender)=2;
