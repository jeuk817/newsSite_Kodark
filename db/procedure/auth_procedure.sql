CREATE DEFINER=`jack`@`localhost` PROCEDURE `auth_procedure`(
	in _switch varchar(20)
	, inout _email varchar(50)
    , inout _auth_string varchar(6)
    , out result_set varchar(10)
)
BEGIN
declare emailCount int;

	if _switch = 'create_auth' then
		select count(*) into emailCount from user where email = _email;
        
		if emailCount > 0 then
			set result_set = 'conflict';
        else
			set _auth_string = LPAD(FLOOR(RAND() * 999999.99), 6, '0');
			insert into auth_string(email, auth_string) values(_email, _auth_string);
			set result_set = 'success';
		end if;
	end if;
    
END