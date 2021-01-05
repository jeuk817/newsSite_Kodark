CREATE DEFINER=`root`@`localhost` PROCEDURE `statistics_procedure`(
in _id int
)
BEGIN
declare adminCheck char(8);
declare emailCheck varchar(50);
declare httpstatus varchar(50);
select auth into adminCheck from users where id = _id;
select email into emailCheck from users where id = _id; 
if adminCheck = 'admin' then
-- 주간 조회수 ----------------------------
   select 
	 --  DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-1) DAY), '%Y/%m/%d') as start,
     --  DATE_FORMAT(DATE_SUB(`day`, INTERVAL (DAYOFWEEK(`day`)-7) DAY), '%Y/%m/%d') as end,
     --  DATE_FORMAT(`day`, '%Y년%m월%U') AS `date`, 
       sum(hit) as weeklyHits
       from daily_hit 
	   where DATE_FORMAT(`day`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U');
     
     --  group by date
     --  order by date asc;
-- 주간 등록된 기사수 
    select 
	--   DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-1) DAY), '%Y/%m/%d') as start,
    --   DATE_FORMAT(DATE_SUB(`created_at`, INTERVAL (DAYOFWEEK(`created_at`)-7) DAY), '%Y/%m/%d') as end,
    --   DATE_FORMAT(`created_at`, '%Y년%m월%U') AS `date`, 
       (count(*)) as weeklyArticleCount
       from article 
	   where DATE_FORMAT(`created_at`, '%Y년%m월%U') = date_format(curdate(),'%Y년%m월%U');
     
    --   group by date
    --   order by date asc;
-- totalUser
	select count(*) as totalUser from users where auth='user' and status='use';
    
-- totalReporter
	select count(*) as totalReporter from users where auth='reporter' and status='use';
   

-- Today Popular (top5)   
	select a.title, a.hit, u.email, d.name 
    from article as a
    join users as u on  a.reporter_id = u.id 
    join user_detail as d on u.id = d.user_id
    group by a.id
    order by a.hit desc
    limit 5; 
-- monthlyIncUser   
	select count(*) as monthlyIncUser from users;
    -- where month(create_at)=month(curdate()) and status='use';
   
-- monthlyDecUser
	select count(*) as monthlyDecUser from users;
    -- where month(create_at)=month(curdate()) and status='stop';
    set httpstatus = '200';
    select httpstatus from dual;

 elseif adminCheck = 'user' or 'reporter' then
	set httpstatus = '403';
    select httpstatus from dual;

 elseif emailCheck is null then
	set httpstatus = '401';
    select httpstatus from dual;

 else

	set httpstatus = '500';
    select httpstatus from dual;
 end if;
	

   
END