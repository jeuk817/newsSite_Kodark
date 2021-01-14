CREATE DEFINER=`jack`@`localhost` PROCEDURE `admin_repoters_procedure`(
)
BEGIN
	
	select 
		users.id, 
		user_detail.name, 
		count(article.id) as articleNum, 
		sum(article.hit) as totalHit,
		(select count(*) from subscriber where reporter_id = users.id) as subNum
	from users 
		
		left join user_detail 
		on 
		users.id = user_detail.user_id
		
		left join article
		on
		users.id = article.reporter_id
		
	where auth = 'reporter'
	group by users.id
	;
END