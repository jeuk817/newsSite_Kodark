
select email,auth from users where email = 'email';
select * from users;
select * from article;
select * from category;
select * from daily_hit;
insert into daily_hit(user_id, day , hit) values (1,curdate()-7,3);
insert into article(reporter_id, category_id,title, content,hit, created_at) values (2,1,'ade','ffe',41,curdate()-3);
insert into user_detail(user_id,nick_name,name,local,birth) values(2,'nick','name','Newyork',curdate()-20);
-- select DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-1) DAY), '%Y/%m/%d') as start,
--       DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-7) DAY), '%Y/%m/%d') as end,
--       DATE_FORMAT(`created_at`, '%Y년%m월%U') AS `date`, sum(hit)
--       where DATE_FORMAT(`created_at`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U')
--       group by date
--       order by date asc;
       
       select 
	   DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-1) DAY), '%Y/%m/%d') as start,
       DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-7) DAY), '%Y/%m/%d') as end,
       DATE_FORMAT(`day`, '%Y년%m월%U') AS `date`, sum(hit) 
       from daily_hit
	   where DATE_FORMAT(`day`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U')
       group by date
       order by date asc;
       
       
  
select month(created_at) from article;
select (count(*)) as wac
       from article
	   where DATE_FORMAT(`created_at`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U')
;
select count(*) from users where auth='user' and status='use' union all
select a.title, a.hit, u.email, d.name 
    from article as a
    join users as u on  a.reporter_id = u.id 
    join user_detail as d on u.id = d.user_id
    group by a.id
    order by a.hit desc; 

select * from user_detail;    


select count(*) as totalReporter from users where auth='reporter' and status='use';
CALL statistics_procedure(1);
select LPAD(FLOOR(RAND() * 999999.99), 6, '0') from dual;
