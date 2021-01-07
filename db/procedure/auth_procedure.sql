CREATE DEFINER=`jack`@`localhost` PROCEDURE `auth_procedure`(
	in _switch varchar(20)
	, inout _email varchar(50)
	, inout _auth_string varchar(6)
    , inout _pwd varchar(300)
    , out _id int
    , out _auth varchar(8)
    , out result_set varchar(10)
)
BEGIN
declare emailCount int;
declare verify varchar(6);
declare exp date;
set _id = -1;

   if _switch = 'create_auth' then
      select count(*) into emailCount from users where email = _email;
        
      if emailCount > 0 then
         set result_set = 'conflict';
        else
         set _auth_string = LPAD(FLOOR(RAND() * 999999.99), 6, '0');
         insert into auth_string(email, auth_string) values(_email, _auth_string);
         set result_set = 'success';
      end if;

   elseif _switch = 'confirm_verify' then   
         
            select auth_string, created_at into verify , exp from auth_string where email = _email order by created_at desc limit 1 ;
            
            if now() > date_add(exp, interval 30 minute) then
				set result_set = 'expiration';
            elseif _auth_string = verify then
				set result_set = 'success';          
			else 
				set result_set = 'fail';
            end if;
   elseif  _switch = 'sign_up' then         
         select count(*) into emailCount from users where email = _email;
		if emailCount > 0 then
			set result_set = 'conflict';
		else   
			insert into users(email, pwd) values(_email, _pwd);
			set result_set = 'success';
		end if;
   elseif _switch = 'sign_in' then
         select id, pwd, auth into _id, _pwd, _auth from users where email = _email;
         
		if _id = -1 then
			set result_set = 'not_found';
		else
			set result_set = 'success';
		end if;
        
	elseif _switch = 'google_oauth' then
		select id, auth into _id, _auth from users where email = _email;
        
        if _id = -1 then
			insert into users(email) values(_email);
			select id, auth into _id, _auth from users where email = _email;
            set result_set = 'sign_up';
		else
			set result_set = 'exist';
		end if;
   end if;
END