CREATE DEFINER=`root`@`localhost` PROCEDURE `hotNews_procedure`()
BEGIN

select a.id, a.title, a.content, i.image, i.description as imgDec
from article as a 
join image as i on a.id=i.article_id
order by a.hit desc limit 3;

END