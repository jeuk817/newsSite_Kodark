CREATE DEFINER=`root`@`localhost` PROCEDURE `statistics_procedure`(
in _id int
,out weeklyHits int
,out weeklyArticleCount int
,out totalUser int
,out totalReporter int
,out monthlyIncUser int
,out monthlyDecUser int
,out result_set varchar(10)
)
BEGIN
-- 주간 조회수 ----------------------------
   select 
	 --  DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-1) DAY), '%Y/%m/%d') as start,
     --  DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-7) DAY), '%Y/%m/%d') as end,
     --  DATE_FORMAT(`day`, '%Y년%m월%U') AS `date`, 
       sum(hit) into weeklyHits
       from daily_hit 
	   where DATE_FORMAT(`day`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U');
     --  group by date
     --  order by date asc;
-- 주간 등록된 기사수 
    select 
	--   DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-1) DAY), '%Y/%m/%d') as start,
    --   DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-7) DAY), '%Y/%m/%d') as end,
    --   DATE_FORMAT(`created_at`, '%Y년%m월%U') AS `date`, 
       (count(*)) into weeklyArticleCount
       from article 
	   where DATE_FORMAT(`created_at`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U');
    --   group by date
    --   order by date asc;
-- totalUser
	select count(*) into totalUser from users where auth='user' and status='use';
-- totalReporter
	select count(*) into totalReporter from users where auth='reporter' and status='use';
-- monthlyIncUser   
	select count(*) into monthlyIncUser from users
    where month(created_at)=month(curdate()) and status='use';
-- monthlyDecUser
	select count(*) into monthlyDecUser from forbidden
    where month(created_at)=month(curdate()) and status='stop';

	

   
END