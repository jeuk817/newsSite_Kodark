CREATE DEFINER=`root`@`localhost` PROCEDURE `reporter_procedure`(

in _switch varchar(20),
in _id int,
in _email varchar(50),
out result_set varchar(20)

)
BEGIN
	declare idCount int;
    declare authCheck char(8);
    
	if _switch = 'navigation' then
    select count(*) into idCount from users where id = _id;
    
		if idCount > 0 then
			select auth into authCheck from users where id = _id;
			
            if authCheck = 'reporter' and _email is not null then
				set result_set = '204';
			elseif _email is null then
				set result_set = '404';
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