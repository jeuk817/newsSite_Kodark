CREATE DEFINER=`root`@`localhost` PROCEDURE `reporter_profile_procedure`(
	in _id int  
 
)
BEGIN

    select
    users.id,
    users.email,    
    ud.nick_name,
    ud.name,
    ud.image,  
    ud.local,
    date_format(ud.birth, '%Y-%m-%d') as birth,
    ud.gender,	
	(select count(*) from subscriber where reporter_id = users.id) as subNum   
    from 
    users

    inner join user_detail ud
    on users.id = ud.user_id
    where auth ='reporter' AND users.id = _id
    group by users.id
    ;

    
END