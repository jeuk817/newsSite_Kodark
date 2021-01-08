CREATE DEFINER=`jack`@`localhost` PROCEDURE `articleStatistics_procedure`(
	in _article_id int
)
BEGIN
 select m.id articleId, maleNum, ifnull(femaleNum,0) femaleNum, ifnull(age10,0)age10,ifnull(age20,0)age20, ifnull(age30,0)age30, ifnull(age40,0)age40, ifnull(elseAge,0)elseAge from  
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
 where _article_id = m.id 
 ;
END