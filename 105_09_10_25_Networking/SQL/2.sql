/*
Write a query using a CTE to find the average movie rating for each director 
(artist who directed at least one movie).

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+---------------------+                                                                                                               
| director_name | avg_directed_rating |                                                                                                               
+---------------+---------------------+                                                                                                               
| John Doe      |                9.00 |                                                                                                               
| Alice Johnson |                9.00 |                                                                                                               
| Emma Stone    |                9.00 |                                                                                                               
| David Brown   |                8.50 |                                                                                                               
| Olivia Taylor |                8.50 |                                                                                                               
| Bob Smith     |                8.00 |                                                                                                               
| Chris Evans   |                8.00 |                                                                                                               
| Jane Doe      |                7.67 |                                                                                                               
+---------------+---------------------+   


*/

use mdb;

with Director_Avg as (
    select d.Artist_id , avg(r.rating) as agr from Reviews r join Directions d on r.Movie_id=d.Movie_id group by d.Artist_id
)

select d.name as director_name , Round(da.agr,2) as avg_directed_rating from Artists d join Director_Avg da 
on d.id = da.Artist_id order by avg_directed_rating desc;
