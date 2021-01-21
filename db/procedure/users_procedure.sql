CREATE DEFINER=`jack`@`localhost` PROCEDURE `users_procedure`(
    in _switch varchar(20)
    , in _id int
    , in _article_id int
    , in _comment_id int
    , in _content varchar(2000)
    , in _reporter_id int
    , in _reputation varchar(20)
    , in _reason varchar(20)
    , in _emotion varchar(30)
    , inout _email varchar(50)  
    , inout _pwd varchar(300)
    , inout _nickName varchar(20)
    , inout _name varchar(20)    
    , inout _local varchar(50)
    , inout _birth datetime
    , inout _gender char(1)
    , inout _image varchar(200)
    , out _auth char(8)
    , out result_set varchar(10)

)
begin
declare emailCheck int;
declare idCount int;
declare letterCheck char(1);
declare tmp_pwd varchar(300);
declare nickNameCount int;
declare _emotion_id int;

-- 회원정보 수정(34)--------------------------------------------
   if _switch = 'update_detail' then
      select count(*) into idCount from users where id = _id;        
      if idCount > 0 then
         if _email is null then
            set result_set = '404';
         else    
            update user_detail 
            set nickname = _nickName, name = _name ,local = _local, birth = _birth , gender = _gender ,image = _image  
            where id = _id;            
            set result_set = '204';
         end if;                       
      elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else 
         set result_set = '500';
      end if; 
	end if;

-- 회원 탈퇴(32) --------------------------------------------------
	if _switch = 'delete' then
	select count(*) into idCount from users where  id = _id;   
	select count(email) into emailCheck from users where id = _id;
      if idCount > 0 then
         if emailCheck = 0 then
            set result_set = '404';
         else    
            update users
            set status = 'stop', email = null where id = _id;
            set result_set = '204';
         end if;    
      elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else 
         set result_set = '500';    
      end if;
      
   elseif _switch = 'update_password' then 
      select count(*) into idCount from users where  id = _id;   
        
      if idCount > 0 then
         if _email is null then
            set result_set = '404';
		else    
            update users
            set pwd = _pwd
                where id = _id;
            set result_set = '204';
		end if;    
      elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else 
         set result_set = '500';    
		end if;

	elseif _switch = 'mypage_detail' then
      select count(*) into idCount from users where  id = _id;   

      if idCount > 0 then
         select nick_name, name, local, DATE_FORMAT(birth, '%Y-%m-%d %H:%i:%S') as birth, gender, image into _nickName, _name,  _local,  _birth, _gender, _image from user_detail where user_id = _id;
            
         set result_set = '200';
            
      elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else
         set result_set = '500';
      end if;
        
  elseif _switch = 'signin_info' then
      select count(*) into idCount from users where  id = _id;   
        
      if idCount > 0 then           
         set result_set = '200';
      else 
         set result_set = '401';
      end if;
      
   elseif _switch = 'user_info' then
      select email, auth, pwd into _email, _auth, _pwd from users where id = _id;
        if _email = null then
         set result_set = 'not_found';
      else
         set result_set = 'success';
        end if;
        
	/***** 이메일 업데이트 *****/
    elseif _switch = 'update_email' then
		select count(*) into idCount from users where email = _email;
        
        if idCount > 0 then
			set result_set = 'conflict';
		else
			update users set email = _email where id = _id;
            set result_set = 'success';
		end if;
        
	-- 37.구독취소 ys		
	elseif _switch = 'cancel_sub' then
		select count(*) into idCount from users where  id = _reporter_id and status = 'use';
        if idCount > 0 then
			delete from subscriber where reporter_id = _reporter_id and user_id = _id;
            set result_set = '205';
		else
			set result_set = '404';
		end if;
    
	-- 36.뉴스레터 토글 ys
  elseif _switch = 'toggle' then
		select count(*) into idCount from users where id = _id;
    if idCount > 0 then
			select letter_accepted into letterCheck from subscriber where user_id = _id and reporter_id = _reporter_id;
      if letterCheck = 'T' then
				update subscriber set letter_accepted = 'F' where user_id = _id and reporter_id = _reporter_id;
			elseif letterCheck = 'F' then
				update subscriber set letter_accepted = 'T' where user_id = _id and reporter_id = _reporter_id;
			end if;
      set result_set = '204';
		else
			set result_set = '404';
		end if;		
    
	end if;
      
