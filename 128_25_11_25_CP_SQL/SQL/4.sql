/*
List the names of all female students who belong to the “Computer Science” 
department.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
student_name                                                                    
Alice                                                                           
Isabella 

*/

use sndb;

-- write your query here

select student_name from students where dept_id in (select dept_id from departments where dept_name ="Computer Science") and gender = "Female";