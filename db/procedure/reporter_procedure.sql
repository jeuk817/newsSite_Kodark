CREATE DEFINER=`jack`@`localhost` PROCEDURE `reporter_procedure`(
	in _switch varchar(50)
	, in _id int
    , inout _reporter_id int
    , inout _category_id int
    , inout _title varchar(200)
    , inout _sub_title varchar(500)
    , inout _content varchar(5000)
    , inout _main_image_url varchar(200)
    , inout _main_image_source varchar(200)
    , inout _main_image_description varchar(200)
	, out result_set varchar(10)
 
)
BEGIN
declare checkId varchar(8);
declare last_article_id int default -1;

CASE _switch
	/* 기자 프로필
	 * 작성자 : 이종현
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
			 from users

			 inner join user_detail ud
				on users.id = ud.user_id
			 where auth ='reporter' AND users.id = _id
			 group by users.id
			 ;
			 set result_set = '200';
      
		ELSEIF checkId = 0 THEN
			set result_set = '404';
		
      END IF;
    
	when 'new_post' then
		-- 트랜잭션
		start transaction;
		insert into article(reporter_id, category_id, title, sub_title, content)
        values(_reporter_id, _category_id, _title, _sub_title, _content);
        
        set last_article_id = last_insert_id();
        
        if last_article_id != -1 then
			insert into image(article_id, image, source, description)
            values(last_article_id, _main_image_url, _main_image_source, _main_image_description);
            commit;
            set result_set = 'success';
		else
			rollback;
            set result_set = 'fail';
		end if;
        
	ELSE
			set result_set = '500';
END CASE;
    
END