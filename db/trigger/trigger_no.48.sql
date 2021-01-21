
use kodark_times;

-- ----------------------------
-- 48. 회원정지 및 이메일전송 처리 ys
-- -------------------------
delimiter $$
create trigger updateUsersTB
after insert on forbidden 
for each row
begin 
declare userStatus varchar(8);
declare userId int;
set userStatus = new.status;
set userId = new.user_id; 
update users set status = new.status where id = userId ;
end $$
delimiter ;

-- ----------------------------
-- 48. 정지가 끝나면 상태변경
-- -------------------------
delimiter $$
create trigger updateUsersTB2
after delete on forbidden 
for each row
begin 
declare userId int;
set userId = old.user_id; 
update users set status = 'use' where id = userId ;
end $$
delimiter ;


delimiter $$
create trigger daily_hitTB
after update on article
for each row
begin 
declare idTemp int;
select count(*) into idTemp from daily_hit where user_id = new.reporter_id;
   if idTemp > 0 then
      if new.hit != old.hit then
         update daily_hit set hit = count(hit)+1 where user_id = new.reporter_id;
      end if;
   else
      insert into daily_hit(user_id, day, hit) values(new.reporter_id, date_format(current_timestamp, '%Y-%m-%d'),1);
    end if;    
end $$
delimiter ; 


