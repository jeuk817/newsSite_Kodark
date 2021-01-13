-- 이용제한이 풀리면 상태를 'use'로 업데이트하는 트리거
delimiter $$
create trigger updateUsers
after delete on forbidden
for each row
begin 
declare idTemp int;
set idTemp = old.user_id;
update users set status = 'use' where id = idTemp;
end $$
delimiter ; 