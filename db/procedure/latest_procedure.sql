CREATE DEFINER=`root`@`localhost` PROCEDURE `latest_procedure`(
in category varchar(10)
)
BEGIN
-- 10개
select a.id, a.title, a.content, i.image, i.description as imgDec from article as a 
join image as i on a.id=i.article_id
join category as c on c.name = category
where created_at  > date_sub(now(), interval 12 hour) limit 10;
END