
select email,auth from users where email = 'email';
select * from users;
select * from article;
select * from category;
select * from daily_hit;
select * from user_detail;
select * from comment;
insert into image(article_id, image, source, description)
values(2,'image','imageSource','imageDescription');
insert into daily_hit(user_id, day , hit) values (1,curdate()-7,3);
insert into article(reporter_id, category_id,title, content,hit, created_at) values (2,1,'ade','ffe',41,curdate()-3);
insert into user_detail(user_id,nick_name,name,local,birth) values(2,'nick','name','Newyork',curdate()-20);
-- --
 select * from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id 
 group by a.id;
 -- 남자--
 select a.id ,count(*) as maleNum from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and ud.gender = 'M'
 group by a.id;
 -- 여자 --
select a.id, ifnull(count(*),0) as femaleNum from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and ud.gender = 'F'
group by a.id;
 -- 나이구하기 10대-- 
 select a.id,count(*)as 'age10' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 10 and 19
 group by a.id;
 -- 나이구하기 20대-- 
 select a.id,count(*)as 'age20' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 20 and 29
 group by a.id;
 -- 나이구하기 30대-- 
 select a.id,count(*)as 'age30' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 30 and 39
 group by a.id;
 -- 나이구하기 40대-- 
 select a.id,count(*)as 'age40' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 40 and 49
 group by a.id;
 -- 나이구하기 else-- 
 select a.id,count(*)as 'elseAge' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000)>50
 and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000)<10
 group by a.id;
 
 select m.id , maleNum, ifnull(femaleNum,0) femaleNum, ifnull(age10,0)age10,ifnull(age20,0)age20, ifnull(age30,0)age30, ifnull(age40,0)age40, ifnull(elseAge,0)elseAge from 
 
(select a.id ,count(*) as maleNum from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and ud.gender = 'M'
 group by a.id) m
left outer join  (select a.id,  count(*) as femaleNum from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and ud.gender = 'F'
group by a.id) f on m.id = f.id
left outer join (select a.id,count(*)as 'age10' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 10 and 19
 group by a.id) t on m.id = t.id
 left outer join (select a.id,count(*)as 'age20' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 20 and 29
 group by a.id) t2 on m.id = t2.id
 left outer join (select a.id,count(*)as 'age30' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 30 and 39
 group by a.id) t3 on m.id = t3.id
 left outer join (select a.id,count(*)as 'age40' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000) between 40 and 49
 group by a.id) t4 on m.id = t4.id
 left outer join (select a.id,count(*)as 'elseAge' from users as u join user_detail as ud on u.id=ud.user_id join comment as c on u.id=c.user_id join article as a on a.id = c.article_id
 where a.id = c.article_id and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000)>50
 and floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(ud.birth,'%Y%m%d')) AS UNSIGNED)) / 10000)<10
 group by a.id) t5 on m.id = t5.id
 ;

 
 
 
 select floor(((cast(replace(current_date(),'-','')as unsigned))-CAST((date_format(birth,'%Y%m%d')) AS UNSIGNED)) / 10000) from user_detail;
 

