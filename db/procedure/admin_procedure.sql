CREATE DEFINER=`root`@`localhost` PROCEDURE `admin_procedure`(
   in _switch varchar(20)
    , in _id int
    , inout _email varchar(50)    
    , inout _pwd varchar(300)
    , inout _nickName varchar(20)
    , inout _name varchar(20)    
    , inout _local varchar(50)
    , inout _birth datetime
    , inout _gender char(1)
    , inout _image varchar(200)
    , inout _auth char(8)
   , out result_set varchar(10)
)
BEGIN

declare idCount int;
declare authCheck char(8);

	if _switch = 'create_reporter' then
		insert into  users (email, pwd, auth) values (_email, _pwd, _auth);
		set @result = (select id from users where email = _email);
		insert into user_detail (user_id, nick_name, name, local, birth, gender, image) values (@result, _nickName, _name, _local, _birth, _gender, _image );
        
    elseif _switch = 'navigation' then
		select count(*) into idCount from users where id = _id;
      
		if idCount > 0 then
        
			select auth into authCheck from users where id = _id;

			if authCheck = 'admin' and _email is not null then
				set result_set = '204';
            else
				set result_set = '403';
			end if;

		elseif idCount < 0 or idCount = 0 then 
			set result_set = '401';
        else 
			set result_set = '500';
		end if; 
    -- 54.신고기사 블라인드 토글(최윤수)    
	elseif _switch = 'article_blind' then
		select count(*)into idCount from article where id = _id;
		if idCount > 0 then
			if _auth = 'unpublish' then
				update article set status = 'publish' where id = _id;				
            else
				update article set status = 'unpublish' where id = _id;				
            end if;
            set result_set = 204;
		else 
			set result_set = 404;
		end if;			
    -- 56.기사신고 확인(미완성)    
	elseif _switch = 'article_report' then			
		
        set result_set = '205';
    -- 53.신고기사 목록    
	elseif _switch = 'article_list' then	
		select 
        ar.id,ar.reason,date_format(ar.created_at, '%Y-%m-%d %H:%i:%S') as created_at,ar.user_id,ar.article_id
        ,a.reporter_id articleId,a.title,a.status
        ,u.email userEmail,u.id userId
        ,(select id from users us where us.id = a.reporter_id)as reporterId,(select email from users us where us.id = a.reporter_id)as reporterEmail
        from article_report as ar
        join article as a on a.id = ar.article_id
        join users as u on u.id = ar.user_id
        where done_flag = 'F';
    -- 49.문의글 목록    
	elseif _switch = 'question_list' then
	 	select q.id,q.user_id,q.title,q.content
        ,(select u.id from users u where u.id = q.user_id)userId
        ,(select u.email from users u where u.id = q.user_id)userEmail
        ,aw.question_id , aw.content as answer
        from question q 
        join users u on u.id = q.user_id
        left outer join answer aw on aw.question_id = q.id 
        order by q.id asc
        limit 10 offset _id
        ;
		
	end if;
END