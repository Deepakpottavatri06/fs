/*
Write a SQL query to find the movie that shows the greatest difference between 
its highest and lowest ratings.

Display:
    Movie name
    Rating difference

Hint: Use a CTE to calculate MAX(rating) - MIN(rating) for each movie.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------------+-------------------+                                                                                                                  
| movie_name   | rating_difference |                                                                                                                  
+--------------+-------------------+                                                                                                                  
| Codebreakers |                 4 |                                                                                                                  
+--------------+-------------------+  


*/

use mdb;

with Rating as (
    select Movie_id, max(rating) as maxR, min(rating) as minR from Reviews group by Movie_id
)
select m.name as movie_name , (r.maxR - r.minR) as rating_difference from 
Movies m join Rating r on m.id = r.Movie_id order by rating_difference desc
limit 1;
