/*
Write a query to find the total number of students in each department.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
dept_name           total_students                                                  
Computer Science    6                                                       
Electronics         5                                                               
Mechanical          4
*/

use sndb;

-- write your query here
with dept_s as (
    select d.dept_name , s.student_id from departments d join students s on d.dept_id = s.dept_id
)

select dept_name , count(student_id) as total_students from dept_s group by dept_name order by dept_name;