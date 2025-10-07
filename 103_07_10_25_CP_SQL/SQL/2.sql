/*
Find the artists who reviewed their own movies.
Display the artist name, movie name, and rating they gave to their own movie.


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
+-------------+------------+--------+                                                                                   
| artist_name | movie_name | rating |                                                                                   
+-------------+------------+--------+                                                                                   
| Jane Doe    | Movie B    |      7 |                                                                                   
+-------------+------------+--------+


*/

use bcode;
select a.name as artist_name, m.name as movie_name, r.rating from Artists a 
join Reviews r on r.Artist_id=a.id
join Movies m on r.Movie_id=m.id
where (a.id,m.id) in (select Artist_id,Movie_id from Directions); 
