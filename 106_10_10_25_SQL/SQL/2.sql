/*
Write a SQL query using CTEs and conditional aggregation to list all movies that
were reviewed by their own director and at least one other reviewer. 

Display:
--------
    movie_name
    total_reviewers
    director_reviews

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+------------+-----------------+------------------+                                                                                                   
| movie_name | total_reviewers | director_reviews |                                                                                                   
+------------+-----------------+------------------+                                                                                                   
| Movie B    |               3 |                1 |                                                                                                   
+------------+-----------------+------------------+  


*/

use mdb;
with total_reviews as (
    select Movie_id, count(*) as total_reviewers from Reviews group by Movie_id
),
director_reviews as (
    select d.Movie_id, count(*) as director_reviews from Directions d join Reviews r on d.Artist_id=r.Artist_id and
    d.Movie_id=r.Movie_id
    group by d.Movie_id
)

select m.name as movie_name , tr.total_reviewers, dr.director_reviews from
Movies m join total_reviews tr on m.id = tr.Movie_id
join director_reviews dr on m.id = dr.Movie_id;
