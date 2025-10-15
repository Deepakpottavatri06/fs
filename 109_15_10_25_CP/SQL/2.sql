/*
Write a SQL query to find the top-performing student (highest average grade) 
in each semester.



================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+------------+------------+-------------------+-----------+                                                                                           
| semester   | student_id | name              | avg_grade |                                                                                           
+------------+------------+-------------------+-----------+                                                                                           
| Fall2022   |          1 | Rohan Sharma      |         0 |                                                                                           
| Fall2022   |          3 | Vikram Rao        |         0 |                                                                                           
| Fall2022   |          5 | Manoj Deshmukh    |         0 |                                                                                           
| Fall2022   |          7 | Karan Mehra       |         0 |                                                                                           
| Fall2022   |          9 | Tarun Patel       |         0 |                                                                                           
| Fall2022   |         11 | Nikhil Chatterjee |         0 |                                                                                           
| Fall2022   |         13 | Arjun Varma       |         0 |                                                                                           
| Fall2022   |         15 | Harsh Vardhan     |         0 |                                                                                           
| Spring2023 |          2 | Sneha Reddy       |         0 |                                                                                           
| Spring2023 |          4 | Aditi Singh       |         0 |                                                                                           
| Spring2023 |          6 | Ritika Jain       |         0 |                                                                                           
| Spring2023 |          8 | Ankita Das        |         0 |                                                                                           
| Spring2023 |         10 | Megha Bhat        |         0 |                                                                                           
| Spring2023 |         12 | Deepika Ghosh     |         0 |                                                                                           
| Spring2023 |         14 | Shruti Kulkarni   |         0 |                                                                                           
+------------+------------+-------------------+-----------+


*/

use UDB;
with student_avg as (
    select student_id, avg(grade) as avg_grade, semester from
    results
    group by student_id, semester
)
select sa.semester,sa.student_id,s.name, sa.avg_grade 
from student_avg sa join students s on sa.student_id = s.student_id
order by sa.semester;