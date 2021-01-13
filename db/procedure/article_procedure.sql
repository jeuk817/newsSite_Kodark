CREATE DEFINER=`root`@`localhost` PROCEDURE `article_procedure`(
in _article_id int
,in  _reporter_id int
,in result_set varchar(8)
)
BEGIN
	if result_set = 'blind' then
		update article set status = 'deleted' where reporter_id = _reporter_id and id =_article_id;
	elseif result_set = 'get_list' then
		select a.id,a.title,a.content, i.image,i.description 
        from article a
        left outer join image i on i.article_id = a.id
        where a.reporter_id = _reporter_id
        ;
	end if;
    
END