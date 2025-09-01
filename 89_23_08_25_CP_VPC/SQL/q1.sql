/*
Display the top 3 students with highest GPA.
        

-- 1. Teachers Table Fields
(
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100) UNIQUE
);

-- 2. Students Table Fields
 (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    enrollment_year YEAR,
    email VARCHAR(100) UNIQUE
);

-- 3. Courses Table Fields
 (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
);

-- 4. Results Table Fileds
 (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    grade CHAR(2),
    semester VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

  Sample output:
  --------------
student_id	name	        gpa
12	        Deepika Ghosh	3.85
4	        Aditi Singh	    3.85



*/

use fstest;

WITH grade_points AS (
    SELECT 
        student_id,
        CASE grade
            WHEN 'A+' THEN 4.0
            WHEN 'A'  THEN 4.0
            WHEN 'A-' THEN 3.7
            WHEN 'B+' THEN 3.3
            WHEN 'B'  THEN 3.0
            WHEN 'B-' THEN 2.7
            ELSE 0
        END AS point
    FROM results
)
SELECT 
    s.student_id,
    s.name,
    ROUND(AVG(g.point), 2) AS gpa
FROM students s
JOIN grade_points g ON s.student_id = g.student_id
GROUP BY s.student_id, s.name
ORDER BY gpa DESC
LIMIT 3;


