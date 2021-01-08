CREATE DEFINER=`root`@`localhost` PROCEDURE `todayPopular_procedure`()
BEGIN
-- Today Popular (top5)      
	select a.title, a.hit, u.email, d.name
    from article as a
    join users as u on  a.reporter_id = u.id 
    join user_detail as d on u.id = d.user_id
    where day(a.created_at) = day(curdate()-1)
    group by a.id
    order by a.hit desc
    limit 5; 
END