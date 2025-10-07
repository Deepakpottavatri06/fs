/*
Write a query to find the movie(s) with the Nth highest average rating.
Use a variable @N to select which rank to display.
Show movie name and average rating.

================================================================================

Database: bcode


Tables:

Artists
-------
| id | name | email | phone_number |

Movies
------
| id | name | release_year | budget |

Directions
----------
| id | Artist_id | Movie_id |

Reviews
-------
| id | date_of_review | Artist_id | Movie_id | rating |

================================================================================

Sample Output:
--------------
+----------------+------------+                                                                                         
| name           | avg_rating |                                                                                         
+----------------+------------+                                                                                         
| Digital Mirage |       8.50 |                                                                                         
| Retro Future   |       8.50 |                                                                                         
+----------------+------------+ 


*/
set @N = 1;
use bcode;
with movie_avg_rating as (
    select distinct round(avg(rating),2) as avg_rating from Reviews 
    group by Movie_id
)
select m.name , Round(avg(mar.rating),2) as avg_rating from 
Movies m 
join Reviews mar on m.id = mar.Movie_id
group by m.id having 
Round(avg(mar.rating),2) = (
    select avg_rating from movie_avg_rating 
    order by avg_rating desc
    limit 1 offset 1
);
