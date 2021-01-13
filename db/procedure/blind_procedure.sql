CREATE DEFINER=`root`@`localhost` PROCEDURE `blind_procedure`(
in _article_id int
,in  _reporter_id int
,in result_set varchar(8)
)
BEGIN
	if result_set = 'delete' then
		update article set status = 'blind' where reporter_id = _reporter_id and id =_article_id;
	elseif result_set = 'get_list' then
		select a.id,a.title,a.sub_title, i.image,i.description 
        from article a
        left outer join image i on i.article_id = a.id
        where a.reporter_id = _reporter_id and a.status ='publish'
        ;
	end if;
    
END