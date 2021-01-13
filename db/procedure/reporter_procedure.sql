CREATE DEFINER=`root`@`localhost` PROCEDURE `reporter_procedure`(
	in _switch varchar(50)
	,in _id int
    ,out result_set varchar(10)
 
)
BEGIN
	declare checkId varchar(8);
	CASE _switch
    /* 기자 프로필
	   작성자 : 이종현
    */
    when 'reporter_profile' then
		select count(*)
		into checkId
		from users 
		where auth ='reporter' and id = _id;
    
		IF checkId > 0 THEN
			select
			users.id,
			users.email,
			ud.nick_Name,
			ud.name,
			ud.local,
			date_format(ud.birth, '%Y-%m-%d') as birth,
			ud.gender,
			ud.image,
			(select count(*) from subscriber where reporter_id = users.id) as subNum
			from 
			users

			inner join user_detail ud
			on users.id = ud.user_id
			where auth ='reporter' AND users.id = _id
			group by users.id
			;
			set result_set = '200';
		
		ELSEIF checkId = 0 THEN
			set result_set = '404';
		END IF;
    
    else set result_set = '500';
	end case;
    
END