-- 마이페이지(28)-----------------------------------------------------
	if _switch = 'my_page' then
		select count(*) into idCount from users where id = _id;   
		select email into _email from users where id = _id;
		select auth into _auth from users where id = _id; 
		
        if idCount > 0 then
			if _email is null then
			   set result_set = '404';
			else
				set result_set = '200';
			end if;
        end if;
	end if;
    
if _switch = 'user_update' then
	select count(*) into idCount from users where  id = _id;   
	
	if idCount > 0 then
        select count(*) into nickNameCount from user_detail where nick_name = _nickName;
		
        if nickNameCount > 0 then
			set result_set = '409';
		else
			select count(*) into idCount from user_detail where user_id = _id;
            
			if idCount > 0 then
				update user_detail set nick_name= _nickName, name = _name, local = _local, birth = _birth, gender = _gender, image = _image
                where user_id = _id;
			else
				insert into user_detail(user_id, nick_name, name, local, birth, gender, image)
				values(_id, _nickName, _name, _local, _birth, _gender, _image);
			end if;
            set result_set = '200';
		end if;
	else
		set result_set = '404';
	end if;
end if;

 -- 35.구독목록    
if _switch = 'sub_list' then
  select s.reporter_id, s.user_id, s.letter_accepted , ud.nick_name, (select email from users u where u.id = s.reporter_id)email, ud.image 
	from subscriber s
   left outer join users u on u.id = s.user_id
	left outer join user_detail ud on ud.user_id = s.reporter_id
	where u.id = _id;
end if; 

   -- 26.기자구독      
if _switch = 'subs' then
	select count(*) into idCount from subscriber where  user_id = _id and reporter_id = _reporter_id;
	if idCount > 0 then
		set result_set = '409';
	else    
		insert into subscriber(reporter_id, user_id) values(_reporter_id,_id);
		set result_set = '201';
	end if;
end if;

/* 댓글 입력 */
if _switch = 'insert_comment' then
	insert into comment(user_id, article_id, content) values(_id, _article_id, _content);
end if;

/*감정표현 선택*/
if _switch = 'choose_emotion' then
	if _emotion != '' then
		select id into _emotion_id from emotion where emotion = _emotion;

		select count(*) into idCount from article_emotion
		where article_id = _article_id and user_id = _id and emotion_id = _emotion_id;
		delete from article_emotion where article_id = _article_id and user_id = _id;
		
		if idCount <= 0 then
			insert into article_emotion(user_id,article_id,emotion_id) 
			values(_id, _article_id, _emotion_id);
		end if;
	end if;
    
	select emotion
		, (select count(*) from article_emotion 
			where article_id = _article_id 
				and emotion_id = id 
				and user_id = _id
			) as count
	from emotion
	;
end if;

if _switch = 'comment_reputation' then
	if _reputation != '' then
		select count(*) into idCount from comm_reputation
        where comment_id = _comment_id and user_id = _id and reputation = _reputation;
        
		delete from comm_reputation where comment_id = _comment_id and user_id = _id;
        
        if idCount <= 0 then
			insert into comm_reputation(user_id, comment_id, reputation) values(_id, _comment_id, _reputation);
        end if;
    end if;
    
    select reputation
	from comm_reputation
    where comment_id = _comment_id and user_id = _id
	;
end if;

if _switch = 'comment_reply' then
	-- _id, _article_id, _comment_id, _content
    insert into comment(parent_id, user_id, article_id, content) values(_comment_id, _id, _article_id, _content);
end if;
END