CREATE DEFINER=`root`@`localhost` PROCEDURE `admin_repoters_procedure`(
)
BEGIN
	
	select 
    users.id, 
    user_detail.name, 
    count(distinct article.id) as articleNum, 
    sum(article.hit) as totalHit,
    (select count(*) from subscriber where reporter_id = users.id) as subNum
    from users 
    
    inner join user_detail 
    on 
    users.id = user_detail.user_id
    
    right join article
    on
    users.id = article.reporter_id
    
    where auth = 'reporter'
    group by users.id
    ;
END







