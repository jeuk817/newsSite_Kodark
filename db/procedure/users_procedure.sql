CREATE DEFINER=`root`@`localhost` PROCEDURE `users_procedure`(
   in _switch varchar(20)
    , in _id int
    , in _reporter_id int
    , inout _email varchar(50)
    , inout _pwd varchar(45)    
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
declare idCount int;
declare letterCheck char(1);
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
    elseif _switch = 'mypage' then
      select count(*) into idCount from users where  id = _id;   
       
      if idCount > 0 then
         if _email is null then
            set result_set = '404';
            else    
            select email, auth from users where id = _id;
            set _email = email;
            set _auth = auth;
            set result_set = '200';
            end if;    
      elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else 
         set result_set = '500';    
        end if;
      
    elseif _switch = 'mypage_detail' then
      select count(*) into idCount from users where  id = _id;   
        
          if idCount > 0 then
         if _email is null then
            set result_set = '404';
            else    
            select * from user_detail where id = _id;
            set _image = image, _nickName = nickName, _name = name, _local = local, _birth = birth, _gender = gender;
            set result_set = '200';
            end if;    
        elseif idCount < 0 or idCount = 0 then 
         set result_set = '401';
      else 
         set result_set = '500';
      end if;
     elseif _switch = 'delete' then
      select count(*) into idCount from users where  id = _id;   
        
      if idCount > 0 then
         if _email is null then
            set result_set = '404';
         else    
            update users
            set status = 'stop';
            set result_set = '200';
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
     elseif _switch = 'signin_info' then
      select count(*) into idCount from users where  id = _id;   
        
      if idCount > 0 then           
         set result_set = '200';
      else 
         set result_set = '401';
      
        end if;
   elseif _switch = 'user_info' then
      select email, auth into _email, _auth from users where id = _id;
        if _email = null then
         set result_set = 'not_found';
      else
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
	-- 35. 구독목록
    elseif _switch = 'sub_list' then
		select count(*) into idCount from users where id = _id;
        if idCount > 0 then
			select 
			s.reporter_id, s.user_id, s.letter_accepted , ud.nick_name
			,(select email from users u where u.id = s.reporter_id)email, ud.image 
			from subscriber s
			left outer join users u on u.id = s.user_id
			left outer join user_detail ud on ud.user_id = s.reporter_id
			where u.id = _id;
            set result_set = '200';
		else
			set result_set = '404';
		end if;
	elseif _switch = 'subs' then
		select count(*) into idCount from subscriber where user_id = _id and reporter_id = _reporter_id;
        if idCount > 0 then
			set result_set = '409';
		else		
			insert into subscriber(reporter_id,user_id) values(_reporter_id,_id);
            set result_set = '201';
		end if;				
    end if;
END