select c.course_name , 
round(avg(
    case
        when e.grade = 'A' then 4
        when e.grade = 'B' then 3
        when e.grade = 'C' then 2
        else 0
    end

),2 )as avg_points from 
courses c join enrollments e on c.course_id = e.course_id
group by c.course_name
having avg(
    case
        when e.grade = 'A' then 4
        when e.grade = 'B' then 3
        when e.grade = 'C' then 2
        else 0
    end

) > (select avg(
    case
        when grade = 'A' then 4
        when grade = 'B' then 3
        when grade = 'C' then 2
        else 0
    end

) as avg_points from enrollments) order by c.course_name;