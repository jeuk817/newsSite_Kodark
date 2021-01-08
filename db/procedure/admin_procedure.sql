CREATE DEFINER=`jack`@`localhost` PROCEDURE `admin_procedure`(
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
   end if;
END