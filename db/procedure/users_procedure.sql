CREATE DEFINER=`jack`@`localhost` PROCEDURE `users_procedure`(
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
    , out _auth char(8)
    , out result_set varchar(10)
)
begin
declare idCount int;
declare tmp_pwd varchar(300);

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
        
	elseif _switch = 'my_page' then
		select email, auth into _email, _auth from users where id = _id;

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
        
	/***** 이메일 업데이트 *****/
    elseif _switch = 'update_email' then
		select count(*) into idCount from users where email = _email;
        
        if idCount > 0 then
			set result_set = 'conflict';
		else
			update users set email = _email where id = _id;
            set result_set = 'success';
		end if;
	
    /***** 비밀번호 업데이트 *****/
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
		select email, auth, pwd into _email, _auth, _pwd from users where id = _id;
        
        if _email = null then
			set result_set = 'not_found';
		else
			set result_set = 'success';
        end if;
    end if;
END