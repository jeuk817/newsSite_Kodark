CREATE DEFINER=`jack`@`localhost` PROCEDURE `users_procedure`(
   in _switch varchar(20)
    , in _id int
   , inout _email varchar(50)
    , inout _pwd varchar(45)    
    , inout _nickName varchar(20)
    , inout _name varchar(20)    
    , inout _local varchar(50)
    , inout _birth datetime
    , inout _gender char(1)
    , inout _image varchar(200)
    , out result_set varchar(10)
    , out _auth char(8)
)
begin
declare idCount int;
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
    end if;
END