/*
Write a SQL query using CTEs and ranking functions to identify the most active 
reviewer in each decade. 

Display:
--------
    decade
    reviewer_name
    review_count

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+---------------+--------------+                                                                                                             
| decade | reviewer_name | review_count |                                                                                                             
+--------+---------------+--------------+                                                                                                             
|   1970 | Bob Smith     |            1 |                                                                                                             
|   1980 | Jane Doe      |            2 |                                                                                                             
|   1990 | Alice Johnson |            1 |                                                                                                             
|   1990 | Olivia Taylor |            1 |                                                                                                             
|   1990 | Bob Smith     |            1 |                                                                                                             
|   2000 | David Brown   |            1 |                                                                                                             
|   2000 | John Doe      |            1 |                                                                                                             
|   2000 | Chris Evans   |            1 |                                                                                                             
|   2010 | Olivia Taylor |            1 |                                                                                                             
|   2010 | Chris Evans   |            1 |                                                                                                             
|   2020 | Emma Stone    |            1 |                                                                                                             
|   2020 | Alice Johnson |            1 |                                                                                                             
+--------+---------------+--------------+


*/

use mdb;
with Reviewer_count as (
    select r.Artist_id , count(*) as review_count, m.release_year from Reviews r join Movie m 
    on r.Movie_id=m.id group by m.release_year, r.Artist_id
),
Decade_rating as (
    select floor(m.release_year/10)*10 as decade, a.name as reviewer_name, rc.review_count
    from Reviewer_count rc join Artists a on rc.Artist_id = a.id 
    join Movies m on m.id = a.id group by decade
),

Most_Active as (
    select decade,reviewer_name,review_count, Row_number () over ( partition by decade order by review_count desc)
    as rn from Decade_rating
)

select decade, reviewer_name, review_count from Most_Active;



