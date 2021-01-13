CREATE DEFINER=`root`@`localhost` PROCEDURE `latest_procedure`(
in _category varchar(10)
,out result_set varchar(8)
)
BEGIN
-- 10개
declare idCount int;
	select count(*)into idCount from article where created_at  > date_sub(now(), interval 12 hour);
    if idCount > 0 then
		select a.id, a.title, a.sub_title, i.image, i.description as imgDec from article as a 
		left outer join image as i on a.id=i.article_id
		left outer join category as c on c.name = _category
		where created_at  > date_sub(now(), interval 12 hour) 
		order by created_at desc
		limit 10;
        set result_set = '200';
    else
		set result_set = '404';
	end if;		
END