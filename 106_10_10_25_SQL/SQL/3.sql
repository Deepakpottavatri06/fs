/*
Write a SQL query using multi-level CTEs to determine which decades produced the 
most top-rated movies (average rating >= 8).

Display:
--------
    decade
    high_rating_count
    popularity_rank

Show the top 3 decades with the most highly rated movies.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+-------------------+-----------------+                                                                                                      
| decade | high_rating_count | popularity_rank |                                                                                                      
+--------+-------------------+-----------------+                                                                                                      
|   1990 |                 2 |               1 |                                                                                                      
|   1970 |                 1 |               2 |                                                                                                      
|   2000 |                 1 |               2 |                                                                                                      
|   2010 |                 1 |               2 |                                                                                                      
|   1980 |                 1 |               2 |                                                                                                      
|   2020 |                 1 |               2 |                                                                                                      
+--------+-------------------+-----------------+ 


*/

use mdb;
with Movie_Rating as (
    select m.id, m.release_year, avg(r.rating) as avg_rating
    from Movies m inner join Reviews r on m.id = r.Movie_id
    group by m.id,m.release_year
),

Decade_rating as (
    select (floor(release_year/10)*10) as decade, count(id) as high_rating_count
    from Movie_Rating where avg_rating>=8 group by decade 
),

Ranked_Decades as (
 select decade, high_rating_count , Dense_rank () over (order by high_rating_count desc ) as popularity_rank from Decade_rating
)

select decade, high_rating_count,popularity_rank from 
Ranked_Decades where popularity_rank<=3;
