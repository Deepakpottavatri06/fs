/*
Write a SQL query using CTEs and window functions to find the top 3 highest-rated 
movies in each decade. Display the following columns:
    decade
    movie_name
    avg_rating

The output should show the top 3 movies per decade based on average ratings, 
ordered by decade and descending rating.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+--------------------+------------+                                                                                                          
| decade | movie_name         | avg_rating |                                                                                                          
+--------+--------------------+------------+                                                                                                          
|   1970 | Movie A            |       9.00 |                                                                                                          
|   1980 | Retro Future       |       8.50 |                                                                                                          
|   1980 | Movie B            |       7.67 |                                                                                                          
|   1990 | The Silent Network |       9.00 |                                                                                                          
|   1990 | Movie C            |       8.00 |                                                                                                          
|   2000 | The Quantum Code   |       9.00 |                                                                                                          
|   2010 | Digital Mirage     |       8.50 |                                                                                                          
|   2020 | Codebreakers       |       8.00 |                                                                                                          
+--------+--------------------+------------+ 


*/

use mdb;

with each_movie as (
    select (floor(m.release_year/10)*10) as decade 
    , m.name as movie_name, Round(avg(ma.rating),2) as avg_rating
    from Movies m join Reviews ma on m.id = ma.Movie_id
    group by (floor(m.release_year/10)*10),m.name
),
ranking as (
    select decade, movie_name, avg_rating , 
        Dense_rank() over (
            partition by decade
            order by avg_rating desc
        ) as rn
    from each_movie
)
select decade, movie_name, avg_rating from ranking 
where rn<=3;